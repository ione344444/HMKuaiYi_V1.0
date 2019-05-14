package com.hengmeng.hmkuaiyi.pro.model.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SP_FunctionSettingsData {
    public final String FILENAME_FUNCTION_SETTINGS = "FunctionSettings";
    public final String KEYNAME_SF_OPEN_SETTINGS = "sf_open_settings";
    public final String KEYNAME_CT_OPEN_SETTINGS = "ct_open_settings";

    private Context context;

    private SharedPreferences sp;

    public SP_FunctionSettingsData(Context context) {
        this.context = context;

        sp = context.getSharedPreferences(FILENAME_FUNCTION_SETTINGS,Context.MODE_PRIVATE);
    }

    public void saveScreenFetchOpenSettings(boolean open){
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putBoolean(KEYNAME_SF_OPEN_SETTINGS,open);
        spEditor.apply();
    }

    public boolean getScreenFetchOpenSettings(){
        return sp.getBoolean(KEYNAME_SF_OPEN_SETTINGS,false);
    }

    public void saveClipboardTransOpenSettings(boolean open){
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putBoolean(KEYNAME_CT_OPEN_SETTINGS,open);
        spEditor.apply();
    }

    public boolean getClipboardTransOpenSettings(){
        return sp.getBoolean(KEYNAME_CT_OPEN_SETTINGS,false);
    }
}
