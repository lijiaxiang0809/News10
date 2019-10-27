package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.adapter.MatchInnerFatherAdapter;
import com.teach.news10.bean.MatchInnerInfo;
import com.teach.news10.bean.MatchInnerTeamerInfo;
import com.teach.news10.bean.SelfMatchInfo;
import com.teach.news10.design.MatchInnerDecoration;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.LoadStatusConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MatchInnerFragment extends BaseMvpFragment<HomeModel> {
    private static final String API = "api";
    private static final String LABEL = "label";
    private String mApi;
    private String mLabel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<MatchInnerInfo.ListBean> list ;
    private List<SelfMatchInfo> mListData ;
    private MatchInnerFatherAdapter mAdapter;

    public static MatchInnerFragment newInstance(String api, String label) {
        MatchInnerFragment fragment = new MatchInnerFragment();
        Bundle args = new Bundle();
        args.putString(API, api);
        args.putString(LABEL, label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mApi = getArguments().getString(API);
            mLabel = getArguments().getString(LABEL);
            list = new ArrayList<>();
            mListData= new ArrayList<>();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerView, refreshLayout);
        mAdapter = new MatchInnerFatherAdapter(mListData, getContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new MatchInnerDecoration(getContext(), mListData));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
    }

    @Override
    public void initData() {
        if (list.size() != 0) {
            list.clear();
        }
        mPresenter.getData(ApiConfig.MATCH_INNER_DATA, mApi, LoadStatusConfig.NORMAL_LOAD);
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
            case ApiConfig.MATCH_INNER_DATA:
                MatchInnerInfo innerInfo = (MatchInnerInfo) t[0];
                List<MatchInnerInfo.ListBean> list = innerInfo.getList();
                this.list.addAll(list);
                String matchUrls = "[";
                for (int i = 0; i < list.size(); i++) {
                    matchUrls += list.get(i).getMatch_id() + ",";
                }
                if (matchUrls.length() > 1)
                    matchUrls = matchUrls.substring(0, matchUrls.length() - 1);
                matchUrls += "]";
                mPresenter.getData(ApiConfig.MATCH_INNER_TEAMER_DATA, matchUrls);
                break;
            case ApiConfig.MATCH_INNER_TEAMER_DATA://https://bkbapi.dqdgame.com/data/tab/matchs/max/person?match_ids=[11384,11451,11430,11427,11469,11472,11471,11504,11505,11506,11515]
                MatchInnerTeamerInfo info = (MatchInnerTeamerInfo) t[0];
                List<MatchInnerInfo.TeamerInfo> data = info.data;
                if (data != null && data.size() != 0 && this.list.size() != 0) {
                    for (int i = 0; i < data.size(); i++) {
                        MatchInnerInfo.ListBean listBean = this.list.get(i);
                        listBean.mTeamerInfo = data.get(i);
                    }
                }
                if (mLabel != null && (mLabel.equals("重要"))) {
                    MatchFragment fatherFragment = (MatchFragment) getParentFragment();
                    String dataIndex = fatherFragment != null && fatherFragment.mLive_index != null ? fatherFragment.mLive_index : "";
                    if (!TextUtils.isEmpty(dataIndex))
                        mPresenter.getData(ApiConfig.MATCH_INNER_DATA_INDEX, dataIndex);
                    else {
                        changeData();
                    }
                } else {
                    changeData();
                }
                break;
            case ApiConfig.MATCH_INNER_DATA_INDEX:
                List<MatchInnerInfo.ListBean> listIndex = (List<MatchInnerInfo.ListBean>) t[0];
                this.list.addAll(listIndex);
                changeData();
                break;
        }
    }

    private void changeData() {
        for (int i = 0; i < list.size(); i++) {
            String time = list.get(i).getStart_play().substring(0,10);
            if (i == 0){
               newData(time,i);
            } else {
                if (time.equals(mListData.get(mListData.size()-1).day)){
                    SelfMatchInfo info = mListData.get(mListData.size() - 1);
                    info.data.add(list.get(i));
                } else {
                    newData(time,i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
        hideLoadingDialog();
    }

    private void newData(String time,int i) {
        SelfMatchInfo info = new SelfMatchInfo();
        info.day = time;
        List<MatchInnerInfo.ListBean> data = new ArrayList<>();
        data.add(list.get(i));
        info.data = data;
        mListData.add(info);
    }
}
