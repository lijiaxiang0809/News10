package com.teach.news10.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.adapter.MyFragmentAdapter;
import com.teach.news10.bean.TabFatherInfo;
import com.teach.news10.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MatchFragment extends BaseMvpFragment<HomeModel> {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    @BindView(R.id.refresh_image)
    ImageView refreshImage;
    @BindView(R.id.bottom_right_corner_rl)
    RelativeLayout bottomRightCornerRl;
    private MyFragmentAdapter mFragmentAdapter;
    static MatchFragment fragment;
    public String mLive_index;

    public static MatchFragment newInstance() {
        if (fragment == null) fragment = new MatchFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_page;
    }

    @Override
    public void initView() {
        bottomRightCornerRl.setVisibility(View.VISIBLE);
        refreshImage.setVisibility(View.VISIBLE);
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        viewPager.setAdapter(mFragmentAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setTabSpaceEqual(false);
        tabLayout.setCurrentTab(1);
    }

    @Override
    public void initData() {
        HomeActivity a = (HomeActivity) getActivity();
        if (a.mTabFatherInfo != null && a.mTabFatherInfo.getLive_tabs() != null && a.mTabFatherInfo.getLive_tabs().size() != 0) {
            setUpMatchFragment(a.mTabFatherInfo.getLive_tabs());
            mLive_index = a.mTabFatherInfo.getLive_index();
        } else {
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
        showLog(whichApi + ":" + e.getMessage() != null ? e.getMessage() : "net error————————————");
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hideLoadingDialog();
        switch (whichApi) {
            case ApiConfig.MATCH_AND_DATA_TAB_DATA:
                TabFatherInfo fatherInfo = (TabFatherInfo) t[0];
                mLive_index = fatherInfo.getLive_index();
                List<TabFatherInfo.LiveTabsBean> live_tabs = fatherInfo.getLive_tabs();
                setUpMatchFragment(live_tabs);
                break;
        }
    }

    private void setUpMatchFragment(List<TabFatherInfo.LiveTabsBean> live_tabs) {
        for (int i = 0; i < live_tabs.size(); i++) {
            mTitleList.add(live_tabs.get(i).getLabel());
            mFragmentList.add(MatchInnerFragment.newInstance(live_tabs.get(i).getApi(), live_tabs.get(i).getLabel()));
        }
        mFragmentAdapter.notifyDataSetChanged();
        tabLayout.notifyDataSetChanged();
        tabLayout.setCurrentTab(1);
    }

    @OnClick(R.id.bottom_right_corner_rl)
    public void onViewClicked() {
    }
}
