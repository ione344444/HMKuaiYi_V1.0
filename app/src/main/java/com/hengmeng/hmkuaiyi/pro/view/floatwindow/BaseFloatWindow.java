package com.hengmeng.hmkuaiyi.pro.view.floatwindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

public abstract class BaseFloatWindow {
    Context context;
    WindowManager windowManager;
    WindowManager.LayoutParams wmParams;
    View floatView;

    private boolean isShowing = false;

    /**
     * 显示悬浮窗
     *
     * 如果没有则创建
     */
    public void show(){
        createFloatWindow();
        windowManager.addView(floatView,wmParams);
        isShowing = true;
    }

    /**
     * 暂时隐藏悬浮窗
     */
    public void tempHide(){
        if (windowManager != null && floatView != null){
            windowManager.removeView(floatView);
        }

        isShowing = false;
    }

    /**
     * 隐藏并销毁悬浮窗
     */
    public void destroyHide(){
        tempHide();

        windowManager = null;
        wmParams = null;
        floatView = null;
    }

    /**
     * 设置悬浮窗参数为createWindowParams时候的状态
     */
    public void recoverWindowParams(){
        wmParams = createWindowParams();

        if (windowManager != null && floatView != null){
            windowManager.updateViewLayout(floatView,wmParams);
        }
    }

    /**
     * 悬浮窗是否为显示状态
     */
    public boolean isShowing(){
        return isShowing;
    }

    BaseFloatWindow(Context context){
        this.context = context;
    }

    /**
     * 创建悬浮窗
     */
    private void createFloatWindow(){
        if (windowManager == null){
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }

        if (wmParams == null){
            wmParams = createWindowParams();
        }

        if (floatView == null){
            floatView = createFloatView();
        }
    }

    /**
     * 创建悬浮窗View交给子类实现
     */
    public abstract View createFloatView();

    /**
     * 创建悬浮窗参数交给子类实现
     *
     * 由于recoverWindowParams()方法，最好是保证createFloatView()的职责单一
     */
    public abstract WindowManager.LayoutParams createWindowParams();
}
