package com.hengmeng.hmkuaiyi.pro.view.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.function.FunctionSettingsContract;
import com.hengmeng.hmkuaiyi.pro.model.function.FunctionSettingsModelImpl;
import com.hengmeng.hmkuaiyi.pro.presenter.function.FunctionSettingsPresenterImpl;
import com.hengmeng.hmkuaiyi.pro.base.BaseFinishBarActivity;
import com.hengmeng.hmkuaiyi.pro.screenfetcher.access.AccessScreenFetcher;
import com.hengmeng.hmkuaiyi.pro.screenfetcher.access.MyAccessibilityService;
import com.hengmeng.hmkuaiyi.pro.util.AccessibilityServiceUtil;
import com.hengmeng.hmkuaiyi.pro.view.NotificationToolsBarService;

import org.w3c.dom.Text;

public class FunctionActivity extends BaseFinishBarActivity implements FunctionSettingsContract.FunctionSettingsView {
    private FunctionSettingsPresenterImpl presenter;

    private AlertDialog openAccessServiceDialog;

    /*-*************************************** BaseFinishBarActivity抽象方法实现 ***********************************-*/

    @Override
    protected int  getContentViewId() {
        return R.layout.activity_function;
    }

    /**
     * 父类BaseFinishBarActivity在 onCreate() 中隐藏并绘制好了带标题的状态栏会调用此方法
     */
    @Override
    protected void init() {
        FunctionSettingsModelImpl model = new FunctionSettingsModelImpl(this);
        presenter = new FunctionSettingsPresenterImpl();
        presenter.attach(model,this);

        // 加载复制翻译和屏幕取词功能的打开状态
        presenter.loadClipboardOpenSettingsUpdateUI();
        presenter.loadScreenFetchOpenSettingsUpdateUI();

        // 处理功能开关，比如监听打开状态什么的
        switchCheckedHandle();
    }

    @Override
    protected String getFinishBarTitle() {
        return "功能";
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

/*-*********************************************** mvp-view的抽象方法实现 ****************************************-*/

    @Override
    public void showScreenFetchingOpen(boolean open) {
        Switch sh_function_SF = findViewById(R.id.sh_function_SF);
        sh_function_SF.setChecked(open);
    }

    @Override
    public void showClipboardTransOpen(boolean open) {
        Switch sh_function_CT = findViewById(R.id.sh_function_CT);
        sh_function_CT.setChecked(open);
    }


/*-****************************************** 对switch，功能打开和关闭的处理 ******************************************-*/

    private void switchCheckedHandle(){
        /* 屏幕取词 */
        final Switch sh_function_SF = findViewById(R.id.sh_function_SF);
        sh_function_SF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){ // open
                    openScreenFetchingFunction();
                    // 如果没开启辅助服务，引导用户开启辅助服务
                    if (! AccessibilityServiceUtil.isAccessServiceOpen(FunctionActivity.this,
                            MyAccessibilityService.class.getCanonicalName())){
                        showOpenAccessibilityServiceDialog();
                    }
                }else { //  close
                    closeScreenFetchingFunction();// 关闭取词功能
                }
            }
        });

        /* 剪贴板翻译 */
        final Switch sh_function_CT = findViewById(R.id.sh_function_CT);
    }


    /**
     * 开启取词功能
     *
     * 重复调用此方法不会出bug
     */
    private void openScreenFetchingFunction(){
        presenter.saveScreenFetchOpenSettingsUpdateUI(true);

        if (ScreenTranslatorService.instance != null){
            // 取词服务创建时便会显示悬浮窗，但有时取词翻译服务运行时悬浮窗却可能会被关闭
            // 所以在这里单独显示悬浮窗
            ScreenTranslatorService.instance.createShowTriggerFloat();
        }else {
            // 开启取词翻译服务
            Intent intent = new Intent(this,ScreenTranslatorService.class);
            startService(intent);
        }

        // 开启并不重复开启通知服务
        if (NotificationToolsBarService.instance == null){
            Intent intentService = new Intent(this,NotificationToolsBarService.class);
            startService(intentService);
        }
    }


    /**
     * 开启取词功能
     *
     * 重复调用此方法不会出bug
     */
    private void closeScreenFetchingFunction(){
        // 关闭悬浮窗
        if (ScreenTranslatorService.instance != null){
            ScreenTranslatorService.instance.destroyTriggerFloat();
        }
        presenter.saveScreenFetchOpenSettingsUpdateUI(false);
    }

/*-************************************ 屏幕取词功能开启的准备工作:权限申请等 *************************************-*/

    /**
     * 对话框：提示开启辅助服务
     */
    private void showOpenAccessibilityServiceDialog(){
        View openAccessServiceDialogView = View.inflate(this, R.layout.dialog_open_accessibility_service, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(openAccessServiceDialogView);
        openAccessServiceDialog = builder.create();
        openAccessServiceDialog.setCanceledOnTouchOutside(false);
        openAccessServiceDialog.show();

        final TextView tv_goto = openAccessServiceDialogView.findViewById(R.id.tv_openAccessibilityService);
        final TextView tv_cancel = openAccessServiceDialogView.findViewById(R.id.tv_cancel_openAccessService);

        tv_goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessibilityServiceUtil.gotoAccessibilityService(FunctionActivity.this);
                openAccessServiceDialog.dismiss();
            }
        });

        // 取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessServiceDialog.dismiss();
            }
        });
    }
}
