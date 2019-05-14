package com.hengmeng.hmkuaiyi.pro.presenter.main;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.main.HistoryContract;
import com.hengmeng.hmkuaiyi.pro.bean.TransHistoryObject;

import java.util.List;

public class HistoryPresenterImpl extends HistoryContract.HistoryPresenter {
    private Context context;

    public HistoryPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadHistoryData() {
        List<TransHistoryObject> historys = getModel().loadHistoryData();
        getView().showHistory(historys);
    }


}
