package com.teach.news10.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpActivity;
import com.teach.news10.R;
import com.teach.news10.adapter.MyFragmentAdapter;
import com.teach.news10.bean.FavTeamEntity;
import com.teach.news10.design.CommonTitle;
import com.teach.news10.design.RoundImage;
import com.teach.news10.fragment.FaverateFragment;
import com.teach.news10.local_utils.statusbar.StatusBarCompat;
import com.teach.news10.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FavlistsActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.title)
    public CommonTitle title;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    List<Fragment> mFragmentList = new ArrayList<>();
    private MyFragmentAdapter mFragmentAdapter;
    List<String> mTitleList = new ArrayList<>();
    private ArrayList<String> mSelectedTeams;
    private ArrayList<FavTeamEntity> mTeam_list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_favlists;
    }

    @Override
    public void initView() {
        mFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        viewPager.setAdapter(mFragmentAdapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.setCurrentTab(0);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.FAVERATE_LIST);
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showLog("关注数据加载失败："+whichApi);
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hideLoadingDialog();
        switch (whichApi) {
            case ApiConfig.FAVERATE_LIST:
                FavTeamEntity entity = (FavTeamEntity) t[0];
                mSelectedTeams = entity.getSelected_teams() != null ? entity.getSelected_teams() : new ArrayList<String>();
                mTeam_list = entity.getTeam_list();
                for (int i = 0;i < mTeam_list.size();i++){
                    mTitleList.add(mTeam_list.get(i).getTitle());
                    mFragmentList.add(FaverateFragment.newInstance(mTeam_list.get(i).getList(),mSelectedTeams));
                }
                tabLayout.notifyDataSetChanged();
                mFragmentAdapter.notifyDataSetChanged();
                break;
        }
    }
}
