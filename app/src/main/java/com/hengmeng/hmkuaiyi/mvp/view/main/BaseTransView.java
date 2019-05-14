package com.hengmeng.hmkuaiyi.mvp.view.main;


public interface BaseTransView<T> {
    void showTransLoading(boolean show);

    void showResult(T transObject);

    void clearText(boolean clearFrom,boolean clearTo);

    void showCopyResult(boolean successful);

    void showToast(String text,int duration);
}
