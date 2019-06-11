package com.hengmeng.hmkuaiyi.pro.view.function;

import android.app.ActivityOptions;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.pro.contract.function.ScreenTranslatorContract;
import com.hengmeng.hmkuaiyi.pro.entity.TextNode;
import com.hengmeng.hmkuaiyi.pro.model.function.ScreenTranslatorModelImpl;
import com.hengmeng.hmkuaiyi.pro.presenter.function.ScreenTranslatorPresenterImpl;
import com.hengmeng.hmkuaiyi.pro.screenfetcher.ScreenFetcher;
import com.hengmeng.hmkuaiyi.pro.screenfetcher.access.AccessScreenFetcher;
import com.hengmeng.hmkuaiyi.pro.widget.floatwindow.FetchingTriggerFloatWindow;
import com.xiaoxi.floatpermission.FloatPerUtil;

import java.util.ArrayList;

public class ScreenTranslatorService extends Service
        implements ScreenTranslatorContract.ScreenTranslatorView {
    private final String TAG = "ScreenFetching";

    // presenter
    private ScreenTranslatorPresenterImpl presenter;

    // 这个服务的实例
    public static ScreenTranslatorService instance;

    @Override
    public void createShowTriggerFloat() {
        // 每一次show需要重新创建触发器悬浮窗
        initShowTriggerFloat();
    }

    @Override
    public void destroyTriggerFloat() {
        FetchingTriggerFloatWindow.getInstance(this).destroyHide();
    }

    @Override
    public void onCreate() {
        instance = this;

        presenter = new ScreenTranslatorPresenterImpl();
        ScreenTranslatorModelImpl model = new ScreenTranslatorModelImpl(this);
        presenter.attach(model,this);

        createShowTriggerFloat();
        presenter.saveScreenFetchingOpenSettings(true);

        super.onCreate();
    }


    @Override
    public void onDestroy() {
        // 通知中介保存关闭取词功能的设置并更新UI（关闭触发器悬浮窗）
        presenter.saveFetchingCloseSettingsUpdateUI();
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 初始化并显示触发器（悬浮窗）
     *
     * 只是初始化才调用此方法
     *
     */
    private void initShowTriggerFloat(){
        if (! FloatPerUtil.isFloatPerOpen(this)){
            Toast.makeText(this, "请开启悬浮窗权限！", Toast.LENGTH_SHORT).show();
            return;
        }

        FetchingTriggerFloatWindow.getInstance(getApplicationContext()).show();
        FetchingTriggerFloatWindow.getInstance(getApplicationContext())
                .setOnActionListener(new FetchingTriggerFloatWindow.OnActionListener() {
                    // 获取到的屏幕节点对象(TextNode)
                    private ArrayList<TextNode> textNodes;

                    boolean isStartDragging = false;

                    FetchingTriggerFloatWindow triggerFloatWindow = FetchingTriggerFloatWindow.getInstance(getApplicationContext());

                    ScreenFetcher fetcher = new AccessScreenFetcher ();

                    @Override
                    public void onStartDragging() {
                        textNodes = null;

                        if (! triggerFloatWindow.isOpened() && !isStartDragging){
                            fetcher.startFetching(new ScreenFetcher.OnFetchingListener() {
                                @Override
                                public void onSuccess(ArrayList<TextNode> data) {
                                    textNodes =  data;
                                    if (triggerFloatWindow.isOpened()){
                                        startChoiceTextActivity(data);
                                        destroyTriggerFloat();
                                    }
                                }

                                @Override
                                public void onError(String errorMsg) {
                                    Toast.makeText(ScreenTranslatorService.this, errorMsg, Toast.LENGTH_SHORT).show();
                                    // 重置悬浮窗
                                    destroyTriggerFloat();
                                    createShowTriggerFloat();
                                }
                            });
                        }
                    }

                    @Override
                    public void onDragOpened() {
                        isStartDragging = false;

                        // 该舍弃的都舍弃了，还不为空就代表成功获取到了
                        if (textNodes != null){
                            startChoiceTextActivity(textNodes);
                            destroyTriggerFloat();
                            textNodes = null;
                        }
                    }

                    @Override
                    public void onDragClosed() {
                        isStartDragging = false;

                        textNodes = null;// 舍弃数据
                    }
                });
    }
    

    private void startChoiceTextActivity(ArrayList<TextNode> textNodes){

        Intent intent = new Intent(this, ChoiceTextActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putParcelableArrayListExtra("text_nodes", textNodes);
        startActivity(intent, ActivityOptions.makeCustomAnimation(this.getBaseContext(),
                android.R.anim.fade_in,android.R.anim.fade_out).toBundle());// 淡入淡出
    }

}
