package com.hengmeng.hmkuaiyi.pro.model.main;

import android.content.Context;

import com.hengmeng.hmkuaiyi.pro.constant.BaiduAppid;
import com.hengmeng.hmkuaiyi.pro.contract.main.TransContract;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.xiaoxi.translate.BaiduTransApi;
import com.xiaoxi.translate.JsonTransApi;
import com.xiaoxi.translate.bean.TransObject;

public class TransModelImpl implements TransContract.TransModel {
    private Context context;

    public TransModelImpl(Context context) {
        this.context = context;

        BaiduTransApi.init(context, BaiduAppid.APPID,BaiduAppid.SECURITYKEY);
    }

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

}
