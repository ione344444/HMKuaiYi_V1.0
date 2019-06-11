package com.hengmeng.hmkuaiyi.mvp.view;

import java.util.List;

/**
 * 每一个界面，尤其是主界面包含了很多杂项设置，没必要再去创建新类，
 * 此View用于处理那些杂项设置
 */
public interface BaseMainView<T,TH> {
    void showHistory(List<TH> historyObjects );

    void refreshHistory(List<TH> historyObjects);


    void showFromLgSettings(String fromLgAbb);

    void showToLgSettings(String toLgAbb);


    void showTransLoading(boolean show);

    void showResult(T transObject);

    void showToast(String text,int duration);
}
