package com.example.rxjava;

/**
 * Created by 任小龙 on 2018/11/6.
 */

public class RxPresenter implements IRxPresenter, IBackContent {
    IBackContent view;
    IRxModel mIRxModel;

    public RxPresenter(IBackContent view, IRxModel IRxModel) {
        this.view = view;
        mIRxModel = IRxModel;
    }

    @Override
    public void presenter(Object... objects) {
        mIRxModel.doSomething(this, (int) objects[0]);
    }

    @Override
    public void getResult(Object o) {
        view.getResult(o);
    }
}
