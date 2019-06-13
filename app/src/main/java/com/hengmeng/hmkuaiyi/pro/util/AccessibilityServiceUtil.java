package com.hengmeng.hmkuaiyi.pro.util;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.hengmeng.hmkuaiyi.pro.view.function.ScreenTranslatorService;

import static android.content.ContentValues.TAG;

public class AccessibilityServiceUtil {
    /**
     * 判断辅助服务是否打开
     *
     * @param canonicalName service.class.getCanonicalName()
     */
    public static boolean isAccessServiceOpen(Context context, String canonicalName){
        int accessibilityEnabled = 0;
        final String service = context.getPackageName() + "/"
                + canonicalName;
        try{
            accessibilityEnabled = Settings.Secure.getInt(
                    context.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        }catch (Settings.SettingNotFoundException e){
            Log.e(TAG,e.getMessage());
        }

        if(accessibilityEnabled == 1){
            TextUtils.SimpleStringSplitter simpleStringSplitter =
                    new TextUtils.SimpleStringSplitter(':');
            String settingValue = Settings.Secure.getString(
                    context.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if(settingValue != null){
                simpleStringSplitter.setString(settingValue);
                while (simpleStringSplitter.hasNext()){
                    String accessibilityService = simpleStringSplitter.next();

                    if(accessibilityService.equalsIgnoreCase(service)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void gotoAccessibilityService(Context context){
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
