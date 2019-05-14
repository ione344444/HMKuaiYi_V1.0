package com.hengmeng.hmkuaiyi.pro.presenter.main;

import android.content.Context;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.pro.contract.main.TransContract;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.xiaoxi.translate.TransError;
import com.xiaoxi.translate.bean.TransObject;

public class TransPresenterImpl extends TransContract.TransPresenter {
    private Context context;

    public TransPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void startTranslating(final TransObject transObject) {
        getModel().startTranslating(transObject, new TransListener() {
            @Override
            public void onSuccess(TransObject transResult) {
                getView().showResult(transResult);
            }

            @Override
            public void onError(String errorCode) {
                String errorMsg = "";

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
}
