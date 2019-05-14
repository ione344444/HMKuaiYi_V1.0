package com.hengmeng.hmkuaiyi.pro.presenter.main;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.main.LanguageContract;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

public class LanguagePresenterImpl extends LanguageContract.LanguagePresenter {
    private Context context;

    public LanguagePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadLgSettingsData() {
        TransObject lgSettings = getModel().loadLgSettingsData();
        if (lgSettings.getFromLgAbb().equals("")){
            lgSettings.setFromLgAbb("auto");
        }
        if (lgSettings.getToLgAbb().equals("")){
            lgSettings.setToLgAbb("auto");
        }

        getView().showFromLgSettings(lgSettings.getFromLgAbb());
        getView().showToLgSettings(lgSettings.getToLgAbb());
    }


    @Override
    public void loadAllLanguage() {
        List<String> languages = getModel().loadAllLanguageAbb();
        getView().createAllLgView(languages);
    }

    @Override
    public void selectFromLg(String fromLgAbb) {
        getModel().saveFromLgSettingsData(fromLgAbb);

        getView().showFromLgSettings(fromLgAbb);
    }

    @Override
    public void selectToLg(String toLgAbb) {
        getModel().saveToLgSettingsData(toLgAbb);

        getView().showToLgSettings(toLgAbb);
    }
}
