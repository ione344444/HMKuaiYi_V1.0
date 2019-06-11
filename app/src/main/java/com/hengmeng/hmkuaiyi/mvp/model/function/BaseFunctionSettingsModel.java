package com.hengmeng.hmkuaiyi.mvp.model.function;

public interface BaseFunctionSettingsModel {
    void saveScreenFetchOpenSettings(boolean open);

    boolean loadScreenFetchOpenSettings();

    void saveClipboardTransOpenSettings(boolean open);

    boolean loadClipboardTransOpenSettings();
}
