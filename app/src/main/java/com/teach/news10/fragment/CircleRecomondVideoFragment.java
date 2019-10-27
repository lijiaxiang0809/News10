package com.teach.news10.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.CircleRecomondVideoAdapter;
import com.teach.news10.bean.RecommondAndVideoInfo;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CircleRecomondVideoFragment extends BaseMvpFragment<HomeModel> {

    private static final String ARG_PARAM1 = "url";
    private static final String ARG_PARAM2 = "fragmentType";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String url;
    private String label;
    List<RecommondAndVideoInfo.DataBean.FeedsListBean> mList = new ArrayList<>();
    private CircleRecomondVideoAdapter mAdapter;

    public static CircleRecomondVideoFragment newInstance(String param1, String param2) {
        CircleRecomondVideoFragment fragment = new CircleRecomondVideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_PARAM1);
            label = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle_inner;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerView, refreshLayout);
        mAdapter = new CircleRecomondVideoAdapter(getContext(), mList, label);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        setData(LoadStatusConfig.NORMAL_LOAD);
    }

    @Override
    public void refresh() {
        super.refresh();
        setData(LoadStatusConfig.REFRESH_LOAD);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        setData(LoadStatusConfig.MORE_LOAD);
    }

    private void setData(int pNormal) {
        if (pNormal == LoadStatusConfig.NORMAL_LOAD)showLoadingDialog();
        if (label.equals("推荐"))
            mPresenter.getData(ApiConfig.CIRCLE_RECONMMOND_VIDEO, url,pNormal);
        if (label.equals("视频"))
            mPresenter.getData(ApiConfig.CIRCLE_VIDEO, url,pNormal);
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
        int loadType = (int) t[1];
        if (loadType == LoadStatusConfig.REFRESH_LOAD) {
            mList.clear();
            refreshLayout.finishRefresh();
        } else if (loadType == LoadStatusConfig.MORE_LOAD) refreshLayout.finishLoadMore();
        switch (whichApi) {
            case ApiConfig.CIRCLE_RECONMMOND_VIDEO:
                RecommondAndVideoInfo info = (RecommondAndVideoInfo) t[0];
                List<RecommondAndVideoInfo.DataBean.FeedsListBean> feeds_list = info.getData().getFeeds_list();
                if (info.getData().getTopic_banner() != null && info.getData().getTopic_banner().getTopic_list() != null
                        && info.getData().getTopic_banner().getTopic_list().size() != 0) {
                    RecommondAndVideoInfo.DataBean.FeedsListBean bannerData = new RecommondAndVideoInfo.DataBean.FeedsListBean();
                    bannerData.topic_banner = info.getData().getTopic_banner();
                    feeds_list.add(0, bannerData);
                }
                mList.addAll(feeds_list);
                mAdapter.notifyDataSetChanged();
                break;
            case ApiConfig.CIRCLE_VIDEO:
                RecommondAndVideoInfo videoInfo = (RecommondAndVideoInfo) t[0];
                List<RecommondAndVideoInfo.DataBean.FeedsListBean> videoList = videoInfo.getData().getFeeds_list();
                mList.addAll(videoList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
