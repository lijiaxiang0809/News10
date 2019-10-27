package com.teach.news10.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.activity.HomeActivity;
import com.teach.news10.adapter.MyFragmentAdapter;
import com.teach.news10.bean.FirstPageTitleInfo;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.NormalConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FirstPageFragment extends BaseMvpFragment<HomeModel> {
    static FirstPageFragment fragment;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    private MyFragmentAdapter mFragmentAdapter;
    private List<FirstPageTitleInfo.DataBean.ListBean> mList;
    private HomeActivity mActivity;
    private EventBus mEventBus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static FirstPageFragment newInstance() {
        if (fragment == null) fragment = new FirstPageFragment();
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
        if (tabLayout.getTabCount() > 1) tabLayout.setCurrentTab(1);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(String content) {
        if (content.equals(NormalConfig.NET_HAS_CONNECTED))getNetData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mEventBus.unregister(this);
        mFragmentAdapter = null;
    }

    @Override
    public void initData() {
        mActivity = (HomeActivity) getActivity();
        getNetData();
    }

    private void getNetData() {
        if (mFragmentList.size() != 0 && mTitleList.size() != 0) {
            if (mFragmentList.size() == mTitleList.size()) return;
            else {
                mTitleList.clear();
                mFragmentList.clear();
            }
        }
        FirstPageTitleInfo titleInfo = SharedPrefrenceUtils.getObject(getContext(), NormalConfig.TAB_FIRST_PAGE);
        if (titleInfo != null) setData(titleInfo);
        else
            mPresenter.getData(ApiConfig.FIRST_PAGE_TITLE_INFO);
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }


    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object... t) {
        switch (whichApi) {
            case ApiConfig.FIRST_PAGE_TITLE_INFO:
                FirstPageTitleInfo titleInfo = (FirstPageTitleInfo) t[0];
                setData(titleInfo);
                SharedPrefrenceUtils.putObject(getContext(), NormalConfig.TAB_FIRST_PAGE, titleInfo);
                break;
        }
    }

    private void setData(FirstPageTitleInfo pTitleInfo) {
        mList = pTitleInfo.getData().getList();
        for (int i = 0; i < mList.size(); i++) {
            FirstPageTitleInfo.DataBean.ListBean listBean = mList.get(i);
            if (listBean.getType().equals("hot"))
                mFragmentList.add(HotFragment.newInstance(listBean));
            else if (listBean.getType().equals("normal"))
                mFragmentList.add(NormalNewsFragment.newInstance(listBean));
            else if (listBean.getType().equals("video"))
                mFragmentList.add(VideoFragment.newInstance(listBean));
            else if (listBean.getType().equals("wall"))
                mFragmentList.add(InsNewsFragment.newInstance(listBean));
            else if (listBean.getType().equals("classification"))
                mFragmentList.add(ClassFicationFragment.newInstance(listBean));
            mTitleList.add(listBean.getLabel());
        }
        if (mFragmentAdapter != null && !mActivity.mSaveInstanceHasPerform){
            mFragmentAdapter.notifyDataSetChanged();
            tabLayout.notifyDataSetChanged();
            tabLayout.setCurrentTab(1);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) mActivity.releaseVideo();
    }
}
