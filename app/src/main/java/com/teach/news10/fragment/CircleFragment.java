package com.teach.news10.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpFragment;
import com.teach.news10.R;
import com.teach.news10.adapter.MyFragmentAdapter;
import com.teach.news10.bean.CircleInfo;
import com.teach.news10.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CircleFragment extends BaseMvpFragment<HomeModel> {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.bottom_right_corner_text)
    TextView cornerText;
    List<String> mTitleList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();
    static CircleFragment fragment = null;
    private MyFragmentAdapter mFragmentAdapter;

    public static CircleFragment newInstance() {
        if (fragment == null) fragment = new CircleFragment();
        return fragment;
    }

    private String circleTab = "https://bkbapi.dqdgame.com/group/app/thread/tabs";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_page;
    }

    @Override
    public void initView() {
        cornerText.setText("发帖");
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        viewPager.setAdapter(mFragmentAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setTabSpaceEqual(true);
        tabLayout.setCurrentTab(1);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.CIRCLE_INFO, circleTab);
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
            case ApiConfig.CIRCLE_INFO:
                CircleInfo info = (CircleInfo) t[0];
                List<CircleInfo.DataBean.ListBean> list = info.getData().getList();
                if (list.size() != 0 && mTitleList.size() != 0){
                    mFragmentList.clear();
                    mTitleList.clear();
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getType().equals("fav"))
                        mFragmentList.add(CircleFavFragment.newInstance(list.get(i).getApi()));
                    else if (list.get(i).getType().equals("normal"))
                        mFragmentList.add(CircleRecomondVideoFragment.newInstance(list.get(i).getApi(),list.get(i).getLabel()));
                    else mFragmentList.add(CircleTopicFragment.newInstance(list.get(i).getApi()));
                    mTitleList.add(list.get(i).getLabel());
                }
                tabLayout.notifyDataSetChanged();
                mFragmentAdapter.notifyDataSetChanged();
                tabLayout.setCurrentTab(1);
                break;
        }
    }

    @OnClick(R.id.bottom_right_corner_rl)
    public void onViewClicked() {
    }
}
