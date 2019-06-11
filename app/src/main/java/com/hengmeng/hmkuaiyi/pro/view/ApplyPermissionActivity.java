package com.hengmeng.hmkuaiyi.pro.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.base.BaseActivity;
import com.hengmeng.hmkuaiyi.pro.base.BaseFinishBarActivity;
import com.hengmeng.hmkuaiyi.pro.view.function.FunctionActivity;
import com.xiaoxi.floatpermission.FloatPerUtil;

public class ApplyPermissionActivity extends BaseFinishBarActivity {

    private Button btn_next;
    private ImageButton ib_openFloatPer;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_apply_permissions;
    }

    @Override
    protected void init() {
        ib_openFloatPer = findViewById(R.id.ib_floatPer_gotoOpen);
        if (FloatPerUtil.isFloatPerOpen(this)) {
            ib_openFloatPer.setImageDrawable(getDrawable(R.drawable.ic_check_grey_600_18dp));
        }else {
            ib_openFloatPer.setImageDrawable(getDrawable(R.drawable.ic_arrow_forward_grey_600_18dp));
        }
        ib_openFloatPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatPerUtil.goToPermissionActivity(ApplyPermissionActivity.this);
            }
        });

        btn_next = findViewById(R.id.btn_nextStep);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApplyPermissionActivity.this, FunctionActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateUiByFloatPermissionOpenState();
        updateUiByReadyState();
    }

    @Override
    protected String getFinishBarTitle() {
        return "准备";
    }

    private void updateUiByFloatPermissionOpenState() {
        if (FloatPerUtil.isFloatPerOpen(this)) {
            ib_openFloatPer.setImageDrawable(getDrawable(R.drawable.ic_check_grey_600_18dp));
        }else {
            ib_openFloatPer.setImageDrawable(getDrawable(R.drawable.ic_arrow_forward_grey_600_18dp));
        }
    }

    private void updateUiByReadyState() {
        if (FloatPerUtil.isFloatPerOpen(this)) {
            btn_next.setClickable(true);
            btn_next.setAlpha(1);
        }else {
            btn_next.setClickable(false);
            btn_next.setAlpha(0.4f);
        }
    }
}
