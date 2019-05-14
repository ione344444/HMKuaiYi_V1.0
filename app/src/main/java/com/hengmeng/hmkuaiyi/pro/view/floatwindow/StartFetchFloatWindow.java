package com.hengmeng.hmkuaiyi.pro.view.floatwindow;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hengmeng.hmkuaiyi.R;
import com.xiaoxi.screenutil.ScreenInfoUtil;

/**
 * 触发屏幕取词的悬浮窗
 *
 * 提供一个用户从屏幕左侧向右侧拖出的效果，并且松手触发取词
 */
public class StartFetchFloatWindow extends BaseFloatWindow{
    private final String TAG = "StartFetchFloatWindow";

    private final int NORMAL_FLOAT_WIDTH = 40;
    private final int NORMAL_FLOAT_HEIGHT = 200;
    private final int FULL_FLOAT_WIDTH;
    private final int FULL_FLOAT_HEIGHT;

    private final int NORMAL_FLOAT_X;
    private final int NORMAL_FLOAT_Y;
    private final int FULL_FLOAT_X;
    private final int FULL_FLOAT_Y;

    @SuppressLint("StaticFieldLeak")
    private static StartFetchFloatWindow instance;

    private int screenWidth;
    private int screenHeight;

    private View floatView;

    private boolean isShowing = false;

    private OnActionListener actionListener;

    private boolean dragAble = true;

    private boolean isOpened = false;


    @Override
    public View createFloatView(){
        floatView = View.inflate(context, R.layout.view_float_startfetching,null);

        if (floatView.getBackground() != null) {
            floatView.getBackground().mutate().setAlpha(0);
        }
        else {
            Log.e(TAG,"floatView.getBackground == null!");
        }

        View v_drag = floatView.findViewById(R.id.v_drag);
        if (v_drag.getBackground() != null) {
            v_drag.getBackground().mutate().setAlpha(60);
        }
        else {
            Log.e(TAG, "v_drag.getBackground() == null!");
        }

        dragViewHandle();

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

        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        wmParams.format = PixelFormat.TRANSLUCENT;// 不设置这个属性悬浮窗会有一个黑漆漆的背景
        wmParams.gravity = Gravity.START | Gravity.TOP;
        wmParams.x = NORMAL_FLOAT_X;
        wmParams.y = NORMAL_FLOAT_Y;
        wmParams.width = NORMAL_FLOAT_WIDTH;
        wmParams.height = NORMAL_FLOAT_HEIGHT;

        return wmParams;
    }

    @Override
    public void onDestroyHide() {
        dragAble = true;
        isOpened = false;
    }


    public static StartFetchFloatWindow getInstance(Context context) {
        if (instance == null) {
            synchronized (TransResultFloatWindow.class){
                if (instance == null){
                    instance = new StartFetchFloatWindow(context);
                }
            }
        }

        return instance;
    }


    public void setOnActionListener(OnActionListener actionListener){
        this.actionListener = actionListener;
    }

    public interface OnActionListener{
        void onStartDragging();

        /* 打开或关闭的回调 */
        void onDragOpened();

        void onDragClosed();
    }


    public boolean isOpened(){
        return isOpened;
    }


    public void setCanDragAble(boolean able){
        this.dragAble = able;
    }

    public boolean isDragAble(){
        return dragAble;
    }


    private StartFetchFloatWindow(Context context){
        super(context);

        screenWidth = ScreenInfoUtil.getScreenWidth(context);
        screenHeight = ScreenInfoUtil.getScreenHeight(context);
        int statusBarHeight = ScreenInfoUtil.getStatusBarHeight(context);
        int navigationBarHeight = ScreenInfoUtil.getNavigationBarHeight(context);

        FULL_FLOAT_WIDTH = screenWidth;
        FULL_FLOAT_HEIGHT = screenHeight + statusBarHeight + navigationBarHeight;
        NORMAL_FLOAT_X = 0;
        NORMAL_FLOAT_Y = screenHeight * 3 / 4 - 30;// 大概在屏幕左下角拖动
        FULL_FLOAT_X = 0;
        // 让悬浮窗可以覆盖全屏
        FULL_FLOAT_Y = - statusBarHeight;
    }


