package com.xiaoxi.floatpermission.rom;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;


public class MiuiFloatPerUtil {
    private static final String TAG = "MiuiFloatPerUtil";

    public static boolean checkIsPermissionOpen(Context context){
        return AndroidFloatPerUtil.checkIsPermissionOpen(context);
    }

    /**
     * 针对miui5,miui6,miui7的悬浮窗界面跳转，其它版本或无法正常跳转返回false
     */
    public static boolean goToPermissionActivity(Context context) {
        int miuiVersion = RomUtils.getMiuiVersion();
        switch (miuiVersion){
            case 5:
                return goToPermissionActivity_V5(context);

            case 6:
                return goToPermissionActivity_V6(context);

            case 7:
                return goToPermissionActivity_V7(context);

            case 8:
                return goToPermissionActivity_V8(context);

            default:
                return false;
        }

    }

    private static boolean isIntentAvailable(Intent intent, Context context) {
        if (intent == null) {
            return false;
        }
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    /**
     * 小米 V5 版本 ROM权限申请
     */
    private static boolean goToPermissionActivity_V5(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package" , packageName, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return true;
        } else {
            Log.e(TAG, "intent is not available!");
            return false;
        }
    }

    /**
     * 小米 V6 版本 ROM权限申请
     */
    private static boolean goToPermissionActivity_V6(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return true;
        } else {
            Log.e(TAG, "Intent is not available!");
            return false;
        }
    }

    /**
     * 小米 V7 版本 ROM权限申请
     */
    private static boolean goToPermissionActivity_V7(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return true;
        } else {
            Log.e(TAG, "Intent is not available!");
            return false;
        }
    }

    /**
     * 小米 V8 版本 ROM权限申请
     */
    private static boolean goToPermissionActivity_V8(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter",
                "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
            return true;
        } else {
            Log.e(TAG, "Intent is not available!");
            return false;
        }
    }

    /**
     * 跳转到miui的权限管理页面,MIUI10依然可用
     */
    public static boolean gotoPermissionSettingsActivity(Context context) {
        try { // MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivity(localIntent);
        } catch (Exception e) {
            try { // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", context.getPackageName());
                context.startActivity(localIntent);
            } catch (Exception e1) {
                return false;
            }
        }

        return true;
    }

}
