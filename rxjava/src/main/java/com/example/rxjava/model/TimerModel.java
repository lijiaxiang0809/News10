package com.example.rxjava.model;

import android.annotation.SuppressLint;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 任小龙 on 2018/11/14.
 */

public class TimerModel implements IRxModel {
    @Override
    public void doSomething(final IBackContent back, Object... objects) {
//       timer(back);
        interval(back);
    }

    int count = 0;
    Disposable subscribe = null;

    private void interval(final IBackContent back) {

        back.getResult(getNowStrTime());
        /*
        * interval:根据设定的间隔时间循环执行（隔设定时间执行一次）
        *
        * initialDelay:首次执行后间隔时间
        * period：第二次后每次间隔时间
        * 时间类型
        * */
        subscribe = io.reactivex.Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        count++;
                        if (count > 5 && subscribe != null && !subscribe.isDisposed())
                            subscribe.dispose();
                        back.getResult(getNowStrTime());
                    }
                });

    }

    private void timer(final IBackContent back) {
        back.getResult(getNowStrTime());
        io.reactivex.Observable.timer(2, TimeUnit.SECONDS)//延迟几秒操作
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        back.getResult(getNowStrTime());
                    }
                });
        /*
        * new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               back.getResult(getNowStrTime());
            }
        },2000);
        * */
    }

    public static String getNowStrTime() {
        long time = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time));
    }
}
