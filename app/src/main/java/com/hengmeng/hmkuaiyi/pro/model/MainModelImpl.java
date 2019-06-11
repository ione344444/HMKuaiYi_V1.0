package com.hengmeng.hmkuaiyi.pro.model;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.entity.TransHistoryObject;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.hengmeng.hmkuaiyi.pro.contract.MainContract;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_LanguageSettingsData;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_TransHistoryData;
import com.xiaoxi.translate.JsonTransApi;
import com.xiaoxi.translate.Language;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

public class MainModelImpl implements MainContract.MainModel {

    private Context context;

    public MainModelImpl(Context context) {
        this.context = context;
    }

/*-********************************************** Language **************************************************-*/

    @Override
    public List<String> getAllLanguageAbb() {
        return Language.getAllLanguageAbb();
    }

    @Override
    public void saveFromLgSettings(String fromLgAbb) {
        SP_LanguageSettingsData.getInstance(context).saveFromLgAbbData(fromLgAbb);
    }

    @Override
    public void saveToLgSettings(String toLgAbb) {
        SP_LanguageSettingsData.getInstance(context).saveToLgAbbData(toLgAbb);
    }

    @Override
    public TransObject loadLgAbbSettings() {
        return SP_LanguageSettingsData.getInstance(context).loadLgAbbData();
    }

/*-********************************************** Translating **************************************************-*/

    @Override
    public void startTranslating(TransObject transObject, final TransListener transListener) {
        JsonTransApi jsonTransApi = new JsonTransApi(context);
        jsonTransApi.startTranslating(transObject, new JsonTransApi.TransResultListener() {

            @Override
            public void onSuccess(TransObject transResult, String jsonResult) {
                transListener.onSuccess(transResult);
            }

            @Override
            public void onError(String errorCode, String jsonResult) {
                transListener.onError(errorCode);
            }
        });
    }

/*-********************************************** History **************************************************-*/

    @Override
    public List<TransHistoryObject> loadAllHistoryData() {
        return SP_TransHistoryData.getInstance(context).getAllHistoryData();
    }

    @Override
    public void addHistoryData(TransObject transObject){
        SP_TransHistoryData.getInstance(context).addHistoryAtEnd(transObject);
    }

    @Override
    public void deleteHistoryData(int historyId){
        SP_TransHistoryData.getInstance(context).deleteHistoryUseId(historyId);
    }
}
