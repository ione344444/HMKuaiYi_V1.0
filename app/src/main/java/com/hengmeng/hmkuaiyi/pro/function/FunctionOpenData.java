package com.hengmeng.hmkuaiyi.pro.function;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 使用SP来存储功能打开状态的类
 */
public class FunctionOpenData {
    private  final String SPNAME_FUNCTION = "Function";
    public static final String SPKEY_FUNCTION_SWF = "function_swf";
    public static final String SPKEY_FUNCTION_CBD = "function_cbd";

    private Context context;

    public FunctionOpenData(Context context) {
        this.context = context;
    }

    /**
     * 利用给定键名来获取本地SP的布尔值
     * 倒不如说是根据功能键名来获取功能打开状态
     *
     * @param key 功能键名
     * @return 打开状态
     */
    public boolean getFunctionBooleanSp(String key) {
        SharedPreferences spGet = context.getSharedPreferences(
                SPNAME_FUNCTION,Context.MODE_PRIVATE);
        return spGet.getBoolean(key,false);
    }
    /**
     * 利用Sp存储将功能打开状态存储到本地，存入一个布尔值
     *
     * @param key 键
     * @param b 功能是否打开
     */
    public void setFunctionBooleanSP(String key,boolean b) {
        SharedPreferences.Editor spEdit = context.getSharedPreferences(
                SPNAME_FUNCTION,Context.MODE_PRIVATE).edit();
        spEdit.putBoolean(key,b);
        spEdit.apply();
    }
}
