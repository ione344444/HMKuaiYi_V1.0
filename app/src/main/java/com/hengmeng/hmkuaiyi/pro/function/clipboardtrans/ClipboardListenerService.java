package com.hengmeng.hmkuaiyi.pro.function.clipboardtrans;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;

import com.hengmeng.hmkuaiyi.pro.function.FetchingTranslation;
import com.hengmeng.hmkuaiyi.pro.util.ClipBoard;

public class ClipboardListenerService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void addClipBordListener() {
        ClipboardManager cb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (cb == null) {
            return;
        }
        cb.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                doTransSomething(ClipBoard.getCiipBoardContent(
                        ClipboardListenerService.this));
            }
        });
    }

    private void doTransSomething(String cbContent) {
        FetchingTranslation fetchTrans = new FetchingTranslation(this);
        fetchTrans.showFloat();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
