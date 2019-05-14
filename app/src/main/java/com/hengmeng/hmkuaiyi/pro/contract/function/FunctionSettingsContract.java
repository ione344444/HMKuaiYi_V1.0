package com.hengmeng.hmkuaiyi.pro.contract.function;

import com.hengmeng.hmkuaiyi.mvp.model.function.BaseFunctionSettingsModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.function.BaseFunctionSettingsView;

public interface FunctionSettingsContract {
    interface FunctionSettingsModel extends BaseFunctionSettingsModel {

    }

    interface FunctionSettingsView extends BaseFunctionSettingsView {
        void showFloatPerRequestView();

        void hideFloatPerRequestView();
    }

    abstract class FunctionSettingsPresenter extends
            BasePresenter<FunctionSettingsModel, FunctionSettingsView>{
        // 从本地加载剪贴板翻译功能的打开状态
        public abstract void loadClipboardOpenSettings();

        // 从本地加载屏幕取词翻译的打开状态
        public abstract void loadScreenFetchOpenSettings();

        // 保存剪贴板翻译功能的打开状态
        public abstract void saveClipboardTransSettings(boolean open);

        // 保存屏幕取词翻译功能的打开状态
        public abstract void saveScreenFetchOpenSettings(boolean open);

        // 检查权限开启状态
        public abstract void checkPerOpenState();
    }
}
