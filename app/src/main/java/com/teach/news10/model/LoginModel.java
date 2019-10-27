package com.teach.news10.model;

import com.teach.news10.Frame.ApiConfig;
import com.teach.news10.Frame.ICommonModel;
import com.teach.news10.Frame.ICommonView;
import com.teach.news10.Frame.NetManager;
import com.teach.news10.bean.LoginInfo;
import com.teach.news10.bean.VerifyCodeInfo;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by 任小龙 on 2019/7/25.
 */
public class LoginModel implements ICommonModel {

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi) {
            case ApiConfig.GET_SMS:
                long millis = System.currentTimeMillis();
                String s = String.valueOf(millis);
                s = s.substring(s.length() - 5, s.length() - 1);
                VerifyCodeInfo info = new VerifyCodeInfo();
                info.success = true;
                info.verify_token = s;
                try {
                    Thread.sleep(500l);
                } catch (InterruptedException pE) {
                    pE.printStackTrace();
                }
                view.onResponse(whichApi, info);
                break;
            case ApiConfig.LOGIN_ACC:
                String path = (String) t[0];
                LoginInfo loginInfo = new LoginInfo(path, System.currentTimeMillis() + "", "登录成功","ljx");
                try {
                    Thread.sleep(500l);
                } catch (InterruptedException pE) {
                    pE.printStackTrace();
                }
                view.onResponse(whichApi,loginInfo);
                break;
            case ApiConfig.UPLOAD_IMAGE:
                /**
                 json : application/json
                 xml : application/xml
                 png : image/png
                 jpg : image/jpeg
                 gif : image/gif
                 所有类型：image/*
                 */
                File file = new File((String) t[0]);
                RequestBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("key", "1806b")
                        .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file ))
                        .build();
                manager.method(manager.getNetService("http://yun918.cn/").uploadImage(body),view,whichApi);
                break;
        }
    }
}
