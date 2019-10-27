package com.teach.news10.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.NetHeaders;
import com.teach.news10.Frame.OnRecyclerItemClick;
import com.teach.news10.R;
import com.teach.news10.adapter.SlideAdapter;
import com.teach.news10.bean.NetVersionInfo;
import com.teach.news10.bean.SlideInfo;
import com.teach.news10.bean.TabFatherInfo;
import com.teach.news10.design.MyBottomView;
import com.teach.news10.fragment.CircleFragment;
import com.teach.news10.fragment.DataFragment;
import com.teach.news10.fragment.FirstPageFragment;
import com.teach.news10.fragment.MatchFragment;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.NormalConfig;
import com.tencent.qcloud.tim.demo.main.MainActivity;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.DialogPopup;

public class HomeActivity extends BaseHomeActivity implements DrawerLayout.DrawerListener, MyBottomView.OnBottomClick, OnRecyclerItemClick, DialogPopup.DialogClickCallBack {
    private List<SlideInfo.ModulesBean> mList = new ArrayList<>();
    private SlideAdapter mAdapter;
    private final int FRISTPAGE = 1, MATCH = 2, CIRCLE = 3, DATA = 4;
    private FragmentManager mManager;
    public Fragment mFirstPageFragment, mCircleFragment, mMatchFragment, mDataFragment;
    private SlideInfo mInfo;
    private boolean mWhenComeIsDisConnected;
    public JCVideoPlayerStandard mVideoPlayer;
    private final int FAVOR_TEAM = 1;
    public TabFatherInfo mTabFatherInfo;
    private DialogPopup mDialogPopup;
    public boolean mSaveInstanceHasPerform;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        mAdapter = new SlideAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter.setItemClick(this);
        drawer.addDrawerListener(this);
        mBottomView.setBottomBg(Color.WHITE);
        mBottomView.setBottomTextSize(this, 10f);
        mBottomView.setOnBottomClickListener(this);
        mManager = getSupportFragmentManager();
        showFragment(FRISTPAGE);
    }

    @Override
    public void initData() {
        loginIm(1);
        getData();
        findNetVersion();
    }

    private void findNetVersion() {
        Rx2AndroidNetworking.get("https://www.cpzs.org/admin/apkversion/getxml/17")
                .build()
                .getStringObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String pS) throws Exception {
                        NetVersionInfo info = parseVersion(pS);
                        String versionName = NetHeaders.getVersionName();
                        checkVersion(info, versionName);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable pThrowable) throws Exception {
                        showLog(pThrowable.getMessage());
                    }
                });
    }

    private void checkVersion(NetVersionInfo in, String localVersion) {
        if (!in.version.equals(localVersion)) {
            mDialogPopup = new DialogPopup(this, !in.isforceUpdate);
            mDialogPopup.setDialogClickCallBack(this);
            if (in.isforceUpdate) {
//               mDialogPopup.setOutSideTouchUnDisMiss();
            }
            mDialogPopup.setContent("发现新版本，是否升级");
            mDialogPopup.showPopupWindow();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSaveInstanceHasPerform = true;
        showLog("这是个什么玩意儿");
    }

    private NetVersionInfo parseVersion(String pS) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("updateinfo", NetVersionInfo.class);
        xStream.alias("version", NetVersionInfo.class);
        xStream.alias("url", NetVersionInfo.class);
        xStream.alias("description", NetVersionInfo.class);
        xStream.alias("isforceUpdate", NetVersionInfo.class);
        NetVersionInfo info = (NetVersionInfo) xStream.fromXML(pS);
        return info;
    }


    private void getData() {
        mPresenter.getData(ApiConfig.SLIDE_INFO);
        mTabFatherInfo = SharedPrefrenceUtils.getObject(this, NormalConfig.TAB_FATHER);
        if (mTabFatherInfo == null)
            mPresenter.getData(ApiConfig.MATCH_AND_DATA_TAB_DATA);
    }

    @Override
    public void onNetConnected() {
        super.onNetConnected();
        if (mInfo == null && mWhenComeIsDisConnected) {
            EventBus.getDefault().post(NormalConfig.NET_HAS_CONNECTED);
            getData();
            mWhenComeIsDisConnected = false;
        }
    }

    @Override
    public void onNetDisConnected() {
        super.onNetDisConnected();
        mWhenComeIsDisConnected = true;
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
            case ApiConfig.SLIDE_INFO:
                mInfo = (SlideInfo) t[0];
                GlideEngine.loadImage(navHeaderImage,mInfo.get_$Config191().getSidebar_bg_image());
                if (mInfo.getModules() != null) mList.addAll(mInfo.getModules());
                mAdapter.notifyDataSetChanged();
                break;
            case ApiConfig.MATCH_AND_DATA_TAB_DATA:
                mTabFatherInfo = (TabFatherInfo) t[0];
                SharedPrefrenceUtils.putObject(this, NormalConfig.TAB_FATHER, mTabFatherInfo);
                break;
            case ApiConfig.FIND_NET_VERSION:
                String result = (String) t[0];
                showLog(result);
                break;
        }
    }

    private long touchTime = 0;

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long currentTime = System.currentTimeMillis();
            if (currentTime - touchTime > 2000) {
                showToast(getString(R.string.press_again_to_exit_process));
                touchTime = currentTime;
            } else {
                super.onBackPressed();
            }
        }
    }

    @OnClick({R.id.round_image, R.id.login_now, R.id.notify_item, R.id.collected_item, R.id.system_message_item, R.id.reverse_item, R.id.setting_item})
    public void onViewClicked(View view) {
//        drawer.closeDrawers();
        switch (view.getId()) {
            case R.id.login_now:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.notify_item:
                if (mApplication.mImIsLogin)
                    startActivity(new Intent(this, MainActivity.class));
                else {
                    loginIm(2);
                }
                break;
            case R.id.collected_item:
                showToast(getString(R.string.collected_history));
                break;
            case R.id.system_message_item:
                showToast(getString(R.string.system_message));
                break;
            case R.id.reverse_item:
                showToast(getString(R.string.reverse_tell));
                break;
            case R.id.setting_item:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.round_image:
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                else {
                    drawer.openDrawer(GravityCompat.START);
                }
                break;
        }
    }

    private int currentIndex = 5;

    private void showFragment(int index) {
        if (currentIndex == index) return;
        else currentIndex = index;
        FragmentTransaction fragmentTransaction = mManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (index) {
            case FRISTPAGE:
                if (mFirstPageFragment != null) {
                    fragmentTransaction.show(mFirstPageFragment);
                } else {
                    mFirstPageFragment = FirstPageFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mFirstPageFragment);
                }
                break;
            case MATCH:
                if (mMatchFragment != null) fragmentTransaction.show(mMatchFragment);
                else {
                    mMatchFragment = MatchFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mMatchFragment);
                }
                break;
            case CIRCLE:
                if (mCircleFragment != null) fragmentTransaction.show(mCircleFragment);
                else {
                    mCircleFragment = CircleFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mCircleFragment);
                }
                break;
            case DATA:
                if (mDataFragment != null) fragmentTransaction.show(mDataFragment);
                else {
                    mDataFragment = DataFragment.newInstance();
                    fragmentTransaction.add(R.id.frame_layout, mDataFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction pFragmentTransaction) {
        if (mFirstPageFragment != null) pFragmentTransaction.hide(mFirstPageFragment);
        if (mCircleFragment != null) pFragmentTransaction.hide(mCircleFragment);
        if (mDataFragment != null) pFragmentTransaction.hide(mDataFragment);
        if (mMatchFragment != null) pFragmentTransaction.hide(mMatchFragment);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        mScrollView.scrollTo(0, 0);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onFirstClick() {
        showFragment(FRISTPAGE);
    }

    @Override
    public void onSecondClick() {
        showFragment(MATCH);
    }

    @Override
    public void onThirdClick() {
        showFragment(CIRCLE);
    }

    @Override
    public void onFourthClick() {
        showFragment(DATA);
    }

    @Override
    public void onFifthClick() {

    }

    public void releaseVideo() {
        if (mVideoPlayer != null) {
            if (mVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
//                mApplication.mVideoPlayer.startButton.performClick();
                JCMediaManager.instance().mediaPlayer.pause();
                mVideoPlayer.currentState = JCVideoPlayer.CURRENT_STATE_PAUSE;
            } else if (mVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PREPARING) {
                JCVideoPlayer.releaseAllVideos();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseVideo();
        if (mNetStatusBroadCast != null) unregisterReceiver(mNetStatusBroadCast);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSaveInstanceHasPerform = false;
        if (mVideoPlayer != null && mVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PAUSE) {
            mVideoPlayer.startVideo();
        }
        registerNetWorkStatus();
        if (!TextUtils.isEmpty(mApplication.mToken)) {
            navImage.setVisibility(View.VISIBLE);
            loginNow.setVisibility(View.GONE);
            Glide.with(this).load(mApplication.mUserPhoto).into(navImage);
            Glide.with(this).load(mApplication.mUserPhoto).into(roundImage);
        } else {
            navImage.setVisibility(View.GONE);
            loginNow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(int pos) {
        switch (pos) {
            case FAVOR_TEAM:
                startActivity(new Intent(this, FavlistsActivity.class));
                break;
        }
    }

    @Override
    public void okClick() {
        showToast("开始下载");
    }

    @Override
    public void cancelClick() {
        if (mDialogPopup != null) mDialogPopup.dismiss();
    }
}
