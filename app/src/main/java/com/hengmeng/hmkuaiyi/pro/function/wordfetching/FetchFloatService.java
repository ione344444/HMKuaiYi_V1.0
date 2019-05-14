package com.hengmeng.hmkuaiyi.pro.function.wordfetching;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;

import com.hengmeng.hmkuaiyi.pro.util.AccessibilityServiceManager;

/**
 * 创建屏幕取词悬浮窗的服务，不承担取词功能
 * 开启取词功能会启动该服务，由此服务来引导用户开启具体的辅助服务（承担取词功能的服务）
 */
public class FetchFloatService extends Service {

    /* 取词悬浮窗对象 */
    private WordFetchFloat fetchFloat;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchFloat = new WordFetchFloat(this);
        fetchFloat.show();
        fetchFloat.setActionListener(new WordFetchFloat.ActionListener() {
            @Override
            public void onStartFetch(View view) {
                // 直接跳转无障碍界面
                AccessibilityServiceManager.gotoAccessibilityService(
                        FetchFloatService.this);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fetchFloat.colose();// 服务结束关闭悬浮窗
    }
}
