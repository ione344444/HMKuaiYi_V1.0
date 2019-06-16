package com.hengmeng.hmkuaiyi.pro.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.pro.entity.TransHistoryObject;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.hengmeng.hmkuaiyi.pro.contract.MainContract;
import com.xiaoxi.translate.TransError;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

public class MainPresenterImpl extends MainContract.MainPresenter {
    private Context context;

    public MainPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadLgSettingsData() {
        TransObject lgSettings = getModel().loadLgAbbSettings();
        String fromLgAbb = lgSettings.getFromLgAbb();
        String toLgAbb = lgSettings.getToLgAbb();

        Log.e("MainPresenterImpl","fromLgAbb:" + fromLgAbb);
        Log.e("MainPresenterImpl","toLgAbb:" + toLgAbb);
        getView().showFromLgSettings(fromLgAbb.equals("") ? "auto" : fromLgAbb);
        getView().showToLgSettings(toLgAbb.equals("") ? "auto" : toLgAbb);
    }


    @Override
    public void loadAllLanguageAbbUpdateUI() {
        List<String> languages = getModel().getAllLanguageAbb();
        getView().createAllLgView(languages);
    }

    @Override
    public void saveFromLgSettingsUpdateUI(String fromLgAbb) {
        getModel().saveFromLgSettings(fromLgAbb);

        getView().showFromLgSettings(fromLgAbb);
    }

    @Override
    public void saveToLgSettingsUpdateUI(String toLgAbb) {
        getModel().saveToLgSettings(toLgAbb);

        getView().showToLgSettings(toLgAbb);
    }

    @Override
    public void startTranslating(final TransObject transObject) {
        if (transObject.getFromText().isEmpty()){
            return;
        }
        getModel().startTranslating(transObject, new TransListener() {
            @Override
            public void onSuccess(TransObject transResult) {
                getView().showResult(transResult);
            }

            @Override
            public void onError(String errorCode) {
                String errorMsg;

                switch (errorCode){
                    case TransError.ErrorCode.UNABLE_CONNECT_SEVER:/*无法连接到服务器*/
                        errorMsg = "无法连接至服务器，请检查你的网络";
                        break;

                    case TransError.BaiduErrorCode.REQUEST_TIMEOUT:/*请求超时*/
                        errorMsg = "请求超时，请重试";
                        break;

                    case TransError.BaiduErrorCode.SYSTEM_ERROR:/*系统错误*/
                        errorMsg = "系统错误，请重试";
                        break;

                    case TransError.BaiduErrorCode.VISIT_FREQUNT:/*访问频率受限*/
                        errorMsg = "访问过于频繁";
                        break;

                    case TransError.BaiduErrorCode.UNSUPPORTED_LANGUAGE:/*目标语种方向不支持*/
                        errorMsg = "目标语种方向不支持";
                        break;

                    case TransError.BaiduErrorCode.SEVER_OVER:/*服务器关闭*/
                        errorMsg = "很抱歉，服务器当前已关闭";
                        break;

                    default:
                        errorMsg = "预期外的错误";
                        break;
                }

                errorMsg += "(" + errorCode +")";
                getView().showToast(errorMsg, Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void addHistoryDataUpdateUI(TransObject transObject) {
        getModel().addHistoryData(transObject);

        getView().refreshHistory(getModel().loadAllHistoryData());
    }

    @Override
    public void loadHistoryData() {
        List<TransHistoryObject> historys = getModel().loadAllHistoryData();
        getView().showHistory(historys);
    }
}
