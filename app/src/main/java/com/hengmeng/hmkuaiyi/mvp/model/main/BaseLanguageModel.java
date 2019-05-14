package com.hengmeng.hmkuaiyi.mvp.model.main;

public interface BaseLanguageModel<T> {
     T loadLgSettingsData();

     void saveFromLgSettingsData(String fromLgAbb);

     void saveToLgSettingsData(String toLgAbb);
}
