package com.hengmeng.hmkuaiyi.pro.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;


public class ClipBoard {

    /**
     * 该方法用于将指定内容保存至系统剪贴板
     *
     * @param context context对象
     * @param text 要保存的文本
     * @return 成功与否（空指针的判断）
     */
    public static boolean setClipBoardText(Context context,String text){
        ClipboardManager mClipboard = (ClipboardManager)context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("translationResults",text);

        if(mClipboard != null){
            mClipboard.setPrimaryClip(clipData);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 该方法用于获取系统剪贴板的内容
     *
     * @param context 上下文
     * @return 剪贴板内容
     */
    public static String getCiipBoardContent(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);

        ClipData clipData = null;
        if (cm != null) {
            clipData = cm.getPrimaryClip();
        }
        if(clipData == null) {
            return null;
        }
        return clipData.toString();
    }
}
