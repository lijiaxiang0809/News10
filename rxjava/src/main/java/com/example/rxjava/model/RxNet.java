package com.example.rxjava.model;

import android.util.Log;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;
import com.example.rxjava.RetrofitManager;
import com.example.rxjava.bean.NewsInfo;
import com.example.rxjava.bean.ReadInfo;
import com.example.rxjava.bean.ImageInfo;
import com.example.rxjava.bean.UpdataResult;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 任小龙 on 2018/11/23.
 */

public class RxNet implements IRxModel {
    @Override
    public void doSomething(final IBackContent back, Object... objects) {
//       net1(back);
//       net2(back);
//        netGet(back);
    }

    //调用别人公司的上传图片接口，传单个图片
    private void netUploadByOtherServer() {
        String url = "http://yun918.cn/study/public/file_upload.php";
        Rx2AndroidNetworking.upload(url)
                .setContentType("multipart/form-data")
                .addMultipartFile("file", new File("文件路劲"))
                .addMultipartParameter("key", "1806b")
                .build()
                .getObjectObservable(ImageInfo.class)//RespData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageInfo>() {
                    @Override
                    public void accept(ImageInfo updataResult) throws Exception {
                        if (updataResult.code==200){
                        }
                    }
                });
    }

    private void netUpload(final IBackContent back) {
        String second = "users/uploadHeadImage";
        File file = new File("/storage/emulated/0/temp/1542960469720.jpg");
        Rx2AndroidNetworking.upload(RetrofitManager.DYTH_BASEURL + second)
                .setContentType("multipart/form-data")
                .addMultipartFile("headImageFile", file)
                .addMultipartParameter("userId", "e7d9914be4214263ad8e2a3e88e72263")
                .build()
                .getObjectObservable(UpdataResult.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UpdataResult>() {
                    @Override
                    public void accept(UpdataResult updataResult) throws Exception {
                        back.getResult(updataResult.code);
                    }
                });
    }

    private void netGet(final IBackContent back) {
        String url = "http://static.owspace.com/";
        Map<String, String> params = new HashMap<>();
        params.put("c", "api");
        params.put("a", "getList");
        params.put("page_id", "0");
        Rx2AndroidNetworking.get(url)
                .addQueryParameter(params)
                .build()
                .getObjectObservable(ReadInfo.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReadInfo>() {
                    @Override
                    public void accept(ReadInfo readInfo) throws Exception {
                        back.getResult(readInfo.getCode());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void net3(final IBackContent back, String s, Map<String, String> params) {
        Rx2AndroidNetworking.post(RetrofitManager.DYTH_BASEURL + s)
                .addBodyParameter(params)
                .build()
                .getObjectObservable(Object.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        back.getResult(o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void net2(final IBackContent back) {
        String s2 = "news/listNewsChannel";
        Rx2AndroidNetworking.post(RetrofitManager.DYTH_BASEURL + s2)
                .build()
                .getObjectObservable(NewsInfo.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsInfo>() {
                    @Override
                    public void accept(NewsInfo newsInfo) throws Exception {
                        back.getResult(newsInfo.getCode() + "success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        back.getResult(throwable.getMessage());
                    }
                });

    }

    private void jiangYu() {
        String url = "http://api.precard.taagoo.com/v1/login/send-sms";

        Map<String, String> params = new HashMap<>();
        params.put("sign", "sign");
        params.put("timestamp", "0");
        params.put("token", "token");
        String body = "{\"phone\":\"15100133517\" ,\"scenarios\": \"register\"}";

        JSONObject object = null;
        try {
            object = new JSONObject(body);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Rx2AndroidNetworking.post(url)
                .addQueryParameter(params)
                .addJSONObjectBody(object)
                .addHeaders("Content-Type", "application/json")
                .build()
                .getObjectObservable(NewsInfo.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsInfo>() {
                    @Override
                    public void accept(NewsInfo newsInfo) throws Exception {
                        Log.e("NewsInfo", "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Throwable", "");
                    }
                });
    }

    private void net1(final IBackContent back) {

        String body = "{\"channelId\":\"b9240eee3b0211e8b64c00163e30445d\" ,\"cursor\": \"0\"}";

        JSONObject object = null;
        try {
            object = new JSONObject(body);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        RequestBody requestBody = RequestBody.create(null, body);
        String s1 = RetrofitManager.DYTH_BASEURL + "news/downListNews";
        Rx2AndroidNetworking.post(s1)
                .addHeaders("Content-Type", "application/json")
//                .setContentType("application/json")
                .addJSONObjectBody(object)
                .build()
                .getObjectObservable(NewsInfo.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsInfo>() {
                    @Override
                    public void accept(NewsInfo newsInfo) throws Exception {
                        back.getResult(newsInfo.getData().getNewList().size() + "success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        back.getResult(throwable.getMessage());
                    }
                });
    }
}