    private void openDragView(){
        if (windowManager == null || wmParams == null || floatView == null){
            return;
        }

        ValueAnimator anim = changeDragViewWidthByAnimator(screenWidth);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isOpened = true;
                Log.e(TAG,"onOpened");

                if (actionListener != null){
                    actionListener.onDragOpened();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void closeDragView(){
        if (windowManager == null || wmParams == null || floatView == null){
            return;
        }

        ValueAnimator anim = changeDragViewWidthByAnimator(0);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                wmParams.x = NORMAL_FLOAT_X;
                wmParams.y = NORMAL_FLOAT_Y;
                wmParams.width = NORMAL_FLOAT_WIDTH;
                wmParams.height = NORMAL_FLOAT_HEIGHT;

                try {
                    windowManager.updateViewLayout(floatView,wmParams);
                }catch (Exception e){
                    e.printStackTrace();
                }

                isOpened = false;
                Log.e(TAG,"onClosed");

                if (actionListener != null){
                    actionListener.onDragClosed();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    private void dragViewHandle(){
        LinearLayout llt_parent = floatView.findViewById(R.id.llt_startFetch_parent);
        llt_parent.setOnTouchListener(new View.OnTouchListener() {
            LinearLayout.LayoutParams vParams;
            View v_drag;

            int lastX;
            int moveX;

            boolean isStartDragging = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!dragAble){
                    return false;
                }

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isStartDragging = false;

                        wmParams.x = FULL_FLOAT_X;
                        wmParams.y = FULL_FLOAT_Y;
                        wmParams.height = FULL_FLOAT_HEIGHT;
                        wmParams.width = FULL_FLOAT_WIDTH;
                        windowManager.updateViewLayout(floatView,wmParams);

                        v_drag  =floatView.findViewById(R.id.v_drag);
                        vParams = new LinearLayout.LayoutParams(v_drag.getWidth(),
                                FULL_FLOAT_HEIGHT);

                        lastX = (int) event.getRawX();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        Log.e("StartFloatWindow","ACTION_MOVE");
                        if (! isStartDragging){
                            isStartDragging = true;

                            if (actionListener != null){
                                actionListener.onStartDragging();
                            }
                        }

                        int nowX = (int) event.getRawX();
                        moveX = nowX - lastX;

                        vParams.height = FULL_FLOAT_HEIGHT;
                        vParams.width += moveX;
                        v_drag.setLayoutParams(vParams);
                        v_drag.requestLayout();

                        lastX = nowX;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        /*故意穿透，CANCEL事件与UP要做相同的处理*/
                    case MotionEvent.ACTION_UP:
                        Log.e("StartFloatWindow","ACTION_UP");

                        if (moveX >= 0){// 右滑
                            // 判断view宽度是否超过屏幕宽的1/3，
                            // 超过了便将其宽设置为屏幕宽（带动画）
                            // 未超过便将其宽设置为0（带动画）
                            // 类似于抽屉布局效果
                            if (v_drag.getWidth() > (screenWidth / 9)){// 如果宽大于1/9屏幕宽，则打开
                                openDragView();
                            }else {// 如果宽小于1/3屏幕宽，则关闭，并且在动画结束还原悬浮窗大小
                                closeDragView();
                            }
                        }else {
                            if (v_drag.getWidth() > (screenWidth * 9 / 10)){// 如果宽度依然大于9/10屏幕宽,则打开
                                openDragView();
                            }else {// 如果宽度小于2/3屏幕宽，则关闭并且在动画结束还原悬浮窗大小
                                closeDragView();
                            }
                        }
                        break;
                }
                return false;
            }
        });
    }


    private ValueAnimator changeDragViewWidthByAnimator(final int newWidth){
        final View v_drag = floatView.findViewById(R.id.v_drag);
        int oldWidth = v_drag.getWidth();
        final LinearLayout.LayoutParams vParams = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT);

        ValueAnimator animWidth = ValueAnimator.ofInt(oldWidth,newWidth);
        // 经测试，动画时间设置为start-end的差值（绝对值） / 4 最合适
        animWidth.setDuration(Math.abs(oldWidth - newWidth) / 4);
        animWidth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int var = (int) animation.getAnimatedValue();

                vParams.width = var;
                v_drag.setLayoutParams(vParams);
                v_drag.requestLayout();
            }
        });

        animWidth.start();

        return animWidth;
    }

}
