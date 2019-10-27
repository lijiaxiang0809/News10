package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.HotFatherAdapter;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.bean.HotInfo;
import com.teach.news10.design.SuspensionDecoration;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HotFragment extends BaseMvpFragment<HomeModel> {
    private static final String HOT_CONTENT = "hot_content";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private FirstPageTitleInfo.DataBean.ListBean mParam;
    private List<HotInfo.ContentsBean> mContentsBeanList = new ArrayList<>();
    private HotFatherAdapter mAdapter;
    private HotInfo mHotInfo;

    public static HotFragment newInstance(FirstPageTitleInfo.DataBean.ListBean listBean) {
        HotFragment fragment = new HotFragment();
        Bundle args = new Bundle();
        args.putParcelable(HOT_CONTENT, listBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getParcelable(HOT_CONTENT);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerView, refreshLayout);
        mAdapter = new HotFatherAdapter(mContentsBeanList, getContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new SuspensionDecoration(getContext(), mContentsBeanList));
    }

    @Override
    public void refresh() {
        super.refresh();
        if(mHotInfo == null)return;
        getData(LoadStatusConfig.REFRESH_LOAD, mParam.getApi());
    }

    private void getData(int pRefresh, String url) {
        if (mHotInfo != null)
            mPresenter.getData(ApiConfig.HOT_DATA, url,pRefresh);
        else showLog(getString(R.string.dont_found_url));
    }

    @Override
    public void loadMore() {
        super.loadMore();
        getData(LoadStatusConfig.MORE_LOAD, mHotInfo.getNext());
    }

    @Override
    public void initData() {
        if (mParam != null)
            mPresenter.getData(ApiConfig.HOT_DATA, mParam.getApi(),LoadStatusConfig.NORMAL_LOAD );
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
        switch (whichApi) {
            case ApiConfig.HOT_DATA:
                mHotInfo = (HotInfo) t[0];
                int loadType = (int) t[1];
                if (loadType == LoadStatusConfig.MORE_LOAD){
                    refreshLayout.finishLoadMore();
                } else if (loadType == LoadStatusConfig.REFRESH_LOAD){
                    refreshLayout.finishRefresh();
                    mContentsBeanList.clear();
                }
                mContentsBeanList.addAll(mHotInfo.getContents());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
