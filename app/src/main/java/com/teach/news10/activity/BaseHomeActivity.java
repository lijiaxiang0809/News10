package com.teach.news10.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.teach.news10.Frame.BaseMvpActivity;
import com.teach.news10.R;
import com.teach.news10.design.MyBottomView;
import com.teach.news10.design.RoundImage;
import com.teach.news10.model.HomeModel;
import com.teach.news10.utils.ImHelper;
import com.tencent.qcloud.tim.demo.main.MainActivity;
import com.tencent.qcloud.tim.demo.signature.GenerateTestUserSig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 任小龙 on 2019/7/29.
 */
public abstract class BaseHomeActivity extends BaseMvpActivity<HomeModel> {
    @BindView(R.id.round_image)
    RoundImage roundImage;
    @BindView(R.id.nav_view)
    RelativeLayout navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_header_image)
    ImageView navHeaderImage;
    @BindView(R.id.login_now)
    TextView loginNow;
    @BindView(R.id.notify_item)
    RelativeLayout notifyItem;
    @BindView(R.id.collected_item)
    RelativeLayout collectedItem;
    @BindView(R.id.system_message_item)
    RelativeLayout systemMessageItem;
    @BindView(R.id.reverse_item)
    RelativeLayout reverseItem;
    @BindView(R.id.setting_item)
    RelativeLayout settingItem;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.bottom_view)
    MyBottomView mBottomView;
    @BindView(R.id.home_rl)
    RelativeLayout rl;
    @BindView(R.id.has_login_image)
    RoundImage navImage;
    private static final int REQ_PERMISSION_CODE = 0x100;
    private String account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(this);
    }

    public void loginIm(final int index) {
        if (index == 1 && mApplication.mImIsLogin) return;
        if (TextUtils.isEmpty(mApplication.mUserNick)){
            showToast("用户未登录");
            return;
        }
        account = mApplication.mUserNick;
        String userSig = GenerateTestUserSig.genTestUserSig(account);
        TUIKit.login(account, userSig, new IUIKitCallBack() {
            @Override
            public void onError(String module, final int code, final String desc) {
                ToastUtil.toastLongMessage("登录失败, errCode = " + code + ", errInfo = " + desc);
                mApplication.mImIsLogin = false;
            }

            @Override
            public void onSuccess(Object data) {
                showToast("登陸成功");
                mApplication.mImIsLogin = true;
                ImHelper.getSelfInfo();
                if (index == 2) startActivity(new Intent(BaseHomeActivity.this, MainActivity.class));
            }
        });
    }

    public static boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                String[] permissionsArray = permissions.toArray(new String[1]);
                ActivityCompat.requestPermissions(activity,
                        permissionsArray,
                        REQ_PERMISSION_CODE);
                return false;
            }
        }

        return true;
    }

    /**
     * 系统请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.toastLongMessage("未全部授权，部分功能可能无法使用！");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
