package com.example.rxjava.model;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by 任小龙 on 2018/11/14.
 */

public class DoOnNextModel implements IRxModel {
    List<String> mList = new ArrayList<>();

    @Override
    public void doSomething(final IBackContent back, Object... objects) {
       doOnNext(back);
       take(back);
    }

    private void take(final IBackContent back) {
        Observable.fromArray(1,3,4,5,7,8)
                .take(3)//获取指定数据或集合的前三个数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        back.getResult(integer);
                    }
                });
    }

    private void doOnNext(final IBackContent back) {
        Observable.just("方超","四牛","猪哥")
                .doOnNext(new Consumer<String>() {//对于被观察者发出的数据进行二次操作
                    @Override
                    public void accept(String s) throws Exception {
                        mList.add(s);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                back.getResult(s+mList.size());
            }
        });
    }
}
