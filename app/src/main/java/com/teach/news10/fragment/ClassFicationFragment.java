package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.ClassFicationAdapter;
import com.teach.news10.bean.ClassFicationInfo;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassFicationFragment extends BaseMvpFragment<HomeModel> {

    private static final String FRAGMENT_ID = "fragment_id";
    private FirstPageTitleInfo.DataBean.ListBean mParam;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<ClassFicationInfo.ArticlesBean> mList = new ArrayList<>();
    private ClassFicationAdapter mAdapter;

    public static ClassFicationFragment newInstance(FirstPageTitleInfo.DataBean.ListBean param) {
        ClassFicationFragment fragment = new ClassFicationFragment();
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mList.size() == 0 && mParam != null && !TextUtils.isEmpty(mParam.getApi()))
            mPresenter.getData(ApiConfig.CLASS_FICATION_DATA, mParam.getApi());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerView, refreshLayout);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        mAdapter = new ClassFicationAdapter(mList,getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.CLASS_FICATION_DATA, mParam.getApi());
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
        ClassFicationInfo info = (ClassFicationInfo) t[0];
        List<ClassFicationInfo.ArticlesBean> articles = info.getArticles();
        mList.addAll(articles);
        mAdapter.notifyDataSetChanged();
    }
}
