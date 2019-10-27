package com.example.rxjava.model;

import android.support.annotation.NonNull;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by 任小龙 on 2018/11/6.
 */

public class MapModel implements IRxModel {
    @Override
    public void doSomething(final IBackContent back, Object... objects) {
        switch ((int) objects[0]) {
            case 0:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    //相当于内容发射器
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                    }
                }).map(new Function<Integer, String>() {//转换<目前类型，即将转换类型>
                    @Override
                    public String apply(@NonNull Integer integer) throws Exception {
                        return "This is result " + integer;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        back.getResult(s);
                    }
                });
                break;
        }
    }
}
