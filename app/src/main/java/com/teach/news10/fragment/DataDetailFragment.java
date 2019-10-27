package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.bean.DataTabOutInfo;
import com.teach.news10.bean.TabFatherInfo;
import com.teach.news10.design.MyTabView;
import com.teach.news10.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.design.WheelViewPopup;

public class DataDetailFragment extends BaseMvpFragment<DataModel> implements MyTabView.OnTabClick {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.arrow_image)
    ImageView arrowImage;
    @BindView(R.id.tab_view)
    MyTabView tabView;

    private TabFatherInfo.DataTabsBean mParam1;
    private List<DataTabOutInfo> mList;
    private FragmentManager mManager;
    private List<Fragment> mFragments = new ArrayList<>();
    private int SEASON_INDEX = 0;
    private WheelViewPopup mWheelViewPopup;
    private List<String> mWheelData = new ArrayList<>();
    private DataTabSortCUBAFragment mCubaSortFragment;
    private MatchProgressFragment mMatchProgressCUBA;

    public static DataDetailFragment newInstance(TabFatherInfo.DataTabsBean param1) {
        DataDetailFragment fragment = new DataDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public int getLayoutId() {
        mManager = getChildFragmentManager();
        return R.layout.fragment_data_detail;
    }

    @Override
    public void initView() {
        tabView.setOnTabClickListener(this);
        titleText.setText(mParam1.getSeason().getTitle());
        if (!mParam1.getLabel().equals("CUBA")) {
            mWheelViewPopup = new WheelViewPopup(getActivity());
            mWheelViewPopup.mConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWheelViewPopup.dismiss();
                    SEASON_INDEX = mWheelViewPopup.getSelectedPos();
                    getSeasonData();
                }
            });
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mParam1 == null) {
            showToast("fragment加载数据时尚未获取到activity传过来的参数");
        }
        if (isVisibleToUser && mParam1 != null && mList == null) {
            if (mParam1.getLabel().equals("CUBA") && mCubaSortFragment == null && mMatchProgressCUBA == null) {
                List<TabFatherInfo.DataTabsBean.SubTabsBean> sub_tabs = mParam1.getSub_tabs();
                setCubaData(sub_tabs);
            } else {
                String url = mParam1.getSeason().getUrl();
                mPresenter.getData(ApiConfig.DATA_FOUR_TAB_OUT, url);
            }
        }
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
            case ApiConfig.DATA_FOUR_TAB_OUT:
                mList = (List<DataTabOutInfo>) t[0];
                if (mList == null || mList.size() == 0) return;
                for (int i = 0; i < mList.size(); i++) {
                    mWheelData.add(mList.get(i).getSeason_name());
                }
                mWheelViewPopup.setWheelData(mWheelData);
                getSeasonData();
                break;
        }
    }

    private void setCubaData(List<TabFatherInfo.DataTabsBean.SubTabsBean> pSub_tabs) {
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < pSub_tabs.size(); i++) {
            titleList.add(pSub_tabs.get(i).getTitle());
        }
        tabView.setTab(titleList);
        if (mFragments.size() != 0) mFragments.clear();
        for (int i = 0; i < pSub_tabs.size(); i++) {
            if (pSub_tabs.get(i).getTitle().equals("排名")) {
                mCubaSortFragment = DataTabSortCUBAFragment.newInstance(pSub_tabs.get(i).getTitle(), pSub_tabs.get(i).getUrl(), mParam1.getLabel());
                mFragments.add(mCubaSortFragment);
            } else if (pSub_tabs.get(i).getTitle().equals("赛程")) {
                mMatchProgressCUBA = MatchProgressFragment.newInstance(pSub_tabs.get(i).getTitle(), pSub_tabs.get(i).getUrl());
                mFragments.add(mMatchProgressCUBA);
            }
        }
        mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(0)).commit();
    }

    private void getSeasonData() {
        mWheelViewPopup.setDefaultSelected(SEASON_INDEX);
        tabView.resetView();
        titleText.setText(mList.get(SEASON_INDEX).getSeason_name());
        List<DataTabOutInfo.SubTabsBean> sub_tabs = mList.get(SEASON_INDEX).getSub_tabs();
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < sub_tabs.size(); i++) {
            titleList.add(sub_tabs.get(i).getTitle());
        }
        tabView.setTab(titleList);
        if (mFragments.size() != 0) mFragments.clear();
        for (int i = 0; i < sub_tabs.size(); i++) {
            if (sub_tabs.get(i).getTitle().equals("排名")) {
                DataTabInnerFragment dataTabInnerFragment = DataTabInnerFragment.newInstance(sub_tabs.get(i).getTitle(), sub_tabs.get(i).getUrl(), mParam1.getLabel());
                mFragments.add(dataTabInnerFragment);
            } else if (sub_tabs.get(i).getTitle().equals("赛程")) {
                MatchProgressFragment fragment = MatchProgressFragment.newInstance(sub_tabs.get(i).getTitle(), sub_tabs.get(i).getUrl());
                mFragments.add(fragment);
            } else {
                TeamDataFragment teamDataFragment = TeamDataFragment.newInstance(sub_tabs.get(i).getTitle(), sub_tabs.get(i).getUrl());
                mFragments.add(teamDataFragment);
            }
        }
        mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(0)).commit();
    }

    @OnClick(R.id.title_text)
    public void onViewClicked() {
        if (mParam1.getLabel().equals("CUBA")) {
            showToast(getString(R.string.only_one_season));
            return;
        }
        if (mWheelViewPopup != null) mWheelViewPopup.showPopupWindow();
    }

    @Override
    public void onFirstClick() {
        if (mFragments.size() > 0)
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(0)).commit();
    }

    @Override
    public void onSecondClick() {
        if (mFragments.size() == 4) {
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(1)).commit();
        }
    }

    @Override
    public void onThirdClick() {
        if (mFragments.size() == 4) {
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(2)).commit();
        } else if (mFragments.size() == 3) {
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(1)).commit();
        }
    }

    @Override
    public void onFourthClick() {
        if (mFragments.size() == 4) {
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(3)).commit();
        } else if (mFragments.size() == 3) {
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(2)).commit();
        } else if (mFragments.size() == 2) {
            mManager.beginTransaction().replace(R.id.frame_layout, mFragments.get(1)).commit();
        }
    }
}
