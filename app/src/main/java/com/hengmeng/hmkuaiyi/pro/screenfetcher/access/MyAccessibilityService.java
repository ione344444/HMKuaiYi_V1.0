package com.hengmeng.hmkuaiyi.pro.screenfetcher.access;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService{
    private final String TAG = "ScreenFetching";

    public static MyAccessibilityService instance;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        instance = this;
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
