package com.hengmeng.hmkuaiyi.pro.presenter.function;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.function.FunctionSettingsContract;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_FunctionSettingsData;

public class FunctionSettingsPresenterImpl extends FunctionSettingsContract.FunctionSettingsPresenter {
    private Context context;

    public FunctionSettingsPresenterImpl(Context context){
        this.context = context;
    }

    @Override
    public void loadClipboardOpenSettings() {
        getView().showClipboardTransOpen(getModel().loadClipboardTransOpenSettings());
    }

    @Override
    public void loadScreenFetchOpenSettings() {
        getView().showScreenFetchOpen(getModel().loadScreenFetchOpenSettings());
    }

    @Override
    public void saveClipboardTransSettings(boolean open) {
        getModel().saveClipboardTransOpenSettings(open);
    }

    @Override
    public void saveScreenFetchOpenSettings(boolean open) {
        getModel().saveScreenFetchOpenSettings(open);
    }

    @Override
    public void checkPerOpenState() {

    }
}
