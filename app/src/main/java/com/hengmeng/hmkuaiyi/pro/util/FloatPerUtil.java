package com.hengmeng.hmkuaiyi.pro.util;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

public class FloatPerUtil {
    public static boolean checkIsPerOpen(Context context){
        if (Build.VERSION.SDK_INT >= 23){
            try {
                Class<Settings> clazz = Settings.class;
                Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
                return (boolean) canDrawOverlays.invoke(null,context);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return false;
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            AppOpsManager opsManager = (AppOpsManager) context.getSystemService(
                    Context.APP_OPS_SERVICE);
            try {
                Class clazz = AppOpsManager.class;
                Method method = clazz.getDeclaredMethod("checkOp",
                        int.class,int.class,String.class);
                return AppOpsManager.MODE_ALLOWED
                        == (int) method.invoke(opsManager,24,Binder.getCallingUid(),context.getPackageName());
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
            return false;
        }
        return true;
    }

    public static void gotoPerSetting(){

    }
}
