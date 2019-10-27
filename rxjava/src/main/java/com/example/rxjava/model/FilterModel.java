package com.example.rxjava.model;

import com.example.rxjava.IBackContent;
import com.example.rxjava.IRxModel;

import java.sql.Array;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by 任小龙 on 2018/11/14.
 */

public class FilterModel implements IRxModel {
    @Override
    public void doSomething(final IBackContent back, Object... objects) {
        Integer[] integers = new Integer[]{1,3,5,0,9};
        Observable.fromArray(integers)
                .skip(3)//跳过几个事件执行
                .filter(new Predicate<Integer>() {//根据条件过滤
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer>0;//过滤条件
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                back.getResult(integer);
            }
        });
    }
}
