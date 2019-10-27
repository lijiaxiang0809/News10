package com.teach.news10.Frame;

import java.lang.ref.WeakReference;

/**
 * Created by 任小龙 on 2019/4/1.
 */
public class BasePresenter<V extends ICommonView, M extends ICommonModel> {
    private WeakReference<V> view;
    private WeakReference<M> model;

    public void attach(V view, M model) {
        this.view = new WeakReference<>(view);
        this.model = new WeakReference<>(model);
    }

    public void detach() {
        if (view != null) {
            view.clear();
            this.view = null;
        }
        if (model != null) {
            model.clear();
            this.model = null;
        }
    }

    public V getView() {
        return view != null ? view.get() : null;
    }

    public M getModel() {
        return model != null ? model.get() : null;
    }
}
