package com.hengmeng.hmkuaiyi.pro.model.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiaoxi.translate.bean.TransObject;

/**
 * 用户语种数据类，SP存储实现
 */
public class SP_LanguageSettingsData {
    private final String FILENAME_LANGUAGE = "LanguageSettings";
    private final String KEYNAME_FROM_LANGUAGEABB = "fromLgAbb";
    private final String KEYNAME_TO_LANGUAGEABB = "toLgAbb";

    private SharedPreferences languagePref;

    /**
     * 构造方法，用于获取context，并且实例化SP对象
     *
     * @param context  context对象
     */
    public SP_LanguageSettingsData(Context context){
        languagePref = context.getSharedPreferences(FILENAME_LANGUAGE,Context.MODE_PRIVATE);
    }

    public void saveLgData(TransObject transObject) {
        saveFromLgData(transObject.getFromLgAbb());
        saveToLgData(transObject.getToLgAbb());
    }

    /**
     * 保存源语种到本地
     *
     */
    public void saveFromLgData(String fromLanguageAbb){
        SharedPreferences.Editor languageEditor = languagePref.edit();
        languageEditor.putString(KEYNAME_FROM_LANGUAGEABB,fromLanguageAbb);
        languageEditor.apply();
    }

    /**
     * 保存目标语种到本地
     *
     */
    public void saveToLgData(String toLanguageAbb){
        SharedPreferences.Editor languageEditor = languagePref.edit();
        languageEditor.putString(KEYNAME_TO_LANGUAGEABB,toLanguageAbb);
        languageEditor.apply();
    }

    /**
     * 从本地获取目标语种
     *
     */
    public TransObject getLgData(){
        TransObject transObject = new TransObject("","","","");
        transObject.setFromLgAbb(languagePref.getString(KEYNAME_FROM_LANGUAGEABB,""));
        transObject.setToLgAbb(languagePref.getString(KEYNAME_TO_LANGUAGEABB,""));
        return transObject;
    }
}
