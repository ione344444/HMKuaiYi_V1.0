package com.hengmeng.hmkuaiyi.pro.function.wordfetching;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class WordFetchFloat {
    private Context context;
    private WindowManager.LayoutParams wmParams;
    private WindowManager windowManager;
    private Button btn_startFetch;

    public WordFetchFloat(Context context) {
        this.context = context;
        createFloat();
    }

    private void createFloat() {
        wmParams = new WindowManager.LayoutParams();
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        btn_startFetch = new Button(context);
        btn_startFetch.setText("开始取词");

        /*
         * android 8.0 以上不再支持TYPE_PHONE等参数
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.gravity = Gravity.END | Gravity.TOP;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        wmParams.x = dm.widthPixels;
        wmParams.y = 500;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public void show() {
        windowManager.addView(btn_startFetch,wmParams);
    }

    public void colose() {
        windowManager.removeView(btn_startFetch);
    }

    public void setActionListener(final ActionListener actionListener) {
        btn_startFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionListener.onStartFetch(btn_startFetch);
            }
        });
    }

    public interface ActionListener{
        public void onStartFetch(View view);
    }
}