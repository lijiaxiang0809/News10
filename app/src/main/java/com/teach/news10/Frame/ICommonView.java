package com.teach.news10.Frame;

/**
 * Created by 任小龙 on 2019/3/29.
 */
public interface ICommonView<T> {
    void onError(int whichApi,Throwable e);
    void onResponse(int whichApi, T... t);
}
