package com.hengmeng.hmkuaiyi.pro.model.function;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.function.ScreenTranslatorContract;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_FunctionSettingsData;

public class ScreenTranslatorModelImpl implements ScreenTranslatorContract.ScreenTranslatorModel {
    private Context context;

    public ScreenTranslatorModelImpl(Context context){
        this.context = context;
    }

    @Override
    public void saveScreenFetchingOpenSettings(boolean open) {
        SP_FunctionSettingsData.getInstance(context).saveScreenFetchingOpenSettings(open);
    }

    @Override
    public boolean loadScreenFetchingOpenSettings() {
        return SP_FunctionSettingsData.getInstance(context).getScreenFetchOpenSettings();
    }

}
