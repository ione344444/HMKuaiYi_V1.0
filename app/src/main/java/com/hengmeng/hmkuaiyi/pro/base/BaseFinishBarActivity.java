package com.hengmeng.hmkuaiyi.pro.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hengmeng.hmkuaiyi.R;

public abstract class BaseFinishBarActivity extends BaseActivity {
    private void setFinishActionBar(String title) {
        ImageButton ib_back = findViewById(R.id.bar_ib_back);
        TextView tv_title = findViewById(R.id.bar_tv_title);

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_title.setText(title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        setFinishActionBar(getFinishBarTitle());

        init();

        setStatusBarColor();
    }

    protected abstract int getContentViewId();

    protected abstract void init();

    protected abstract String getFinishBarTitle();

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.coolGray));// 冷灰色
    }

}
