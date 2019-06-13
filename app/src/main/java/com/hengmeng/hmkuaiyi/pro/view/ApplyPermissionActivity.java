package com.hengmeng.hmkuaiyi.pro.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.base.BaseFinishBarActivity;
import com.hengmeng.hmkuaiyi.pro.view.function.FunctionActivity;
import com.xiaoxi.floatpermission.FloatPerUtil;

public class ApplyPermissionActivity extends BaseFinishBarActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_apply_permissions;
    }

    @Override
    protected void init() {
        ImageButton ib_openFloatPer = findViewById(R.id.ib_floatPer_gotoOpen);
        ib_openFloatPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatPerUtil.goToPermissionActivity(ApplyPermissionActivity.this);
            }
        });

        Button btn_next = findViewById(R.id.btn_nextStep);
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


    /**
     * 根据悬浮窗权限开启状态来更新悬浮窗权限跳转按钮图标的实现
     */
    private void updateUiByFloatPermissionOpenState() {
        ImageButton ib_openFloatPer = findViewById(R.id.ib_floatPer_gotoOpen);
        if (FloatPerUtil.isFloatPerOpen(this)) {
            ib_openFloatPer.setImageDrawable(getDrawable(R.drawable.ic_check_grey_600_18dp));
        }else {
            ib_openFloatPer.setImageDrawable(getDrawable(R.drawable.ic_arrow_forward_grey_600_18dp));
        }
    }

    /**
     * 判断用户是否做好了准备工作，来禁用或是启用下一步按钮的实现
     */
    private void updateUiByReadyState() {
        Button btn_next = findViewById(R.id.btn_nextStep);
        if (FloatPerUtil.isFloatPerOpen(this)) {
            btn_next.setClickable(true);
            btn_next.setAlpha(1);
        }else {
            btn_next.setClickable(false);
            btn_next.setAlpha(0.4f);
        }
    }
}
