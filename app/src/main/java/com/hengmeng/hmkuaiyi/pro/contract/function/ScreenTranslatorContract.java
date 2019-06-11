package com.hengmeng.hmkuaiyi.pro.contract.function;

import com.hengmeng.hmkuaiyi.mvp.model.function.BaseScreenTranslatorModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.function.BaseScreenTranslatorView;

public interface ScreenTranslatorContract {
    interface ScreenTranslatorModel extends BaseScreenTranslatorModel {
        // 只需要将取词功能打开状态保存到本地设置
        void saveScreenFetchingOpenSettings(boolean open);

        // 只需要从本地设置中获取取词功能的打开状态
        boolean loadScreenFetchingOpenSettings();
    }

    interface ScreenTranslatorView extends BaseScreenTranslatorView {
        // 创建和显示触发器悬浮窗
        void createShowTriggerFloat();

        // 要关闭悬浮窗调用方法，销毁触发器悬浮窗
        void destroyTriggerFloat();
    }

    abstract class ScreenTranslatorPresenter extends BasePresenter<ScreenTranslatorModel, ScreenTranslatorView>{
        // 保存取词功能的打开状态到本地设置
        public abstract void saveScreenFetchingOpenSettings(boolean open);

        // 加载取词功能的打开状态从本地设置，只需返回一个boolean值，View拿到boolean值自己操作
        public abstract boolean loadScreenFetchingOpenSettings();

        // 从本地设置加载取词功能打开状态并更新UI，如显示触发器悬浮窗
        public abstract void loadFetchingOpenSettingsUpdateUI();

        // 保存取词功能关闭的本地设置，并更新UI，如销毁触发器悬浮窗
        public abstract void saveFetchingCloseSettingsUpdateUI();
    }
}
