package com.hengmeng.hmkuaiyi.pro.model.main;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.contract.main.HistoryContract;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_TransHistoryData;
import com.hengmeng.hmkuaiyi.pro.bean.TransHistoryObject;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

public class HistoryModelImpl implements HistoryContract.HistoryModel {
    private Context context;

    private SP_TransHistoryData sp_transHistoryData;

    public HistoryModelImpl(Context context){
        this.context = context;
        sp_transHistoryData = new SP_TransHistoryData(context);
    }

    @Override
    public List<TransHistoryObject> loadHistoryData() {
        return sp_transHistoryData.getAllHistoryData();
    }

    public void addHistoryData(TransObject transObject){
        sp_transHistoryData.addHistoryAtEnd(transObject);
    }

    public void deleteHistoryData(TransHistoryObject transHistoryObject){
        deleteHistoryData(transHistoryObject.getHistoryId());
    }

    public void deleteHistoryData(int historyId){
        sp_transHistoryData.deleteHistoryUseId(historyId);
    }
}
