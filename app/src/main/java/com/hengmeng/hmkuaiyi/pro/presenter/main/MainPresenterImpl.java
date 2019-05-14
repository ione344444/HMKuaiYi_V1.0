package com.hengmeng.hmkuaiyi.pro.presenter.main;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.main.MainContract;

public class MainPresenterImpl extends MainContract.MainPresenter {
    private Context context;

    public MainPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    protected void loadSettingsData() {

    }
}
