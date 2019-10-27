package com.teach.news10.fragment;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.Frame.OnRecyclerItemClick;
import com.teach.news10.R;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.activity.NewsDetailActivity;
import com.teach.news10.adapter.NormalNewsAdapter;
import com.teach.news10.bean.FavTeamEntity;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.bean.MatchInfo;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.bean.ZipImportNewInfo;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;
import com.teach.news10.utils.NormalConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.teach.news10.local_utils.NetworkUtils.isWiFiConnect;

public class NormalNewsFragment extends BaseMvpFragment<HomeModel> implements OnRecyclerItemClick {

    private static final String FRAGMENT_ID = "fragment_id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private FirstPageTitleInfo.DataBean.ListBean mParam;
    private String faveUrl = "https://bkbapi.dqdgame.com/v2/league/favlists";
    List<NormalNewsInfo.ArticlesBean> articles = new ArrayList<>();
    List<NormalNewsInfo.RecommendBean> recommend = new ArrayList<>();
    List<MatchInfo> matchInfo = new ArrayList<>();
    private NormalNewsAdapter mAdapter;
    private ZipImportNewInfo mImportNewInfo;
    public int firstPosition = 0, visibleCount = 0, lastPosition = 0;
    private HomeActivity mActivity;

    public static NormalNewsFragment newInstance(FirstPageTitleInfo.DataBean.ListBean param) {
        NormalNewsFragment fragment = new NormalNewsFragment();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_ID, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getParcelable(FRAGMENT_ID);
        }
        mActivity = (HomeActivity) getActivity();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_page_news;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerView, refreshLayout);
        mAdapter = new NormalNewsAdapter(getContext(), articles, recommend, matchInfo, mParam.getLabel());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerItemClick(this);
    }

    @Override
    public void initData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == SCROLL_STATE_IDLE){
                    if (mActivity.mApplication.mPlayInWifi && isWiFiConnect(getContext()) || !mActivity.mApplication.mPlayInWifi){
                        playVideo(recyclerView);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstPosition = mManager.findFirstVisibleItemPosition();
                lastPosition = mManager.findLastVisibleItemPosition();
                visibleCount = lastPosition - firstPosition;
            }
        });
        getData(LoadStatusConfig.NORMAL_LOAD);
    }

    private void playVideo(RecyclerView view) {
        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.video_player) != null) {
                JCVideoPlayerStandard videoPlayer = view.getChildAt(i).findViewById(R.id.video_player);
                Rect rect = new Rect();
                videoPlayer.getLocalVisibleRect(rect);
                int height = videoPlayer.getHeight();
                if (rect.top == 0 && rect.bottom == height) {
                    if (videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
//                        videoPlayer.startButton.performClick();
                        videoPlayer.startVideo();
                        mActivity.mVideoPlayer = videoPlayer;
                    }
                    return;
                }
            }
        }
        JCVideoPlayer.releaseAllVideos();
        mActivity.mVideoPlayer = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if ((mActivity.mApplication.mPlayInWifi && isWiFiConnect(getContext())) || !mActivity.mApplication.mPlayInWifi)
            if (visibleCount != 0 && recyclerView != null) playVideo(recyclerView);
        } else {
            mActivity.releaseVideo();
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        getData(LoadStatusConfig.REFRESH_LOAD);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        getData(LoadStatusConfig.MORE_LOAD);
    }

    private void getData(int pNormal) {
        String mainUrl = mParam.getApi();
        if (mImportNewInfo != null) {
            if (pNormal == LoadStatusConfig.MORE_LOAD) mainUrl = mImportNewInfo.next;
            else if (pNormal == LoadStatusConfig.NORMAL_LOAD)showLoadingDialog();
        }
        if (mParam.getLabel().equals("头条")){
            List<FavTeamEntity> tempData = SharedPrefrenceUtils.getSerializableList(getContext(), NormalConfig.CATCH_GIRL_FRIEND);
            faveUrl = tempData != null && tempData.size() != 0 ? NormalConfig.NON_NULL : faveUrl;
            mPresenter.getData(ApiConfig.GET_IMPORT_ZIP_DATA, mainUrl, mParam.getIndex_match_url(), faveUrl, pNormal);
        } else
            mPresenter.getData(ApiConfig.GET_NORMAL_DATA_WITHOUT_IMPORT, mainUrl, pNormal);
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hideLoadingDialog();
        switch (whichApi) {
            case ApiConfig.GET_IMPORT_ZIP_DATA:
                setImportData(t);
                break;
            case ApiConfig.GET_NORMAL_DATA_WITHOUT_IMPORT:
                NormalNewsInfo info = (NormalNewsInfo) t[0];
                int loadMode = getLoadType(t);
                if (loadMode == LoadStatusConfig.REFRESH_LOAD) {
                    articles.clear();
                    refreshLayout.finishRefresh();
                } else if (loadMode == LoadStatusConfig.MORE_LOAD) refreshLayout.finishLoadMore();
                articles.addAll(info.getArticles());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void setImportData(Object[] t) {
        mImportNewInfo = (ZipImportNewInfo) t[0];
        int load = getLoadType(t);
        if (load == LoadStatusConfig.REFRESH_LOAD) {
            articles.clear();
            recommend.clear();
            matchInfo.clear();
            refreshLayout.finishRefresh();
        } else if (load == LoadStatusConfig.MORE_LOAD) refreshLayout.finishLoadMore();
        List<NormalNewsInfo.ArticlesBean> articles = mImportNewInfo.articles;
        List<NormalNewsInfo.RecommendBean> recommend = mImportNewInfo.recommend;
        List<MatchInfo> matchInfo = mImportNewInfo.matchInfo;
        FavTeamEntity favOut = mImportNewInfo.mFavTeamEntity != null ? mImportNewInfo.mFavTeamEntity : new FavTeamEntity();
        if (load != LoadStatusConfig.MORE_LOAD) {
            for (int i = 0; i < articles.size(); i++) {
                if (articles.get(i).isTop()) {
                    continue;
                } else {
                    NormalNewsInfo.ArticlesBean bean = new NormalNewsInfo.ArticlesBean();
                    bean.favTeamEntity = favOut;
                    bean.setIs_video(false);
                    articles.add(i, bean);
                    break;
                }
            }
        }
        if (matchInfo != null) this.matchInfo.addAll(matchInfo);
        this.articles.addAll(articles);
        if (recommend != null) {
            this.recommend.clear();
            this.recommend.addAll(recommend);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int pos) {
        NormalNewsInfo.ArticlesBean newsModel = articles.get(pos);
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(NormalConfig.NEWS_DETAIL, newsModel);
        startActivity(intent);
    }
}
