package com.xiaoxi.floatpermission.rom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class OppoFloatPerUtil {

    public static boolean checkIsPermissionOpen(Context context){
        return AndroidFloatPerUtil.checkIsPermissionOpen(context);
    }

    /**
     * oppo ROM 权限申请
     */
    public static boolean goToPermissionActivity(Context context) {
        //merge request from https://github.com/zhaozepeng/FloatWindowPermission/pull/26
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //com.coloros.safecenter/.sysfloatwindow.FloatWindowListActivity
            ComponentName comp = new ComponentName("com.coloros.safecenter",
                    "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");//悬浮窗管理页面
            intent.setComponent(comp);
            context.startActivity(intent);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
