package com.hengmeng.hmkuaiyi.pro.view;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.base.BaseFinishBarActivity;

public class SettingsActivity extends BaseFinishBarActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void init() {

    }

    @Override
    protected String getFinishBarTitle() {
        return "设置";
    }
}
