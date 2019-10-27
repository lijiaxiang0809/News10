package com.teach.news10.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 任小龙 on 2019/4/17.
 */

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitle;
    private FragmentManager fragmentManager;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitle) {
        super(fm);
        this.fragmentManager = fm;
        this.mFragments = mFragments;
        this.mTitle = mTitle;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = mFragments.get(position);
        if (!fragment.isAdded()) { // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commit();
            fragmentManager.executePendingTransactions();
        }
        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView()); // 为viewpager增加布局
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments != null ? mFragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle == null ? "" : mTitle.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mFragments != null && mFragments.get(position).getView() != null)
            container.removeView(mFragments.get(position).getView());
//        container.removeAllViews();
    }

    /*
     * getItemPosition() 函数，返回 POSITION_NONE，fragmentpageradapter和fragmentstatepageradapter的解决方案都需要的。
     * 二者不同之处在于，FragmentStatePagerAdapter 在会在因 POSITION_NONE 触发调用的 destroyItem() 中真正的释放资源，
     * 重新建立一个新的 Fragment；而 FragmentPagerAdapter 仅仅会在 destroyItem() 中 detach 这个 Fragment，
     * 在 instantiateItem() 时会使用旧的 Fragment，并触发 attach，因此没有释放资源及重建的过程。
     * */
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
