package com.xiaoxi.translate;

import android.content.Context;
import android.content.SharedPreferences;

public class BaiduTransApi {
    private static String appId;
    private static String securityKey;

    /**
     * 使用翻译api时必须要调用此方法进行初始化,这个初始化是临时的
     */
    public static void init(String appId,String securityKey) {
        BaiduTransApi.appId = appId;
        BaiduTransApi.securityKey = securityKey;
    }

    static String getAppId() {
        return appId;
    }

    static String getSecurityKey() {
        return securityKey;
    }
}
