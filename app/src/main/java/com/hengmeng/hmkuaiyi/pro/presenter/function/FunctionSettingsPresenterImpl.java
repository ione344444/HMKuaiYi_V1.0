package com.hengmeng.hmkuaiyi.pro.presenter.function;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.function.FunctionSettingsContract;
import com.hengmeng.hmkuaiyi.pro.util.AccessibilityServiceUtil;
import com.hengmeng.hmkuaiyi.pro.view.function.ScreenTranslatorService;

public class FunctionSettingsPresenterImpl extends FunctionSettingsContract.FunctionSettingsPresenter {
    private Context context;

    public FunctionSettingsPresenterImpl(Context context){
        this.context = context;
    }

    @Override
    public void loadClipboardOpenSettingsUpdateUI() {
        getView().showClipboardTransOpen(getModel().loadClipboardTransOpenSettings());
    }

    @Override
    public void loadScreenFetchOpenSettingsUpdateUI() {
        getView().showScreenFetchingOpen(getModel().loadScreenFetchOpenSettings()
                && ScreenTranslatorService.instance != null);
    }

    @Override
    public void saveClipboardTransSettingsUpdateUI(boolean open) {
        getModel().saveClipboardTransOpenSettings(open);

        getView().showClipboardTransOpen(open);
    }

    @Override
    public void saveScreenFetchOpenSettingsUpdateUI(boolean open) {
        getModel().saveScreenFetchOpenSettings(open);

        getView().showScreenFetchingOpen(open);
    }

}
