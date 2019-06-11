package com.hengmeng.hmkuaiyi.pro.view;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.view.function.ScreenTranslatorService;

import java.lang.reflect.Method;

public class NotificationToolsBarService extends Service {
    public final String CHANNEL_ID = "tools";

    public final int notificationId = 10;

    public final String ACTION_BUTTON_OPEN_ST = "com.notification.intent.action.ButtonClickOpenST";
    public final String ACTION_BUTTON_CLOSE = "com.notification.intent.action.ButtonClickClose";

    public static NotificationToolsBarService instance = null;

    // 取词服务开启状态
    private boolean isSTOpen = false;

    private NotificationManager notificationManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        isSTOpen = ScreenTranslatorService.instance != null;

        // 8.0及以上需要创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = CHANNEL_ID;
            String name = "工具栏";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(id,name,importance);
        }

        createNotification();
    }

    /**
     * 创建通知渠道的实现，8.0以上需要
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId,String channelName,int importance) {
        NotificationChannel channel = new NotificationChannel(channelId,channelName,importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * 创建和显示一个通知的实现
     */
    private void createNotification() {
        notificationManager = (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

        // 用广播实现点击事件
        ButtonBroadCaseReceiver receiver = new ButtonBroadCaseReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BUTTON_OPEN_ST);
        intentFilter.addAction(ACTION_BUTTON_CLOSE);
        registerReceiver(receiver,intentFilter);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_notification_tools_bar);

        // 注册屏幕翻译开关的点击事件
        Intent openSTIntent = new Intent(ACTION_BUTTON_OPEN_ST);
        PendingIntent openSTPendingIntent = PendingIntent.getBroadcast(this,R.id.iv_notification_openST,
                openSTIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_notification_openST,openSTPendingIntent);

        // 注册关闭通知按钮的点击事件
        Intent closeIntent = new Intent(ACTION_BUTTON_CLOSE);
        PendingIntent closePendingIntent = PendingIntent.getBroadcast(this,R.id.iv_notification_close,
                closeIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_notification_close,closePendingIntent);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContent(remoteViews);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;// 设置通知为常驻

        notificationManager.notify(notificationId, notification);
    }


    /**
     * 处理通知事件的广播
     */
    public class ButtonBroadCaseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (ACTION_BUTTON_OPEN_ST.equals(action)) {//   click:取词服务开关
                if (isSTOpen) {//   关闭
                    isSTOpen = false;
                    stopScreenTranslatorService();
                    Toast.makeText(context, "暂时关闭取词功能", Toast.LENGTH_SHORT).show();
                }else {//   开启
                    isSTOpen = true;
                    startScreenTranslatorService();
                }
                // 收起通知栏
                collapseStatusBar(NotificationToolsBarService.this);
            }else if (ACTION_BUTTON_CLOSE.equals(action)) {//   click:关闭通知
                 notificationManager.cancel(notificationId);
            }
        }
    }

    /**
     * 开启取词服务的实现
     */
    private void startScreenTranslatorService() {
        Intent intentService = new Intent(NotificationToolsBarService.this,
                ScreenTranslatorService.class);
        startService(intentService);
    }

    /**
     * 停止取词服务的实现
     */
    private void stopScreenTranslatorService() {
        Intent intentService = new Intent(NotificationToolsBarService.this,
                ScreenTranslatorService.class);
        stopService(intentService);
    }

    /**
     * 收起通知栏的实现
     */
    private void collapseStatusBar(Context context) {
        try {
            Object statusBarManager = context.getSystemService("statusbar");
            Method collapse;
            collapse = statusBarManager.getClass().getMethod("collapsePanels");
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}
