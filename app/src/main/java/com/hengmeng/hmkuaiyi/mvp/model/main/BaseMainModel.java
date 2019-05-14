package com.hengmeng.hmkuaiyi.mvp.model.main;

/**
 * 每一个界面，尤其是主界面包含了很多杂项设置，没必要再去创建新类，
 * 此Model用于处理那些杂项设置
 */
public interface BaseMainModel {
    boolean getWordFetchOpenState();

    boolean getClipboardOpenState();
}
