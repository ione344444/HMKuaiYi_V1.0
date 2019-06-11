package com.hengmeng.hmkuaiyi.pro.model.function;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.function.FunctionSettingsContract;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_FunctionSettingsData;

public class FunctionSettingsModelImpl implements FunctionSettingsContract.FunctionSettingsModel {
    private Context context;

    public FunctionSettingsModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void saveScreenFetchOpenSettings(boolean open) {
        SP_FunctionSettingsData.getInstance(context).saveScreenFetchingOpenSettings(open);
    }

    @Override
    public boolean loadScreenFetchOpenSettings() {
        return SP_FunctionSettingsData.getInstance(context).getScreenFetchOpenSettings();
    }

    @Override
    public void saveClipboardTransOpenSettings(boolean open) {
        SP_FunctionSettingsData.getInstance(context).saveClipboardTransOpenSettings(open);
    }

    @Override
    public boolean loadClipboardTransOpenSettings() {
        return SP_FunctionSettingsData.getInstance(context).getClipboardTransOpenSettings();
    }
}
