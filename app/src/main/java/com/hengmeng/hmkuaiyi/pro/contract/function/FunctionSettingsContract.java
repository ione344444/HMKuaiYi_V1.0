package com.hengmeng.hmkuaiyi.pro.contract.function;

import com.hengmeng.hmkuaiyi.mvp.model.function.BaseFunctionSettingsModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.function.BaseFunctionSettingsView;

public interface FunctionSettingsContract {
    interface FunctionSettingsModel extends BaseFunctionSettingsModel {

    }

    interface FunctionSettingsView extends BaseFunctionSettingsView {
    }

    abstract class FunctionSettingsPresenter extends
            BasePresenter<FunctionSettingsModel, FunctionSettingsView>{
        // 从本地设置加载剪贴板翻译功能的打开状态，并更新UI
        public abstract void loadClipboardOpenSettingsUpdateUI();

        // 从本地加载屏幕取词翻译的打开状态,并且辅助服务正常打开才算打开
        public abstract void loadScreenFetchOpenSettingsUpdateUI();

        // 保存剪贴板翻译功能的打开状态，并更新开关打开状态
        public abstract void saveClipboardTransSettingsUpdateUI(boolean open);

        // 保存屏幕取词翻译功能的打开状态，并更新开关打开状态
        public abstract void saveScreenFetchOpenSettingsUpdateUI(boolean open);

    }
}
