package com.teach.news10.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.Frame.OnRecyclerItemClick;
import com.teach.news10.R;
import com.teach.news10.adapter.LeftBarAdapter;
import com.teach.news10.adapter.TeamerResultAdapter;
import com.teach.news10.bean.NBAPersonInfo;
import com.teach.news10.bean.TeamerResultInfo;
import com.teach.news10.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 任小龙 on 2019/5/13.
 */
public class TeamDataFragment extends BaseMvpFragment<DataModel> implements OnRecyclerItemClick {

    private static final String TAB_NAME = "tab_name";
    private static final String TAB_URL = "tab_url";
    @BindView(R.id.left_recyclerView)
    RecyclerView leftRecyclerView;
    @BindView(R.id.team)
    TextView team;
    @BindView(R.id.recyclerView_data)
    RecyclerView recyclerViewData;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private String tabName;
    private String tabUrl;
    private List<NBAPersonInfo.ContentBean.DataBean> mListData = new ArrayList<>();
    private LeftBarAdapter mLeftBarAdapter;
    private List<String> tempList = new ArrayList<>();
    private int currentIndex;
    private List<TeamerResultInfo.ContentBean.DataBean> teamerResult = new ArrayList<>();
    private TeamerResultAdapter mTeamerResultAdapter;

    public static TeamDataFragment newInstance(String param1, String param2) {
        TeamDataFragment fragment = new TeamDataFragment();
        Bundle args = new Bundle();
        args.putString(TAB_NAME, param1);
        args.putString(TAB_URL, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(TAB_NAME);
            tabUrl = getArguments().getString(TAB_URL);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.team_data_layout;
    }

    @Override
    public void initView() {
        if (tabName.equals("球员")) team.setVisibility(View.VISIBLE);
        else team.setVisibility(View.GONE);
        //左侧tab recyclerView
        initRecycleView(leftRecyclerView, null);
        mLeftBarAdapter = new LeftBarAdapter(getContext(), mListData, tempList);
        leftRecyclerView.setAdapter(mLeftBarAdapter);
        mLeftBarAdapter.setOnRecyclerItemClick(this);
        //右侧数据recyclerView
        initRecycleView(recyclerViewData, null);
        mTeamerResultAdapter = new TeamerResultAdapter(getContext(), teamerResult,tabName);
        recyclerViewData.setAdapter(mTeamerResultAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showLoadingDialog();
                mPresenter.getData(ApiConfig.TEAMER_RESULT_INFO, mListData.get(currentIndex).getUrl());
            }
        });
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.NBA_PERSON_DATA, tabUrl);
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
            case ApiConfig.NBA_PERSON_DATA:
                NBAPersonInfo info = (NBAPersonInfo) t[0];
                List<NBAPersonInfo.ContentBean.DataBean> data = info.getContent().getData();
                if (mListData.size() != 0) mListData.clear();
                mListData.addAll(data);
                if (tempList.size() != 0) tempList.clear();
                tempList.add(data.get(0).getName());
                currentIndex = 0;
                mPresenter.getData(ApiConfig.TEAMER_RESULT_INFO, data.get(currentIndex).getUrl());
                break;
            case ApiConfig.TEAMER_RESULT_INFO:
                this.teamerResult.clear();
                TeamerResultInfo info1 = (TeamerResultInfo) t[0];
                List<TeamerResultInfo.ContentBean.DataBean> teamerResult = info1.getContent().getData();
                this.teamerResult.addAll(teamerResult);
                mLeftBarAdapter.notifyDataSetChanged();
                mTeamerResultAdapter.notifyDataSetChanged();
                hideLoadingDialog();
                if (refreshLayout.isRefreshing())refreshLayout.setRefreshing(false);
                break;
        }
    }

    @Override
    public void onItemClick(int pos) {
        showLoadingDialog();
        currentIndex = pos;
        tempList.clear();
        tempList.add(mListData.get(pos).getName());
        mPresenter.getData(ApiConfig.TEAMER_RESULT_INFO, mListData.get(currentIndex).getUrl());
    }
}
