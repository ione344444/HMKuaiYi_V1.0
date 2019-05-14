package com.hengmeng.hmkuaiyi.pro.model.main;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.main.LanguageContract;
import com.xiaoxi.translate.Language;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_LanguageSettingsData;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

public class LanguageModelImpl implements LanguageContract.LanguageModel {
    private Context context;

    public LanguageModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public TransObject loadLgSettingsData() {
        return new SP_LanguageSettingsData(context).getLgData();
    }

    @Override
    public void saveFromLgSettingsData(String fromLgAbb) {
        new SP_LanguageSettingsData(context).saveFromLgData(fromLgAbb);
    }

    @Override
    public void saveToLgSettingsData(String toLgAbb) {
        new SP_LanguageSettingsData(context).saveToLgData(toLgAbb);
    }

    @Override
    public List<String> loadAllLanguageAbb() {
        return Language.getAllLanguageAbb();
    }
}
