package com.hengmeng.hmkuaiyi.pro.view.floatwindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.hengmeng.hmkuaiyi.R;
import com.xiaoxi.translate.JsonTransApi;
import com.xiaoxi.translate.bean.TransObject;

public class TransResultFloatWindow extends BaseFloatWindow{
    @SuppressLint("StaticFieldLeak")
    private static TransResultFloatWindow instance;

    private String resultText;

    private OnActionListener actionListener;

    @Override
    public View createFloatView(){
        View floatView = View.inflate(context, R.layout.view_fetchtransresult,null);

        TextView tv_copyFromText = floatView.findViewById(R.id.dialogFT_tv_copyFromText);
        tv_copyFromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView tv_copyToText = floatView.findViewById(R.id.dialogFT_tv_copyToText);
        tv_copyToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        //wmParams.format = PixelFormat.TRANSLUCENT;// 不设置这个属性悬浮窗会有一个黑漆漆的背景
        wmParams.gravity = Gravity.CENTER;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return wmParams;
    }

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

    public void startTranslate(final String fromText, String fromLgAbb, String toLgAbb){
        if (floatView == null){
            createFloatView();
        }

        final TextView tv_fromText = floatView.findViewById(R.id.dialogFT_tv_fromText);
        tv_fromText.setText(fromText);
        final TextView tv_toText = floatView.findViewById(R.id.dialogFT_tv_toText);

        JsonTransApi jsonTransApi = new JsonTransApi(context);
        jsonTransApi.startTranslating(new TransObject(fromLgAbb, fromText, toLgAbb, ""),
                new JsonTransApi.TransResultListener() {
                    @Override
                    public void onSuccess(TransObject transResult, String jsonResult) {
                         resultText = transResult.getToText();
                         tv_toText.setText(resultText);

                         if (actionListener != null){
                             actionListener.onTransSuccess(transResult,jsonResult);
                         }
                    }

                    @Override
                    public void onError(String errorCode, String jsonResult) {
                        tv_toText.setText("翻译失败");

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

    private TransResultFloatWindow(Context context) {
        super(context);
    }

}
