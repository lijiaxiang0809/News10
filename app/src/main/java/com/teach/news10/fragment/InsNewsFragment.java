package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.Frame.NetConfig;
import com.teach.news10.R;
import com.teach.news10.adapter.INSAdapter;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.bean.INSInfo;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;
import com.teach.news10.utils.NormalConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InsNewsFragment extends BaseMvpFragment<HomeModel> {

    private static final String FRAGMENT_ID = "fragment_id";
    private FirstPageTitleInfo.DataBean.ListBean mParam;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<INSInfo.FeedlistBean> mList = new ArrayList<>();
    private INSAdapter mAdapter;
    private INSInfo mInsInfo;

    public static InsNewsFragment newInstance(FirstPageTitleInfo.DataBean.ListBean param) {
        InsNewsFragment fragment = new InsNewsFragment();
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
        mAdapter = new INSAdapter(getContext(), mList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.INS_DATA, mParam.getApi(), LoadStatusConfig.NORMAL_LOAD);
    }

    @Override
    public void refresh() {
        super.refresh();
        mPresenter.getData(ApiConfig.INS_DATA, mParam.getApi(), LoadStatusConfig.REFRESH_LOAD);
    }

    @Override
    public void loadMore() {
        super.loadMore();
        if (mInsInfo != null && !TextUtils.isEmpty(mInsInfo.getNext()))
            mPresenter.getData(ApiConfig.INS_DATA, mInsInfo.getNext(), LoadStatusConfig.MORE_LOAD);
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
        mInsInfo = (INSInfo) t[0];
        int load = getLoadType(t);
        if (load == LoadStatusConfig.REFRESH_LOAD) {
            mList.clear();
            refreshLayout.finishRefresh();
        } else if (load ==LoadStatusConfig.MORE_LOAD) {
            refreshLayout.finishLoadMore();
        }
        List<INSInfo.FeedlistBean> feedlist = mInsInfo.getFeedlist();
        mList.addAll(feedlist);
        mAdapter.notifyDataSetChanged();
    }
}
