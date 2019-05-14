package com.hengmeng.hmkuaiyi.pro.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.mvp.view.BaseMvpFinishBarActivity;

public abstract class BaseFinishBarActivity extends BaseMvpFinishBarActivity {
    @Override
    public void setFinishActionBar(String title) {
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

}
