package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.CUBASortAdapter;
import com.teach.news10.adapter.CbaTeamQuenAdapter;
import com.teach.news10.adapter.RankAdapter;
import com.teach.news10.adapter.TeamQuenAdapter;
import com.teach.news10.bean.DataCubaInfo;
import com.teach.news10.bean.RankInfo;
import com.teach.news10.bean.TempRankInfo;
import com.teach.news10.model.DataModel;
import com.teach.news10.presenter.DataInnerPresenter;
import com.teach.news10.utils.LoadStatusConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DataTabSortCUBAFragment extends BaseMvpFragment<DataModel> {
    private String tabName;
    private String tabUrl;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView_west)
    RecyclerView mRecyclerViewWest;
    private static final String TAB_NAME = "tab_name";
    private static final String TAB_URL = "tab_url";
    private static final String FATHER_TAB_NAME = "label";
    private String mFatherTab;
    List<DataCubaInfo.ContentBeanX.RoundsBean.ContentBean.DataBeanX> mCubaData = new ArrayList<>();
    private CUBASortAdapter mAdapter;

    public static DataTabSortCUBAFragment newInstance(String param1, String param2, String label) {
        DataTabSortCUBAFragment fragment = new DataTabSortCUBAFragment();
        Bundle args = new Bundle();
        args.putString(TAB_NAME, param1);
        args.putString(TAB_URL, param2);
        args.putString(FATHER_TAB_NAME, label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(TAB_NAME);
            tabUrl = getArguments().getString(TAB_URL);
            mFatherTab = getArguments().getString(FATHER_TAB_NAME);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_tab_sort_cuba;
    }

    @Override
    public void initView() {
        initRecycleView(mRecyclerViewWest, null);
        mAdapter = new CUBASortAdapter(mCubaData, getContext());
        mRecyclerViewWest.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                getData(LoadStatusConfig.REFRESH_LOAD);
            }
        });
    }

    @Override
    public void initData() {
        if (mCubaData.size() == 0)
            getData(LoadStatusConfig.NORMAL_LOAD);
    }

    private void getData(int loadMode) {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.GET_SORT_CUBA_DATA, loadMode, tabUrl);
    }

    @Override
    public DataModel getModel() {
        return new DataModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_SORT_CUBA_DATA:
                if ((int) t[1] == LoadStatusConfig.REFRESH_LOAD) {
                    mCubaData.clear();
                    mRefreshLayout.setRefreshing(false);
                }
                DataCubaInfo dataCubaInfo = (DataCubaInfo) t[0];
                List<DataCubaInfo.ContentBeanX.RoundsBean.ContentBean.DataBeanX> data = dataCubaInfo.getContent().getRounds().get(0).getContent().getData();
                mCubaData.addAll(data);
                mAdapter.notifyDataSetChanged();
                hideLoadingDialog();
                break;
        }
    }

}
