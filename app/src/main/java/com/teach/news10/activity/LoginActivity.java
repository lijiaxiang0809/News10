package com.teach.news10.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.rxjava.bean.ImageInfo;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.BaseMvpActivity;
import com.teach.news10.R;
import com.teach.news10.bean.LoginInfo;
import com.teach.news10.bean.VerifyCodeInfo;
import com.teach.news10.design.RoundImage;
import com.teach.news10.design.SmsVerifyView;
import com.teach.news10.local_utils.SharedPrefrenceUtils;
import com.teach.news10.model.LoginModel;
import com.teach.news10.utils.NormalConfig;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendAllowType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.demo.ChatApplication;
import com.tencent.qcloud.tim.demo.signature.GenerateTestUserSig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.design.SlideFromBottomPopup;

public class LoginActivity extends BaseMvpActivity<LoginModel> implements TakePhoto.TakeResultListener, SmsVerifyView.SmsVerifyCallback, SlideFromBottomPopup.BottomPopClick {

    @BindView(R.id.sms_verify_view)
    SmsVerifyView mView;
    @BindView(R.id.avarter)
    RoundImage mImage;
    private String photoPath = "";
    private SlideFromBottomPopup mPop;
    private TakePhotoImpl mTakePhoto;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mView.setSmsVerifyCallback(this);
        //初始化底部弹出拍照的pop
        mPop = new SlideFromBottomPopup(this);
        mPop.setLineText(getString(R.string.photo), getString(R.string.camera), getString(R.string.cancel));
        mPop.setBottomClickListener(this);
    }

    @Override
    public void initData() {
        getPermission();
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel();
    }


    @Override
    public void onError(int whichApi, Throwable e) {
        mView.reset();
    }

    private void getPermission() {
        XXPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求 6.0 悬浮窗权限 8.0 请求安装权限
                .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE) //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (denied.size() != 0) showToast("拒绝权限影响您正常使用");
                    }
                });
        //XXPermissions.gotoPermissionSettings(this);//跳转到权限设置页面
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        hideLoadingDialog();
        switch (whichApi) {
            case ApiConfig.GET_SMS:
                VerifyCodeInfo info = (VerifyCodeInfo) t[0];
                if (info.success) showToast("短信发送成功");
                mView.setVerifyCode(info.verify_token);
                break;
            case ApiConfig.LOGIN_ACC:
                LoginInfo loginInfo = (LoginInfo) t[0];
                showToast(loginInfo.msg);
                mApplication.mToken = loginInfo.token;
                mApplication.mUserNick = loginInfo.nick;
                SharedPrefrenceUtils.saveString(this, NormalConfig.TOKEN, loginInfo.token);
                SharedPrefrenceUtils.saveString(this, NormalConfig.USER_NICK, loginInfo.nick);
                loginIm();
                break;
            case ApiConfig.UPLOAD_IMAGE:
                ImageInfo imageInfo = (ImageInfo) t[0];
                if (imageInfo.code == 200) {
                    showToast("图片上传成功");
                    photoPath = imageInfo.data.url;
                    mApplication.mUserPhoto = photoPath;
                    SharedPrefrenceUtils.saveString(this, NormalConfig.USER_PHOTO, photoPath);
                }
                break;
        }
    }

    public void loginIm() {
        String account = mApplication.mUserNick;
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
                sycInfoToIm();
            }
        });
    }

    private void sycInfoToIm() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK, "Alan");
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_SELFSIGNATURE, "渣渣辉");
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_ALLOWTYPE, TIMFriendAllowType.TIM_FRIEND_ALLOW_ANY);
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_FACEURL,mApplication.mUserPhoto);
        TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                ToastUtil.toastShortMessage("modifySelfProfile err code = " + i + ", desc = " + s);
            }

            @Override
            public void onSuccess() {
                ToastUtil.toastShortMessage("信息同步成功");
                getSelfInfo();
            }
        });
    }

    public void getSelfInfo(){
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String pS) {
                ToastUtil.toastShortMessage("获取im个人信息失败" + i + ", desc = " + pS);
            }

            @Override
            public void onSuccess(TIMUserProfile pTIMUserProfile) {
                mApplication.setSelfInfo(pTIMUserProfile);
                finish();
            }
        });
    }

    @OnClick(R.id.weichat_login)
    public void onViewClicked() {
        showToast("暂未开放");
    }

    /**
     * 获取短信验证码
     */
    @Override
    public void smsCodeSend() {
        showLoadingDialog();
        mPresenter.getData(ApiConfig.GET_SMS);
    }

    @Override
    public void countryCodeOpen() {
        mView.setDefaultData("15100133517");
    }

    public void upload(View pView) {
        if (mPop != null) mPop.showPopupWindow();
    }

    public void login(View view) {
        if (TextUtils.isEmpty(mView.getVerifyCode())) {
            showToast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(photoPath)) {
            showToast("请上传头像");
            return;
        }
        mPresenter.getData(ApiConfig.LOGIN_ACC, photoPath);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        showLog(event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            showLog("enter click");
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 点击相册选图
     */
    @Override
    public void clickTop() {
        mTakePhoto = new TakePhotoImpl(this, this);
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
        mTakePhoto.onPickFromGalleryWithCrop(getUri(), getOption());
//        mTakePhoto.onPickFromGallery();
        mPop.dismiss();
    }

    /**
     * 点击拍照
     */
    @Override
    public void clickCenter() {
        mTakePhoto = new TakePhotoImpl(this, this);
        //压缩图片
        mTakePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
        //从相册获取并裁剪
        mTakePhoto.onPickFromCaptureWithCrop(getUri(), getOption());
//        mTakePhoto.onPickFromCapture(getUri());
        mPop.dismiss();
    }

    /**
     * 取消popupwindow
     */
    @Override
    public void clickBottom() {
        mPop.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTakePhoto.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private CropOptions getOption() {
        return new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
    }

    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/news10/" + System.currentTimeMillis() + ".png");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }

    /**
     * 获取拍照或相册处理过的图片路劲
     * @param result
     */
    @Override
    public void takeSuccess(TResult result) {
        String path = result.getImage().getCompressPath() != null ? result.getImage().getCompressPath() : result.getImage().getOriginalPath();//先设置到列表中
        if (!TextUtils.isEmpty(path)) {
            showLoadingDialog();
            mPresenter.getData(ApiConfig.UPLOAD_IMAGE, path);
            Glide.with(this).load(path).into(mImage);
        }
    }

    /**
     * 拍照或获取相册图片失败
     * @param result
     * @param msg
     */
    @Override
    public void takeFail(TResult result, String msg) {
        showLog(msg);
    }

    /**
     * 取消获取图片
     */
    @Override
    public void takeCancel() {
        showLog("cancel_get_image");
    }
}
