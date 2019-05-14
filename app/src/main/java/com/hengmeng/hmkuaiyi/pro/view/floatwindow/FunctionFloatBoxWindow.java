package com.hengmeng.hmkuaiyi.pro.view.floatwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.hengmeng.hmkuaiyi.R;
import com.xiaoxi.screenutil.ScreenInfoUtil;

/**
 * 单例模式
 *
 * 取词功能的悬浮窗类
 */
public class FunctionFloatBoxWindow extends BaseFloatWindow{
    private final String TAG = "FunctionFloatBoxWindow";

    @SuppressLint("StaticFieldLeak")
    private static FunctionFloatBoxWindow instance = null;

    private OnActionListener actionListener;

    @Override
    public View createFloatView() {
        View floatView = View.inflate(context,R.layout.view_float_functionbox,null);
//        LinearLayout llt_parent = floatView.findViewById(R.id.llt_parent);
//        // 此方法可以保证透明度不会影响到子控件
//        llt_parent.getBackground().mutate().setAlpha(19);
        dragHandle();

        return floatView;
    }

    @Override
    public WindowManager.LayoutParams createWindowParams() {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

        /*
         * android 8.0 及以上不再支持TYPE_PHONE等参数
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.format = PixelFormat.TRANSLUCENT;// 不设置这个属性悬浮窗会有一个黑漆漆的背景
        wmParams.gravity = Gravity.START | Gravity.TOP;// 这个属性还会影响x,y的坐标原点位置
        wmParams.x = ScreenInfoUtil.getScreenWidth(context);
        wmParams.y = 500;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return wmParams;
    }

    // 用户对悬浮窗进行的动作的监听接口
    public interface OnActionListener{
        // 一般是用户点击了开始取词按钮时回调此方法
        void onStartFetching();

        // floatBoxView发生了任何一个点击事件时回调此方法，传入被点击的view
        void onClick(View v);
    }

    /**
     * 在单例模式中使用context容易产生内存泄漏，在这里最好传入getApplicationContext().
     */
    public static FunctionFloatBoxWindow getInstance(Context context){
        if (instance == null){
            synchronized (FunctionFloatBoxWindow.class){
                if (instance == null){
                    instance = new FunctionFloatBoxWindow(context);
                }
            }
        }

        return instance;
    }

    private FunctionFloatBoxWindow(Context context){
        super(context);
    }

    /**
     * 创建悬浮窗View
     */
    private void createFloatBoxView(){

    }

    /**
     * 实现悬浮窗拖动的方法
     */
    private void dragHandle(){
        floatView.setClickable(true);
        floatView.setFocusable(true);

        floatView.setOnTouchListener(new View.OnTouchListener() {
            int x = 0;
            int y = 0;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x = (int) event.getRawX();
                        y = (int) event.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int nowX = (int) event.getRawX();
                        int nowY = (int) event.getRawY();
                        int movedX = nowX - x;
                        int movedY = nowY - y;

                        x = nowX;
                        y = nowY;

                        wmParams.x = wmParams.x + movedX;
                        wmParams.y = wmParams.y + movedY;
                        windowManager.updateViewLayout(floatView,wmParams);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    public void setOnActionListener(final OnActionListener actionListener){
        this.actionListener = actionListener;

        if (floatView == null){
            createFloatBoxView();
        }

        final ImageButton ib_startFetching = floatView.findViewById(R.id.ib_startFetching);
        ib_startFetching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onClick(ib_startFetching);
                    actionListener.onStartFetching();
                }
            }
        });
    }
}
