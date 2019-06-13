package com.xiaoxi.translate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.xiaoxi.translate.api.TransApi;
import com.xiaoxi.translate.bean.TransObject;

import org.json.*;

/**
 * 翻译功能的调动类，使用这个类来开始翻译和获取结果
 */
public class JsonTransApi {
    private String appid,securityKey;

    private String fromText, fromLgAbb;
    private String toLgAbb;
    private TransObject transResult;
    private String jsonResult;

    private TransResultListener transResultListener;

    private Handler mTransHandler;

    public JsonTransApi(Context context) {
        mTransHandler = new MyTransHandler();

        appid = BaiduTransApi.getAppId();
        securityKey = BaiduTransApi.getSecurityKey();
    }

    /**
     * 调用此方法开始翻译
     */
    public void startTranslating(TransObject transObject, TransResultListener transResultListener) {
        this.fromText = transObject.getFromText();
        this.fromLgAbb = transObject.getFromLgAbb();
        this.toLgAbb = transObject.getToLgAbb();

        this.transResultListener = transResultListener;
        jsonTransResult();
    }

    private void jsonTransResult(){
        // 翻译过程包括网络请求，需要在子线程操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                startTranslating();
            }
        }).start();
    }

    private void startTranslating() {
        // 获取翻译到的Json数据
        TransApi api =new TransApi(appid,securityKey);
        jsonResult = api.getTransResult(fromText, fromLgAbb, toLgAbb);
        Log.e("JsonTransApi","fromText:" + fromText);
        Log.e("JsonTransApi","toLgAbb" + toLgAbb);

        transResult = new TransObject(fromLgAbb,fromText, toLgAbb,"");

        // 如果结果是空的就代表没有网络
        if(jsonResult == null || jsonResult.equals("")){
            Message msg = new Message();
            msg.what = 1;
            msg.obj = TransError.ErrorCode.UNABLE_CONNECT_SEVER;
            mTransHandler.sendMessage(msg);
            return;
        }

        // 解析json数据
        try {
            JSONObject jsonObject1 = new JSONObject(jsonResult);
            String str1 = jsonObject1.getString("trans_result");
            String str2 = str1.substring(1,str1.length() - 1);// 去掉中括号
            JSONObject jsonObject2 = new JSONObject(str2);

            transResult.setFromLgAbb(jsonObject1.getString("from"));
            transResult.setToLgAbb(jsonObject1.getString("to"));
            transResult.setFromText(jsonObject2.getString("src"));
            transResult.setToText(jsonObject2.getString("dst"));

            Message msg = new Message();
            msg.what = 0;
            mTransHandler.sendMessage(msg);
        } catch (JSONException e) {
            e.printStackTrace();
            // 错误码处理
            // 如果抛出异常则尝试解析错误码
            Message msg = new Message();
            msg.what = 1;
            try {
                JSONObject jsonObject = new JSONObject(jsonResult);
                String str = jsonObject.getString("error_code");

                msg.obj = str;
                mTransHandler.sendMessage(msg);
            } catch (JSONException e1) {
                e1.printStackTrace();

                msg.obj = "";
                mTransHandler.sendMessage(msg);
            }
        }
    }

    @SuppressLint("HandlerLeak")
    class MyTransHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (transResultListener != null){
                        transResultListener.onSuccess(transResult,jsonResult);
                    }
                    break;

                case 1:
                    if (transResultListener != null){
                        transResultListener.onError((String) msg.obj,jsonResult);
                    }
                    break;
            }
            super.handleMessage(msg);
        }

    }

    public interface TransResultListener{
        void onSuccess (TransObject transResult,String jsonResult);

        void onError(String errorCode,String jsonResult);
    }

}

