package com.hengmeng.hmkuaiyi.mvp.model.function;

public interface BaseScreenFetchingModel<T,L> {
    void startTranslating(T transObject,L listener);
}
