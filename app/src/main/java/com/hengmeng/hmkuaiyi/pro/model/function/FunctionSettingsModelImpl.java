package com.hengmeng.hmkuaiyi.pro.model.function;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.function.FunctionSettingsContract;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_FunctionSettingsData;

public class FunctionSettingsModelImpl implements FunctionSettingsContract.FunctionSettingsModel {
    private Context context;

    SP_FunctionSettingsData sp_functionSettingsData;

    public FunctionSettingsModelImpl(Context context) {
        this.context = context;

        sp_functionSettingsData = new SP_FunctionSettingsData(context);

    }

    @Override
    public void saveScreenFetchOpenSettings(boolean open) {
        sp_functionSettingsData.saveScreenFetchOpenSettings(open);
    }

    @Override
    public boolean loadScreenFetchOpenSettings() {
        return sp_functionSettingsData.getScreenFetchOpenSettings();
    }

    @Override
    public void saveClipboardTransOpenSettings(boolean open) {
        sp_functionSettingsData.saveClipboardTransOpenSettings(open);
    }

    @Override
    public boolean loadClipboardTransOpenSettings() {
        return sp_functionSettingsData.getClipboardTransOpenSettings();
    }
}
