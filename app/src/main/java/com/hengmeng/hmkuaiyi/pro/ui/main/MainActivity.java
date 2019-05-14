package com.hengmeng.hmkuaiyi.pro.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.main.MainContract;
import com.hengmeng.hmkuaiyi.pro.presenter.main.MainPresenterImpl;
import com.hengmeng.hmkuaiyi.pro.ui.function.FunctionActivity;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {
    private MainPresenterImpl presenter;

    protected HistoryUI historyUI;

    protected TransUI transUI;

    protected LanguageUI languageUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);

        historyUI = new HistoryUI(this);
        transUI = new TransUI(this);
        languageUI = new LanguageUI(this);

        // 功能界面跳转按钮
        findViewById(R.id.main_ib_function).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FunctionActivity.class));
            }
        });

        // 设置界面跳转按钮
        findViewById(R.id.main_ib_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        transUI.onWindowsFocusChanged(hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void showWordFetchingOpen(boolean open) {

    }

    @Override
    public void showClipboardTransOpen(boolean open) {

    }
}

