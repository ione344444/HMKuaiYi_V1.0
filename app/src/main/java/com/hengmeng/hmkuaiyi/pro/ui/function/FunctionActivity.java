package com.hengmeng.hmkuaiyi.pro.ui.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.function.FunctionSettingsContract;
import com.hengmeng.hmkuaiyi.pro.model.function.FunctionSettingsModelImpl;
import com.hengmeng.hmkuaiyi.pro.presenter.function.FunctionSettingsPresenterImpl;
import com.hengmeng.hmkuaiyi.pro.ui.BaseFinishBarActivity;
import com.hengmeng.hmkuaiyi.pro.view.floatwindow.FunctionFloatBoxWindow;
import com.hengmeng.hmkuaiyi.pro.view.floatwindow.StartFetchFloatWindow;

public class FunctionActivity extends BaseFinishBarActivity implements FunctionSettingsContract.FunctionSettingsView {
    private Switch sc_function_SF,sc_function_CT;

    private FunctionSettingsModelImpl model;

    private FunctionSettingsPresenterImpl presenter;

    private AlertDialog dialogShow;
    private AlertDialog.Builder dialogBuilder;

    @Override
    public int getContentViewId() {
        return R.layout.activity_function;
    }

    @Override
    public void init() {
        model = new FunctionSettingsModelImpl(this);
        presenter = new FunctionSettingsPresenterImpl(this);

        sc_function_SF = findViewById(R.id.sh_function_SF);
        sc_function_CT = findViewById(R.id.sh_function_CT);
        sc_function_SF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(FunctionActivity.this,
                        ScreenFetchingAccessService.class);
                if (isChecked){
                    FunctionActivity.this.startService(intent);
                }else {
                    FunctionActivity.this.stopService(intent);
                    // 当辅助服务开启时，stopService()就无法停止服务了，只能手动关闭悬浮窗
                    FunctionFloatBoxWindow.getInstance(FunctionActivity.this).destroyHide();

                    StartFetchFloatWindow.getInstance(FunctionActivity.this).destroyHide();
                }
            }
        });
    }

    @Override
    public String getFinishBarTitle() {
        return "功能";
    }

    @Override
    public void showScreenFetchOpen(boolean open) {
        sc_function_SF.setChecked(open);
    }

    @Override
    public void showClipboardTransOpen(boolean open) {
        sc_function_CT.setChecked(open);
    }

    @Override
    public void showFloatPerRequestView() {
        if (dialogBuilder == null){
            createFloatPerDialog();
        }

        dialogShow = dialogBuilder.show();
    }

    @Override
    public void hideFloatPerRequestView() {
        if (dialogShow != null){
            dialogShow.dismiss();
        }
    }

    public void createFloatPerDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("缺少权限");
        dialogBuilder.setMessage("部分功能需要创建悬浮窗，缺乏悬浮窗权限将导致功能无法正常使用。");
        dialogBuilder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
