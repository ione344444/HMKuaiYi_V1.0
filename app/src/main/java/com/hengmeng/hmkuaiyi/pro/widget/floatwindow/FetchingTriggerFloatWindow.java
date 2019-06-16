package com.hengmeng.hmkuaiyi.pro.widget.floatwindow;

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
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.xiaoxi.screenutil.ScreenInfoUtil;

/**
 * 触发屏幕取词的悬浮窗
 *
 * 提供一个用户从屏幕左侧向右侧拖出的效果，类似于抽屉布局，并且松手触发取词
 */
public class FetchingTriggerFloatWindow extends BaseFloatWindow{
    private final String TAG = "FetchingTriggerFloatWindow";

    // 正常状态下悬浮窗的宽和高
    private final int NORMAL_FLOAT_WIDTH = 60;
    private final int NORMAL_FLOAT_HEIGHT = 150;
    // 抽屉打开状态下的宽和高
    private final int FULL_FLOAT_WIDTH;
    private final int FULL_FLOAT_HEIGHT;
    // 正常状态下悬浮窗的x和y坐标
    private final int NORMAL_FLOAT_X;
    private final int NORMAL_FLOAT_Y;
    // 抽屉打开状态下悬浮窗的x和y坐标
    private final int FULL_FLOAT_X;
    private final int FULL_FLOAT_Y;
    // 抽屉正常和打开状态下可见view（在滑动过程中可见的view）的宽
    private  int _NORMAL_VISIBLE_VIEW_WIDTH;
    private  int FULL_VISIBLE_VIEW_WIDTH;

    @SuppressLint("StaticFieldLeak")
    private static FetchingTriggerFloatWindow instance;

    private int screenWidth;
    private int screenHeight;

    private View floatView;

    // 动作监听接口
    private OnActionListener actionListener;

    // 触发器是否可拖动
    private boolean dragAble = true;

    // 触发器是否打开
    private boolean isOpened = false;


    @Override
    public View createFloatView(){
        floatView = View.inflate(context, R.layout.view_float_fetching_trigger,null);

        if (floatView.getBackground() != null) {
            floatView.getBackground().mutate().setAlpha(0);
        }
        else {
            Log.e(TAG,"floatView.getBackground == null!");
        }

        View v_drag = floatView.findViewById(R.id.v_drag);
        v_drag.getBackground().mutate().setAlpha(255);

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

        wmParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        wmParams.format = PixelFormat.TRANSLUCENT;// 不设置这个属性悬浮窗会有一个黑漆漆的背景
        wmParams.gravity = Gravity.START | Gravity.TOP;
        wmParams.x = NORMAL_FLOAT_X;
        wmParams.y = NORMAL_FLOAT_Y;
        wmParams.height = NORMAL_FLOAT_HEIGHT;

        return wmParams;
    }

    @Override
    public void onDestroyHide() {
        dragAble = true;
        isOpened = false;
        actionListener = null;
    }

/*-************************************************** Instance **********************************************-*/

    public static FetchingTriggerFloatWindow getInstance(Context context) {
        if (instance == null) {
            synchronized (TransResultFloatWindow.class){
                if (instance == null){
                    instance = new FetchingTriggerFloatWindow(context);
                }
            }
        }

        return instance;
    }


    /**
     * 判断当前单例对象是否存在（instance != null）
     */
    public static boolean existsInstance(){
        return instance != null;
    }

/*-************************************************** 构造方法 **********************************************-*/

    private FetchingTriggerFloatWindow(Context context){
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
        FULL_VISIBLE_VIEW_WIDTH = screenWidth;
    }



/*-************************************************** Instance **********************************************-*/

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


    /**
     * 显示正在抓取的进度条的实现
     */
    public void showFetchLoading() {
        LinearLayout llt_loading = floatView.findViewById(R.id.llt_fetchingLoading);
        llt_loading.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏正在抓取的进度条的实现
     */
    public void hideFetchLoading() {
        LinearLayout llt_loading = floatView.findViewById(R.id.llt_fetchingLoading);
        llt_loading.setVisibility(View.GONE);
    }


    /**
     * 监听parent，实现抽屉效果来触发取词
     */
    @SuppressLint("ClickableViewAccessibility")
    private void dragViewHandle(){
        final LinearLayout llt_parent = floatView.findViewById(R.id.llt_startFetch_parent);

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

                        // 将悬浮窗设置为全屏，方便接收滑动事件
                        wmParams.x = FULL_FLOAT_X;
                        wmParams.y = FULL_FLOAT_Y;
                        wmParams.height = FULL_FLOAT_HEIGHT;
                        wmParams.width = FULL_FLOAT_WIDTH;
                        windowManager.updateViewLayout(floatView,wmParams);

                        v_drag  =floatView.findViewById(R.id.v_drag);
                        if (! isOpened){// 获取v_drag正常状态下的宽，在这里面可以正确获取到
                            _NORMAL_VISIBLE_VIEW_WIDTH = v_drag.getWidth();
                        }
                        v_drag.getBackground().mutate().setAlpha(60);
                        vParams = new LinearLayout.LayoutParams(v_drag.getWidth(),FULL_FLOAT_HEIGHT);

                        lastX = (int) event.getRawX();
                        break;

                    case MotionEvent.ACTION_MOVE:
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

                    case MotionEvent.ACTION_SCROLL:
                        Toast.makeText(context, "outside", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void openDragView(){
        if (windowManager == null || wmParams == null || floatView == null){
            return;
        }

        ValueAnimator anim = changeDragViewWidthByAnimator(FULL_VISIBLE_VIEW_WIDTH);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isOpened = true;

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

        ValueAnimator anim = changeDragViewWidthByAnimator(_NORMAL_VISIBLE_VIEW_WIDTH);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                View v_drag = floatView.findViewById(R.id.v_drag);
                v_drag.getBackground().mutate().setAlpha(255);

                // 将悬浮窗的x，y，宽，高设置为正常状态
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
