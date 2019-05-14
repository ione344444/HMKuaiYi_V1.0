package com.hengmeng.hmkuaiyi.pro.ui.function;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.pro.contract.function.ScreenFetchingContract;
import com.hengmeng.hmkuaiyi.pro.bean.TextNode;
import com.hengmeng.hmkuaiyi.pro.util.LogWriter;
import com.hengmeng.hmkuaiyi.pro.view.floatwindow.FunctionFloatBoxWindow;
import com.hengmeng.hmkuaiyi.pro.view.floatwindow.StartFetchFloatWindow;

import java.util.ArrayList;

public class ScreenFetchingAccessService extends AccessibilityService
        implements ScreenFetchingContract.ScreenFetchingView {

    private final String TAG = "ScreenFetching";

    private ArrayList<TextNode> textNodes;

    @Override
    protected void onServiceConnected() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {
        Toast.makeText(this,"辅助服务中断",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StartFetchFloatWindow.getInstance(getApplicationContext()).show();
        StartFetchFloatWindow.getInstance(getApplicationContext())
                .setOnActionListener(new StartFetchFloatWindow.OnActionListener() {
                    @Override
                    public void onStartDragging() {
                        Log.e(TAG,"onStartDragging");
                        textNodes = null;

                        if (StartFetchFloatWindow.getInstance(ScreenFetchingAccessService.this).isOpened()){
                            // 如果悬浮窗已经打开了，则不做处理
                            return;
                        }

                        ScreenFetchingHandler fetchingHandler = new ScreenFetchingHandler(
                                ScreenFetchingAccessService.this);
                        fetchingHandler.startFetching(new ScreenFetchingHandler.OnFetchingListener() {
                            @Override
                            public void onSuccess(ArrayList<TextNode> textNodes) {
                                Log.e(TAG,"onSuccess");

                                ScreenFetchingAccessService.this.textNodes = textNodes;
                                if (StartFetchFloatWindow.getInstance(ScreenFetchingAccessService.this)
                                    .isOpened()){
                                    Log.e(TAG,"onStartActivityBeOpened");
                                    startChoiceTextActivity(textNodes);

                                    StartFetchFloatWindow.getInstance(ScreenFetchingAccessService.this).destroyHide();
                                }
                            }

                            @Override
                            public void onError() {
                                Log.e(TAG,"onError");

                                ScreenFetchingAccessService.this.textNodes = null;
                                StartFetchFloatWindow.getInstance(ScreenFetchingAccessService.this).show();
                            }
                        });
                    }

                    @Override
                    public void onDragOpened() {
                        Log.e(TAG,"onDragOpened");
                        if (textNodes != null){
                            startChoiceTextActivity(textNodes);
                        }

                        StartFetchFloatWindow.getInstance(ScreenFetchingAccessService.this).destroyHide();
                    }

                    @Override
                    public void onDragClosed() {
                        Log.e(TAG,"onDragClosed");
                        Log.e(TAG,"showing?:"+StartFetchFloatWindow.getInstance(ScreenFetchingAccessService.this).isShowing());
//                        textNodes = null;
                    }
                });

        FunctionFloatBoxWindow.getInstance(this.getApplicationContext()).show();
        FunctionFloatBoxWindow.getInstance(this.getApplicationContext())
                .setOnActionListener(new FunctionFloatBoxWindow.OnActionListener() {
            @Override
            public void onStartFetching() {

            }

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroy() {
        FunctionFloatBoxWindow.getInstance(this).destroyHide();
        super.onDestroy();
    }



    @Override
    public void showFloat() {

    }

    @Override
    public void hideFloat() {

    }

    private void startChoiceTextActivity(ArrayList<TextNode> textNodes){

        Intent intent = new Intent(this, ChoiceTextActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putParcelableArrayListExtra("text_nodes", textNodes);
        startActivity(intent, ActivityOptions.makeCustomAnimation(this.getBaseContext(),
                android.R.anim.fade_in,android.R.anim.fade_out).toBundle());// 淡入淡出
    }
}
