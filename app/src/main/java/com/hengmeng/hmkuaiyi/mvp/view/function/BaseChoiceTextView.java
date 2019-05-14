package com.hengmeng.hmkuaiyi.mvp.view.function;

public interface BaseChoiceTextView<T> {
    void showLoading(boolean show);

    void showResult(T transResult);
}
