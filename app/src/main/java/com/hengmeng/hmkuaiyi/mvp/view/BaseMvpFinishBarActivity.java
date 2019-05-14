package com.hengmeng.hmkuaiyi.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseMvpFinishBarActivity extends Activity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        setFinishActionBar(getFinishBarTitle());
        init();
    }

    public abstract int getContentViewId();

    public abstract void setFinishActionBar(String title);

    public abstract void init();

    public abstract String getFinishBarTitle();
}
