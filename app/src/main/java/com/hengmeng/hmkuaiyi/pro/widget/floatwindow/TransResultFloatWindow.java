package com.hengmeng.hmkuaiyi.pro.widget.floatwindow;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.util.ClipboardUtil;
import com.xiaoxi.screenutil.ScreenInfoUtil;
import com.xiaoxi.translate.JsonTransApi;
import com.xiaoxi.translate.bean.TransObject;

public class TransResultFloatWindow extends BaseFloatWindow{
    @SuppressLint("StaticFieldLeak")
    private static TransResultFloatWindow instance;

    private String resultText;

    private View floatView;

    private OnActionListener actionListener;

    private int normalVisibleFloatViewWidth;
    private int normalVisibleFloatViewHeight;

/*-**************************************** BaseFloatWindow的抽象方法实现 *******************************************-*/

    @Override
    public View createFloatView(){
        normalVisibleFloatViewWidth = ScreenInfoUtil.getScreenWidth(context) - 200;
        normalVisibleFloatViewHeight = ScreenInfoUtil.getScreenHeight(context) / 2;

        floatView = View.inflate(context, R.layout.view_fetchtransresult,null);

        LinearLayout llt_parent = floatView.findViewById(R.id.llt_parent_transResult);
        llt_parent.setBackgroundColor(Color.parseColor("#000000"));
        llt_parent.getBackground().mutate().setAlpha(100);

        ImageButton ib_closeFloatWindow = floatView.findViewById(R.id.ib_closeTransFloatWindow);
        ib_closeFloatWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransResultFloatWindow.getInstance(context).destroyHide();
            }
        });

        // 原文译文复制处理
        copyHandle();

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
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        wmParams.format = PixelFormat.TRANSLUCENT;// 不设置这个属性悬浮窗会有一个黑漆漆的背景
        wmParams.gravity = Gravity.TOP;
        wmParams.y = -ScreenInfoUtil.getStatusBarHeight(context);// 让悬浮窗遮蔽状态栏
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = ScreenInfoUtil.getScreenHeight(context);

        return wmParams;
    }

    /**
    * 悬浮窗被用户销毁时调用此方法（调用了父类的destroyHide()方法）
    */
    @Override
    public void onDestroyHide() {

    }

    /**
     * 弃用原本的show()方法
     */
    @Override
    @Deprecated
    public void show() {

    }

    /**
     * 在悬浮窗关闭销毁之前加了一个动画
     *
     * 在销毁时不用担心悬浮窗已经被销毁或隐藏
     */
    @Override
    public void destroyHide() {
        if (floatView == null){
            super.destroyHide();
            return;
        }

        final int start = 100,end = 0;
        final ValueAnimator anim = ValueAnimator.ofInt(start,end);// 百分比
        anim.setDuration(300);
        anim.setCurrentPlayTime(0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (int) animation.getAnimatedValue();

                if (val == end){
                    TransResultFloatWindow.super.destroyHide();
                }

                floatView.setAlpha((float)val / 100);
            }
        });
        anim.start();
    }

    /**
     * 重写设计show()方法：悬浮窗开启后添加了一个动画，并且show()的时候startTrans...()
     */
    public void show(final String fromText, final String fromLgAbb, final String toLgAbb) {
        super.show();

        final int start = 0,end = 100;
        final ValueAnimator anim = ValueAnimator.ofInt(start,end);// 百分比
        anim.setDuration(300);
        anim.setCurrentPlayTime(0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (int) animation.getAnimatedValue();

                if (val == end){
                    startTranslating(fromText,fromLgAbb,toLgAbb);
                }

                floatView.setAlpha((float)val / 100);
            }
        });
        anim.start();
    }


    /*-********************************************* getInstance() ***************************************************-*/

    public static TransResultFloatWindow getInstance(Context context) {
        if (instance == null) {
            synchronized (TransResultFloatWindow.class){
                if (instance == null){
                    instance = new TransResultFloatWindow(context);
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

/*-******************************************** 翻译，以及翻译监听接口 ***************************************-*/

    private void startTranslating(final String fromText, String fromLgAbb, String toLgAbb){
        resultText = null;

        if (floatView == null){
            createFloatView();
        }

        final TextView tv_fromText = floatView.findViewById(R.id.dialogFT_tv_fromText);
        tv_fromText.setText(fromText);
        final TextView tv_toText = floatView.findViewById(R.id.dialogFT_tv_toText);

        final ProgressBar waitProgressBar = floatView.findViewById(R.id.progressBar_waitTrans);
        waitProgressBar.setVisibility(View.VISIBLE);
        tv_toText.setVisibility(View.GONE);

        JsonTransApi jsonTransApi = new JsonTransApi(context);
        jsonTransApi.startTranslating(new TransObject(fromLgAbb, fromText, toLgAbb, ""),
                new JsonTransApi.TransResultListener() {
                    @Override
                    public void onSuccess(TransObject transResult, String jsonResult) {
                        tv_toText.setVisibility(View.VISIBLE);
                        waitProgressBar.setVisibility(View.GONE);

                        resultText = transResult.getToText();
                        tv_toText.setText(resultText);

                        if (actionListener != null){
                            actionListener.onTransSuccess(transResult,jsonResult);
                        }
                    }

                    @Override
                    public void onError(String errorCode, String jsonResult) {
                        tv_toText.setVisibility(View.VISIBLE);
                        tv_toText.setText("翻译失败");
                        waitProgressBar.setVisibility(View.GONE);

                        if (actionListener != null){
                            actionListener.onTransError(errorCode,jsonResult);
                        }
                    }
                });
    }

    public void setOnActionListener(OnActionListener actionListener){
        this.actionListener = actionListener;
    }

    public interface OnActionListener {
        void onTransSuccess(TransObject transResult,String jsonResult);

        void onTransError(String errorCode,String jsonResult);

        void onFromTextCopy(boolean success);

        void onToTextCopy(boolean success);
    }

/*-*************************************** 构造方法 *********************************************************-*/

    private TransResultFloatWindow(Context context) {
        super(context);
    }

/*-*************************************** 对copy的处理******************************************************-*/

    private void copyHandle(){
        TextView tv_copyFromText = floatView.findViewById(R.id.dialogFT_tv_copyFromText);
        tv_copyFromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_fromText = floatView.findViewById(R.id.dialogFT_tv_fromText);
                if (tv_fromText.getText() != null){
                    boolean success = ClipboardUtil.setClipboardText(context,tv_fromText.getText().toString());

                    if (actionListener != null){
                        actionListener.onFromTextCopy(success);
                    }
                }
            }
        });

        TextView tv_copyToText = floatView.findViewById(R.id.dialogFT_tv_copyToText);
        tv_copyToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_toText = floatView.findViewById(R.id.dialogFT_tv_toText);
                if (tv_toText.getText() != null){
                    boolean success = ClipboardUtil.setClipboardText(context,tv_toText.getText().toString());

                    if (actionListener != null){
                        actionListener.onToTextCopy(success);
                    }
                }
            }
        });
    }
}
