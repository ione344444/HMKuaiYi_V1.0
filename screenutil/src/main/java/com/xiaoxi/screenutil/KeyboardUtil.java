package com.xiaoxi.screenutil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class KeyboardUtil {

    /**
     * 收起软键盘的方法
     *
     * @param context  context对象
     * @param editText 要收起键盘的EditText
     */
    public static void closekeyboard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {// 安全起见，过滤空指针
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * 弹出软键盘的方法
     *
     * @param editText 要弹出键盘的EditText
     * @param context  context对象
     */
    public static void openKeyboard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {// 过滤空指针
            inputMethodManager.showSoftInput(editText, 0);
            editText.requestFocus();
        }
    }

    /**
     * 判断软键盘是否打开
     * @param activity 当前活动对象
     */
    public static boolean isKeyboardOpen(Activity activity){
        View view = activity.getWindow().peekDecorView();
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager)
                    activity.getSystemService(INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                return inputMethodManager.isActive() && activity.getWindow().getCurrentFocus() != null;
            }
        }
        return false;
    }
}
