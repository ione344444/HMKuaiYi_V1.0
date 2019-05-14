package com.xiaoxi.screenutil;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class ScreenInfoUtil {
    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context){
        // 获取屏幕的高度不会包括底部导航栏高度，在此加上
        return getScreenSize(context).y +getNavigationBarHeight(context);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context){
        return getScreenSize(context).x;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context){
        int height = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height","dimen","android");
        if (resourceId > 0){
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * 获取底部导航栏高度（虚拟按键栏）
     */
    public static int getNavigationBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier(
                "navigation_bar_height","dimen","android");
        if (resourceId > 0){
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    private static Point getScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (wm != null) {
            wm.getDefaultDisplay().getSize(point);
        }
        return point;
    }
}
