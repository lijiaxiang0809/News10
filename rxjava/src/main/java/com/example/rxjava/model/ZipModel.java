package com.example.rxjava.model;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;
import com.example.rxjava.IService;
import com.example.rxjava.RetrofitManager;
import com.example.rxjava.bean.ChannelInfo;
import com.example.rxjava.bean.NewsInfo;
import com.example.rxjava.bean.ZipInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by 任小龙 on 2018/11/14.
 */

public class ZipModel implements IRxModel {
    //一般用于平级网络请求的打包请求，还可以对请求的结果进行合并
    @Override
    public void doSomething(final IBackContent back, Object... objects) {
//        test(back);
        IService service = RetrofitManager.getRetrofitManager().getService();
        String content = "{\"channelId\":" + "\"" + "0" + "\" ,\"cursor\": \"0\",}";
        RequestBody body = RequestBody.create(null, content);


        service.getChannel();
        service.getNews(body);

        Observable.zip(service.getChannel(), service.getNews(body), new BiFunction<ChannelInfo, NewsInfo, ZipInfo>() {
            @Override
            public ZipInfo apply(ChannelInfo channelInfo, NewsInfo newsInfo) throws Exception {
                ZipInfo info = new ZipInfo();
                info.zipData = channelInfo.getCode() + "," + newsInfo.getCode();
                return info;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZipInfo>() {
                    @Override
                    public void accept(ZipInfo zipInfo) throws Exception {
                        back.getResult(zipInfo.zipData);
                    }
                });
    }

    private void test(final IBackContent back) {
        Observable.zip(getObservable1(), getObservable2(), new BiFunction<String, Integer, Object>() {
            @Override
            public Object apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                back.getResult(o);
            }
        });
    }

    private Observable<String> getObservable1() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed())
                    e.onNext("快过年了,");
            }
        });
    }

    private Observable<Integer> getObservable2() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed())
                    e.onNext(666);
            }
        });
    }
}
