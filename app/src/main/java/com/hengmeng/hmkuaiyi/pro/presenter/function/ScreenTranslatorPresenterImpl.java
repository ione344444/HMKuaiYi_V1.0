package com.hengmeng.hmkuaiyi.pro.presenter.function;

import com.hengmeng.hmkuaiyi.pro.contract.function.ScreenTranslatorContract;

public class ScreenTranslatorPresenterImpl extends ScreenTranslatorContract.ScreenTranslatorPresenter {
    @Override
    public void saveScreenFetchingOpenSettings(boolean open) {
        getModel().saveScreenFetchingOpenSettings(open);
    }

    @Override
    public boolean loadScreenFetchingOpenSettings() {
        return getModel().loadScreenFetchingOpenSettings();
    }

    @Override
    public void loadFetchingOpenSettingsUpdateUI() {
        if (loadScreenFetchingOpenSettings()){
            getView().createShowTriggerFloat();
        }
    }

    @Override
    public void saveFetchingCloseSettingsUpdateUI() {
        saveScreenFetchingOpenSettings(false);

        getView().destroyTriggerFloat();
    }
}
