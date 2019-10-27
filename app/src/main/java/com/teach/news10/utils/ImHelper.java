package com.teach.news10.utils;

import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.demo.ChatApplication;
import com.tencent.qcloud.tim.uikit.TuiKitApplication;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

/**
 * Created by 任小龙 on 2019/7/30.
 */
public class ImHelper {
    public static void getSelfInfo(){
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String pS) {
                ToastUtil.toastShortMessage("获取im个人信息失败" + i + ", desc = " + pS);
            }

            @Override
            public void onSuccess(TIMUserProfile pTIMUserProfile) {
                TuiKitApplication.instance().setSelfInfo(pTIMUserProfile);
            }
        });
    }
}
