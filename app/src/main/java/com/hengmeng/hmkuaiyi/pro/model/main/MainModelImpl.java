package com.hengmeng.hmkuaiyi.pro.model.main;

import com.hengmeng.hmkuaiyi.pro.contract.main.MainContract;

public class MainModelImpl implements MainContract.MainModel {
    @Override
    public boolean getWordFetchOpenState() {

        return false;
    }

    @Override
    public boolean getClipboardOpenState() {
        return false;
    }
}
