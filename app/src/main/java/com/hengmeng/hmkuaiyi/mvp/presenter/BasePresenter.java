package com.hengmeng.hmkuaiyi.mvp.presenter;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M,V> {
    private M mModel;
    private WeakReference<V> mViewRef;

    public void onAttach(M model,V view){
        mModel = model;
        mViewRef = new WeakReference<>(view);
    }

    public V getView(){
        return isViewAttached() ? mViewRef.get() : null;
    }

    public M getModel() {
        return mModel;
    }

    public boolean isViewAttached() {
        return null != mViewRef && null != mViewRef.get();
    }

    protected void onDetach() {
        if (null != mViewRef){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
