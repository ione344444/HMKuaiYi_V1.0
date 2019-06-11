package com.hengmeng.hmkuaiyi.pro.widget.floatwindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

public abstract class BaseFloatWindow {
    Context context;
    WindowManager windowManager;
    WindowManager.LayoutParams wmParams;
    private View floatView;

    private boolean isShowing = false;

    private OnDestroyHideListener onDestroyHideListener;

    /**
     * 显示悬浮窗
     *
     * 如果没有则创建
     */
    public void show(){
        if (isShowing){
            return;
        }
        createFloatWindow();

        windowManager.addView(floatView,wmParams);

        isShowing = true;
    }

    /**
     * 暂时隐藏悬浮窗
     */
    public void tempHide(){
        if (!isShowing){    // 如果原本就没显示，则不需要关闭导致报错
            return;
        }

        if (windowManager != null && floatView != null){
            windowManager.removeView(floatView);
        }

        isShowing = false;
    }

    /**
     * 隐藏并销毁悬浮窗
     *
     * 使用此方法不用担心悬浮窗已经被销毁或被隐藏
     */
    public void destroyHide(){
        if (onDestroyHideListener != null) {
            onDestroyHideListener.onDestroy();
        }

        this.tempHide();

        onDestroyHide();

        windowManager = null;
        wmParams = null;
        floatView = null;
    }


    /**
     * 使用此方法监听悬浮窗什么时候被销毁
     */
    public void setOnDestroyHideListener(OnDestroyHideListener onDestroyHideListener) {
        this.onDestroyHideListener = onDestroyHideListener;
    }

    public interface OnDestroyHideListener {
        // 只要销毁悬浮窗的函数被盗用就会执行此方法
        void onDestroy();
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
     * 最好是保证createFloatView()的职责单一
     */
    public abstract WindowManager.LayoutParams createWindowParams();

    /**
     * 销毁悬浮窗调用的方法，应该保证在这个方法里面做好该做的事，避免bug
     */
    public abstract void onDestroyHide();


//    private void startApplyFloatPermissionActivity(){
//        Intent intent = new Intent(context,ApplyFloatPermissionActivity.class);
//        // 有时候，可能需要同时创建多个悬浮窗，可能会导致启动两个授权activity
//        // FLAG_ACTIVITY_CLEAR_TOP会清空目标Activity(若存在)之上的所有Activity
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
//    }

}
