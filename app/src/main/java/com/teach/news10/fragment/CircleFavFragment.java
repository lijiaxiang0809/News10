package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.bean.CircleFavInfo;
import com.teach.news10.model.HomeModel;

import butterknife.BindView;
import butterknife.Unbinder;

public class CircleFavFragment extends BaseMvpFragment<HomeModel> {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private String mParam1;

    public static CircleFavFragment newInstance(String param1) {
        CircleFavFragment fragment = new CircleFavFragment();
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter.getData(ApiConfig.CIRCLE_FAV_DATA,mParam1);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle_fav;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
        CircleFavInfo info = (CircleFavInfo) t[0];
        showToast(info.getErrmsg());
    }
}
