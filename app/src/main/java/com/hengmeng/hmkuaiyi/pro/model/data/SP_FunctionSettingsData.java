package com.hengmeng.hmkuaiyi.pro.model.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SP_FunctionSettingsData {
    private final String FILENAME_FUNCTION_SETTINGS = "FunctionSettings";
    private final String KEYNAME_SF_OPEN_SETTINGS = "sf_open_settings";
    private final String KEYNAME_CT_OPEN_SETTINGS = "ct_open_settings";

    @SuppressLint("StaticFieldLeak")
    private static SP_FunctionSettingsData instance;

    private SharedPreferences sp;

/*-*********************************************** Instance *************************************************-*/

    public static SP_FunctionSettingsData getInstance(Context context) {
        if (instance == null) {
            synchronized (SP_FunctionSettingsData.class){
                if (instance == null){
                    instance = new SP_FunctionSettingsData(context);
                }
            }
        }
        return instance;
    }

/*-*********************************************** 构造方法 *************************************************-*/

    private SP_FunctionSettingsData(Context context) {
        sp = context.getSharedPreferences(FILENAME_FUNCTION_SETTINGS,Context.MODE_PRIVATE);
    }

/*-*********************************************** public *************************************************-*/

    public void saveScreenFetchingOpenSettings(boolean open){
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
