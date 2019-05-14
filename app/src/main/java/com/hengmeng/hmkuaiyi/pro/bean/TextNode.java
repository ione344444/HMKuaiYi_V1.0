package com.hengmeng.hmkuaiyi.pro.bean;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class TextNode implements Parcelable {
    private Rect bound;
    private String content;

    public static Creator<TextNode> CREATOR = new Creator<TextNode>() {
        @Override
        public TextNode createFromParcel(Parcel p1) {
            return new TextNode(p1);
        }

        @Override
        public TextNode[] newArray(int size) {
            return new TextNode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bound.left);
        dest.writeInt(this.bound.top);
        dest.writeInt(this.bound.right);
        dest.writeInt(this.bound.bottom);
        dest.writeString(this.content);
    }

    private TextNode(Parcel p1){
        this.bound = new Rect(p1.readInt(),p1.readInt(),p1.readInt(),p1.readInt());
        this.content = p1.readString();
    }

    public TextNode(Rect bound,String content){
        this.bound = bound;
        this.content = content;
    }

    public Rect getBound(){
        return bound;
    }

    public String getContent() {
        return content;
    }

    /**
     * 计算bound的面积
     *
     * @return bound的面积
     */
    public long getBoundSize(){
        return bound.width()*bound.height();
    }

    @NonNull
    @Override
    public String toString() {
        return "ContentNode{" +
                "bound=" + bound +
                ", content='" + content + '\'' +
                '}';
    }
}
