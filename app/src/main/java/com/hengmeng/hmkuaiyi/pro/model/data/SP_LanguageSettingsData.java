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

    private static SP_LanguageSettingsData instance;

    private SharedPreferences languagePref;

/*-********************************************* Instance **************************************************-*/

    public static SP_LanguageSettingsData getInstance(Context context){
        if (instance == null){
            synchronized (SP_LanguageSettingsData.class){
                if (instance == null){
                    instance = new SP_LanguageSettingsData(context);
                }
            }
        }
        return instance;
    }

/*-********************************************* 构造方法 **************************************************-*/

    private SP_LanguageSettingsData(Context context){
        languagePref = context.getSharedPreferences(FILENAME_LANGUAGE,Context.MODE_PRIVATE);
    }

/*-********************************************* public **************************************************-*/

    /**
     * 保存语种设置到本地
     *
     * @param transObject TransObject对象，包含fromLgAbb,toLgAbb
     */
    public void saveLgAbbData(TransObject transObject) {
        saveFromLgAbbData(transObject.getFromLgAbb());
        saveToLgAbbData(transObject.getToLgAbb());
    }

    /**
     * 从本地获取目标语种设置
     *
     * @return TransObject对象，包含fromLgAbb,toLgAbb
     */
    public TransObject loadLgAbbData(){
        TransObject transObject = new TransObject("","","","");
        transObject.setFromLgAbb(languagePref.getString(KEYNAME_FROM_LANGUAGEABB,""));
        transObject.setToLgAbb(languagePref.getString(KEYNAME_TO_LANGUAGEABB,""));
        return transObject;
    }


    /**
     * 保存源语种到本地
     *
     */
    public void saveFromLgAbbData(String fromLanguageAbb){
        SharedPreferences.Editor languageEditor = languagePref.edit();
        languageEditor.putString(KEYNAME_FROM_LANGUAGEABB,fromLanguageAbb);
        languageEditor.apply();
    }

    /**
     * 保存目标语种到本地
     *
     */
    public void saveToLgAbbData(String toLanguageAbb){
        SharedPreferences.Editor languageEditor = languagePref.edit();
        languageEditor.putString(KEYNAME_TO_LANGUAGEABB,toLanguageAbb);
        languageEditor.apply();
    }
}
