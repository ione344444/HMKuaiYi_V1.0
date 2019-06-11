package com.hengmeng.hmkuaiyi.mvp.model;

import java.util.List;

/**
 * 每一个界面，尤其是主界面包含了很多杂项设置，没必要再去创建新类，
 * 此Model用于处理那些杂项设置
 */
public interface BaseMainModel<T,TH,TL> {
    // 获取可用语言列表
    List<String> getAllLanguageAbb();

    // 保存源语种设置
    void saveFromLgSettings(String fromLgAbb);

    // 保存目标语种设置
    void saveToLgSettings(String toLgAbb);

    // 从本地加载语种设置
    T loadLgAbbSettings();


    // 翻译功能的实现
    void startTranslating(T transObject,TL transListener);


    // 加载本地所有的历史数据
    List<TH> loadAllHistoryData();

    // 添加一条历史数据到本地
    void addHistoryData(T transObject);

    void deleteHistoryData(int historyId);
}
