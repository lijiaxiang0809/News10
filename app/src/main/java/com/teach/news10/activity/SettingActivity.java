package com.teach.news10.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.teach.news10.Frame.BaseActivity;
import com.teach.news10.R;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.utils.NormalConfig;

public class SettingActivity extends BaseActivity {

    private Switch mSwitchBtn;
    private boolean mPlayInWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mPlayInWifi = mApplication.mPlayInWifi;
        mSwitchBtn = findViewById(R.id.switch_button);
        mSwitchBtn.setChecked(mPlayInWifi);
        mSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPlayInWifi = isChecked;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mApplication.mPlayInWifi = mPlayInWifi;
        SharedPrefrenceUtils.saveBoolean(this, NormalConfig.IS_WIFI_PLAY,mPlayInWifi);
    }
}
