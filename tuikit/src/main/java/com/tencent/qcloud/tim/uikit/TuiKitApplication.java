package com.tencent.qcloud.tim.uikit;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.imsdk.TIMUserProfile;

/**
 * Created by 任小龙 on 2019/7/31.
 */
public class TuiKitApplication extends MultiDexApplication {
    private static TuiKitApplication instance;
    public String mUserPhoto = "";
    public String mUserNick = "";

    public TIMUserProfile getSelfInfo() {
        return mSelfInfo;
    }

    public void setSelfInfo(TIMUserProfile pSelfInfo) {
        mSelfInfo = pSelfInfo;
    }

    private TIMUserProfile mSelfInfo;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static TuiKitApplication instance() {
        return instance;
    }
}
