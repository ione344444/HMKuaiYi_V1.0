package com.xiaoxi.translate.bean;

public class TransObject {
    private String fromText,toText;
    private String fromLgAbb, toLgAbb;

    public TransObject(String fromLgAbb, String fromText, String toLgAbb, String toText) {
        this.fromLgAbb = fromLgAbb;
        this.fromText = fromText;
        this.toLgAbb = toLgAbb;
        this.toText = toText;
    }

    public void setFromLgAbb(String fromLgAbb) {
        this.fromLgAbb = fromLgAbb;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
    }

    public void setToLgAbb(String toLgAbb) {
        this.toLgAbb = toLgAbb;
    }

    public void setToText(String toText) {
        this.toText = toText;
    }

    public String getFromLgAbb() {
        return fromLgAbb;
    }

    public String getFromText() {
        return fromText;
    }

    public String getToLgAbb() {
        return toLgAbb;
    }

    public String getToText() {
        return toText;
    }
}
