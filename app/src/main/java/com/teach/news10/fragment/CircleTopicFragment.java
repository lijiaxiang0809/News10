package com.teach.news10.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.CircleTopicAdapter;
import com.teach.news10.bean.CircleTopicInfo;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class CircleTopicFragment extends BaseMvpFragment<HomeModel> {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private String mParam1;
    private List<CircleTopicInfo.DataBean.TopicListBean> mList = new ArrayList<>();
    private CircleTopicAdapter mAdapter;

    public static CircleTopicFragment newInstance(String param1) {
        CircleTopicFragment fragment = new CircleTopicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle_topic;
    }

    @Override
    public void initView() {
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.back_grey));
        initRecycleView(recyclerView, refreshLayout);
        mAdapter = new CircleTopicAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.CIRCLE_TOPIC_DATA, mParam1, LoadStatusConfig.NORMAL_LOAD);
    }

    @Override
    public void refresh() {
        super.refresh();
        mPresenter.getData(ApiConfig.CIRCLE_TOPIC_DATA, mParam1, LoadStatusConfig.REFRESH_LOAD);
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
        int load = (int) t[1];
        if (load == LoadStatusConfig.REFRESH_LOAD){
            mList.clear();
            refreshLayout.finishRefresh();
        }
        CircleTopicInfo info = (CircleTopicInfo) t[0];
        List<CircleTopicInfo.DataBean.TopicListBean> topic_list = info.getData().getTopic_list();
        mList.addAll(topic_list);
        mAdapter.notifyDataSetChanged();
    }
}
