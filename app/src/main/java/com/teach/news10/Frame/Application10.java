package com.teach.news10.Frame;

import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.teach.news10.local_utils.DeviceUuidFactory;
import com.tencent.qcloud.tim.demo.ChatApplication;

import java.util.UUID;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class Application10 extends ChatApplication {

    public static Application10 sApplication;
    public UUID mUuid;
    public boolean mPlayInWifi;
    public String mToken = "";
    public boolean mImIsLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        mUuid = DeviceUuidFactory.getInstance(getApplicationContext()).getDeviceUuid();
        setupLeakCanary();
        Log.e("uuid:",mUuid+"\n"+mUuid.toString());
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static Context getAppContext(){
        return sApplication.getApplicationContext();
    }

    public static Application10 getApplication() {
        return sApplication;
    }
}
