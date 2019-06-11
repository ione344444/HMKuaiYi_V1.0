package com.hengmeng.hmkuaiyi.pro.screenfetcher;

import com.hengmeng.hmkuaiyi.pro.entity.TextNode;

import java.util.ArrayList;

/**
 * 取词功能接口，所有取词功能实现都需继承于他
 *
 */
public interface ScreenFetcher {
    void startFetching (OnFetchingListener listener);

    interface OnFetchingListener {
        void onSuccess(ArrayList<TextNode> data);

        void onError(String errorMsg);
    }
}
