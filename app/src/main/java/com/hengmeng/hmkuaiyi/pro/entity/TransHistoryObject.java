package com.hengmeng.hmkuaiyi.pro.entity;
import com.xiaoxi.translate.bean.TransObject;

public class TransHistoryObject extends TransObject {
    private int historyId;

    public TransHistoryObject(int historyId, String fromLgAbb, String fromText, String toLgAbb, String toText) {
        super(fromLgAbb, fromText, toLgAbb, toText);
        this.historyId = historyId;
    }

    public TransHistoryObject(int historyId,TransObject transObject) {
        super (transObject.getFromLgAbb(),transObject.getFromText(),
                transObject.getToLgAbb(),transObject.getToText());
        this.historyId = historyId;
    }
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }
}
