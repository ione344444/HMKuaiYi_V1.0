package com.hengmeng.hmkuaiyi.pro.function;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.util.ClipBoard;
import com.xiaoxi.translate.bean.TransObject;

/**
 * 翻译原文并用悬浮窗显示的类
 * 主要用于翻译显示屏幕取词结果和剪贴板监听结果
 */
public class FetchingTranslation {
    private Context context;

    private View transFloatView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams wmParams;

    private String fromText;
    private String fromLg,toLg;

    private TransObject transObject;

    public FetchingTranslation(Context context){
        this.context = context;
    }

    /**
     * 翻译抓取到的原文并以悬浮窗显示出来
     *
     * @param fromText 抓取到的原文
     * @param fromLg 原文语种
     * @param toLg 译文语种
     */
    public void showFloat(final String fromText, final String fromLg, final String toLg){
        this.fromLg = fromLg;
        this.toLg = toLg;
        this.fromText = fromText;
        final Handler mHandler = new MyHandler();


    }

    @SuppressLint("HandlerLeak")
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showFloat();
                    initFromTextUI(fromLg,fromText);
                    break;
                case 1:
                    initToTextUI(transObject.getToLgAbb(), transObject.getToText());
            }
        }
    }

    /**
     * 创建用于悬浮窗
     * 初始化windowManager,wmParams,transFloatView
     */
    private void creatTransFloat() {
        wmParams = new WindowManager.LayoutParams();
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        transFloatView = LayoutInflater.from(context).inflate(
                R.layout.view_fetchtransresult,null);
        /*
         * android 8.0 以上不再支持TYPE_PHONE等参数
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.CENTER;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 此方法用于加载原文的ui
     *
     * @param fromLg 原文语种
     * @param fromText 原文
     */
    private void initFromTextUI(String fromLg, final String fromText) {
        TextView tv_fromText = transFloatView.findViewById(R.id.dialogFT_tv_fromText);
        TextView tv_copyFromText = transFloatView.findViewById(R.id.dialogFT_tv_copyFromText);
        tv_fromText.setText(fromText);

        tv_copyFromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipBoard.setClipBoardText(context,fromText);
                Toast.makeText(context,"复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 此方法用于加载译文
     *
     * @param toLg 目标语种
     * @param toText 译文
     */
    private void initToTextUI(final String toLg, final String toText) {
        TextView tv_toText = transFloatView.findViewById(R.id.dialogFT_tv_toText);
        TextView tv_copyToText = transFloatView.findViewById(R.id.dialogFT_tv_copyToText);
        tv_toText.setText(toText);
        tv_copyToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipBoard.setClipBoardText(context,toText);
                Toast.makeText(context,"复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示悬浮窗，如果没有就创建
     */
    public void showFloat() {
        if (windowManager == null || wmParams == null || transFloatView == null){
            creatTransFloat();
        }

        windowManager.addView(transFloatView,wmParams);
    }

    /**
     * 关闭悬浮窗
     */
    public void closeFloat(){
        if (windowManager == null || wmParams == null || transFloatView == null){
            return;
        }
        windowManager.removeView(transFloatView);
    }

}
