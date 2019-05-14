package com.hengmeng.hmkuaiyi.pro.contract;

import com.xiaoxi.translate.bean.TransObject;

public interface TransListener {
    void onSuccess(TransObject transResult);

    void onError(String errorCode);
}
