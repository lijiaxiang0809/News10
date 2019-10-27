package com.teach.news10.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.teach.news10.Frame.BaseActivity;
import com.teach.news10.Frame.BaseMvpActivity;
import com.teach.news10.R;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.HomeModel;
import com.teach.news10.presenter.OfflinePushRegistePresenter;
import com.teach.news10.utils.NormalConfig;
import com.tencent.qcloud.tim.uikit.component.picture.imageEngine.impl.GlideEngine;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.teach.news10.utils.NormalConfig.ISFIRST;
import static com.teach.news10.utils.NormalConfig.IS_WIFI_PLAY;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_image)
    ImageView splashImage;
    @BindView(R.id.splash_view)
    RelativeLayout splashView;
    private boolean mIsFirst;
    private boolean mIsWifiPlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GlideEngine.loadCornerImage(splashImage,R.drawable.splash_bg,null,0);
        new OfflinePushRegistePresenter().registerHwAndVivo(this);
        setAnimation();
    }

    private void setAnimation() {
        AlphaAnimation mAa = new AlphaAnimation(0, 1);
        mAa.setDuration(3000);
        mAa.setFillAfter(true);
        AnimationSet mAs = new AnimationSet(true);
        mAs.addAnimation(mAa);
        splashView.startAnimation(mAs);
        mAs.setAnimationListener(mAnimationListener);
    }

    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            mIsFirst = SharedPrefrenceUtils.getBoolean(SplashActivity.this, ISFIRST, true);
            if (mIsFirst)SharedPrefrenceUtils.saveBoolean(SplashActivity.this,ISFIRST,false);
            mIsWifiPlay = SharedPrefrenceUtils.getBoolean(SplashActivity.this,IS_WIFI_PLAY,true);
            mApplication.mPlayInWifi = mIsWifiPlay;
            String token = SharedPrefrenceUtils.getString(SplashActivity.this, NormalConfig.TOKEN,"");
            mApplication.mToken = token;
            String userPhoto = SharedPrefrenceUtils.getString(SplashActivity.this,NormalConfig.USER_PHOTO,"");
            mApplication.mUserPhoto = userPhoto;
            String userNick = SharedPrefrenceUtils.getString(SplashActivity.this,NormalConfig.USER_NICK,"");
            mApplication.mUserNick = userNick;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (mIsFirst){
                startActivity(new Intent(SplashActivity.this,FavlistsActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
            SplashActivity.this.finish();
        }
    };
}
