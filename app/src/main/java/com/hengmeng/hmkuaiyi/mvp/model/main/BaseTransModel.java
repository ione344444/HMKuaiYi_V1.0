package com.hengmeng.hmkuaiyi.mvp.model.main;

public interface BaseTransModel<T,TL> {
    void startTranslating(T transObject,TL transListener);
}
