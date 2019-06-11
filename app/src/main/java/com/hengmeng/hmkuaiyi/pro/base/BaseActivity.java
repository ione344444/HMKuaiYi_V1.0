package com.hengmeng.hmkuaiyi.pro.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.Nullable;

/**
 * 目前该类用于很方便的获取栈顶的Activity
 */
public abstract class BaseActivity extends Activity {
    @SuppressLint("StaticFieldLeak")
    private static Activity topActivity ;

    @Override
    protected void onStart() {
        topActivity = this;
        super.onStart();
    }

    /**
     * 获取栈顶的Activity
     */
    @Nullable
    public static Activity getTopActivity() {
        return topActivity;
    }
}
