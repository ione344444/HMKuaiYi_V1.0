package com.xiaoxi.translate;

import android.content.Context;
import android.content.SharedPreferences;

public class BaiduTransApi {
    private static final String FILENAME_BAIDUTRANSAPI = "BaiduTransApi";
    private static final String KEYNAME_APPID = "appid";
    private static final String KEYNAME_SECURITYKEY = "securityKey";

    /**
     * 使用翻译api时必须要调用此方法进行初始化
     */
    public static void init(Context context,String appid,String securityKey) {
        saveAppidInfoToLocal(context, appid, securityKey);
    }

    public static String getAppidFromLocal(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME_BAIDUTRANSAPI,Context.MODE_PRIVATE);
        return sp.getString(KEYNAME_APPID,"");
    }

    public static String getSecurityKeyFromLocal(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILENAME_BAIDUTRANSAPI,Context.MODE_PRIVATE);
        return sp.getString(KEYNAME_SECURITYKEY,"");
    }

    private static void saveAppidInfoToLocal(Context context,String appid,String securityKey) {
        SharedPreferences.Editor spEditor = context.getSharedPreferences(
                FILENAME_BAIDUTRANSAPI,Context.MODE_PRIVATE).edit();
        spEditor.putString(KEYNAME_APPID,appid);
        spEditor.putString(KEYNAME_SECURITYKEY,securityKey);
        spEditor.apply();
    }

}
