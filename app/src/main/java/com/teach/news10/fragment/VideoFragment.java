package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.FirstPageVideoAdapter;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.bean.NormalNewsInfo;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoFragment extends BaseMvpFragment<HomeModel> {
    private static final String FRAGMENT_ID = "fragment_id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private FirstPageTitleInfo.DataBean.ListBean mParam;
    private List<NormalNewsInfo.ArticlesBean> mList = new ArrayList<>();
    private FirstPageVideoAdapter mAdapter;
    private NormalNewsInfo mInfo;

    public static VideoFragment newInstance(FirstPageTitleInfo.DataBean.ListBean param) {
        VideoFragment fragment = new VideoFragment();
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
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerView, refreshLayout);
        mAdapter = new FirstPageVideoAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.FIRST_PAGE_VIDEO_INFO, mParam.getApi(), LoadStatusConfig.NORMAL_LOAD);
    }

    @Override
    public void refresh() {
        super.refresh();
        if (mInfo != null && !TextUtils.isEmpty(mInfo.getFresh())){
            mPresenter.getData(ApiConfig.FIRST_PAGE_VIDEO_INFO, mInfo.getFresh(), LoadStatusConfig.REFRESH_LOAD);
        } else {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mInfo != null && !TextUtils.isEmpty(mInfo.getNext())){
            mPresenter.getData(ApiConfig.FIRST_PAGE_VIDEO_INFO, mInfo.getNext(), LoadStatusConfig.MORE_LOAD);
        } else refreshLayout.setNoMoreData(true);
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
        mInfo = (NormalNewsInfo) t[0];
        int loadType = getLoadType(t);
        if (loadType == LoadStatusConfig.REFRESH_LOAD){
            refreshLayout.finishRefresh();
            mList.clear();
        } else if (loadType ==  LoadStatusConfig.MORE_LOAD)refreshLayout.finishLoadMore();
        mList.addAll(mInfo.getArticles());
        mAdapter.notifyDataSetChanged();
    }
}
