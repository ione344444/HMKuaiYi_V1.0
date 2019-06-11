package com.xiaoxi.floatpermission.rom;

import android.content.Context;
import android.content.Intent;

public class Qihoo360FloatPerUtil {

    public static boolean checkIsPermissionOpen(Context context){
        return AndroidFloatPerUtil.checkIsPermissionOpen(context);
    }

    /**
     * 跳转至悬浮窗权限界面
     */
    public static boolean goToPermissionActivity(Context context){
        Intent intent = new Intent();
        intent.setClassName("com.android.settings",
                "com.android.settings.Settings$OverlaySettingsActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        return true;
    }
}
