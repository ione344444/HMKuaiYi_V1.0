package com.xiaoxi.floatpermission;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.xiaoxi.floatpermission.rom.AndroidFloatPerUtil;
import com.xiaoxi.floatpermission.rom.HuaweiFloatPerUtil;
import com.xiaoxi.floatpermission.rom.MeizuFloatPerUtil;
import com.xiaoxi.floatpermission.rom.MiuiFloatPerUtil;
import com.xiaoxi.floatpermission.rom.OppoFloatPerUtil;
import com.xiaoxi.floatpermission.rom.Qihoo360FloatPerUtil;
import com.xiaoxi.floatpermission.rom.RomUtils;

/**
 *
 */
public class FloatPerUtil {

    /**
     * 针对于所有机型的悬浮窗权限检测方法
     */
    public static boolean isFloatPerOpen(Context context){
        if (Build.VERSION.SDK_INT < 23) {
            if (RomUtils.checkIsMiuiRom()) {
                return MiuiFloatPerUtil.checkIsPermissionOpen(context);
            } else if (RomUtils.checkIsMeizuRom()) {
                return MeizuFloatPerUtil.checkIsPermissionOpen(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                return HuaweiFloatPerUtil.checkIsPermissionOpen(context);
            } else if (RomUtils.checkIs360Rom()) {
                return Qihoo360FloatPerUtil.checkIsPermissionOpen(context);
            } else if (RomUtils.checkIsOppoRom()) {
                return OppoFloatPerUtil.checkIsPermissionOpen(context);
            }else {
                return true;
            }
        }else {
            // 魅族依然要用以前的方式来检测
            if (RomUtils.checkIsMeizuRom()){
                return MeizuFloatPerUtil.checkIsPermissionOpen(context);
            }else {
                //return Settings.canDrawOverlays(context);
                return AndroidFloatPerUtil.checkIsPermissionOpen(context);
            }
        }
    }

    /**
     * 针对于所有机型的悬浮窗权限界面跳转的方法
     */
    public static boolean goToPermissionActivity(Context context){
        if (RomUtils.checkIsMiuiRom()){
            // miui不推荐跳转到悬浮窗权限，直接跳转的话会跳转到 "允许显示在其他应用上方" 的界面，
            // 这个界面授予的权限只是临时的
            return MiuiFloatPerUtil.gotoPermissionSettingsActivity(context);
        }
        if (Build.VERSION.SDK_INT < 23){
            if (RomUtils.checkIsMeizuRom()) {
                return MeizuFloatPerUtil.goToPermissionActivity(context);
            } else if (RomUtils.checkIsHuaweiRom()) {
                return HuaweiFloatPerUtil.goToPermissionActivity(context);
            } else if (RomUtils.checkIs360Rom()) {
                return Qihoo360FloatPerUtil.goToPermissionActivity(context);
            } else if (RomUtils.checkIsOppoRom()) {
                return OppoFloatPerUtil.goToPermissionActivity(context);
            }else {
                return false;
            }
        }else {
            return AndroidFloatPerUtil.goToPermissionActivity_V23P(context);
        }
    }
}
