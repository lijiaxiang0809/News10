package com.teach.news10.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.adapter.MyFragmentAdapter;
import com.teach.news10.bean.TabFatherInfo;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.NormalConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DataFragment extends BaseMvpFragment<HomeModel> {

    public List<TabFatherInfo.LiveTabsBean> mLive_tabs;
    static DataFragment fragment;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.bottom_right_corner_text)
    public TextView bottomRightCornerText;
    @BindView(R.id.bottom_right_corner_rl)
    RelativeLayout bottomRightCornerRl;
    private MyFragmentAdapter mFragmentAdapter;
    public static DataFragment newInstance() {
        if (fragment == null) {
            fragment = new DataFragment();
        }
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_page;
    }

    @Override
    public void initView() {
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        viewPager.setAdapter(mFragmentAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setTabSpaceEqual(false);
        tabLayout.setCurrentTab(0);
        bottomRightCornerRl.setVisibility(View.VISIBLE);
        bottomRightCornerText.setVisibility(View.VISIBLE);
    }


    @Override
    public void initData() {
        HomeActivity activity = (HomeActivity) getActivity();
        if (activity.mTabFatherInfo != null && activity.mTabFatherInfo.getData_tabs() != null && activity.mTabFatherInfo.getData_tabs().size() != 0)
            setUpChildFragment(activity.mTabFatherInfo.getData_tabs());
        else {
            showLoadingDialog();
            mPresenter.getData(ApiConfig.MATCH_AND_DATA_TAB_DATA);
        }
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
        switch (whichApi) {
            case ApiConfig.MATCH_AND_DATA_TAB_DATA:
                TabFatherInfo fatherInfo = (TabFatherInfo) t[0];
                List<TabFatherInfo.DataTabsBean> data_tabs = fatherInfo.getData_tabs();
                mLive_tabs = fatherInfo.getLive_tabs();
                setUpChildFragment(data_tabs);
                SharedPrefrenceUtils.putObject(getContext(), NormalConfig.TAB_FATHER, fatherInfo);
                break;
        }
    }
    private void setUpChildFragment(List<TabFatherInfo.DataTabsBean> data_tabs) {
        for (int i = 0; i < data_tabs.size(); i++) {
            mTitleList.add(data_tabs.get(i).getLabel());
            mFragmentList.add(DataDetailFragment.newInstance(data_tabs.get(i)));
        }
        mFragmentAdapter.notifyDataSetChanged();
        tabLayout.notifyDataSetChanged();
    }

    @OnClick(R.id.bottom_right_corner_rl)
    public void onViewClicked() {
    }

}
