package com.xiaoxi.screenutil;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

public class KeyboardOpenListenerUtil {
    boolean isLastVisible = false;

    /**
     * 监听软键盘是否打开
     */
    public KeyboardOpenListenerUtil(Activity activity, final OnKeyBoardOpenStateListener openStateListener){
        final int statusBarHeight = ScreenInfoUtil.getStatusBarHeight(activity);
        final int navigationBarHeight = ScreenInfoUtil.getNavigationBarHeight(activity);

        final View decorView = activity.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                // 计算出屏幕可见高度
                int displayHeight = rect.bottom - rect.top;
                // 获得屏幕整体高度
                int height = decorView.getHeight();
                // 获得键盘高度
                int keyboardHeight = height - displayHeight - statusBarHeight - navigationBarHeight;

                boolean visible = (double)keyboardHeight / height > 0.3;

                // 判断：避免其他原因引起的回调导致重复调用onOpen..方法
                if (visible != isLastVisible){
                    openStateListener.onOpenStateChanged(visible,keyboardHeight);
                }
                isLastVisible = visible;
            }
        });
    }

    public interface OnKeyBoardOpenStateListener{
        void onOpenStateChanged(boolean visible,int keyBoardHeight);
    }
}
