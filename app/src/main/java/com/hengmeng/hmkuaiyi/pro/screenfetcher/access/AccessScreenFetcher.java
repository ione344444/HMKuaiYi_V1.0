package com.hengmeng.hmkuaiyi.pro.screenfetcher.access;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.accessibility.AccessibilityNodeInfo;

import com.hengmeng.hmkuaiyi.pro.entity.TextNode;
import com.hengmeng.hmkuaiyi.pro.base.BaseActivity;
import com.hengmeng.hmkuaiyi.pro.screenfetcher.ScreenFetcher;
import com.hengmeng.hmkuaiyi.pro.view.function.ScreenTranslatorService;
import com.hengmeng.hmkuaiyi.pro.util.AccessibilityServiceUtil;

import java.util.ArrayList;

public class AccessScreenFetcher implements ScreenFetcher {
    private OnFetchingListener listener;

    private AccessibilityNodeInfo rootWindow;
    private ArrayList<AccessibilityNodeInfo> windowNodeList;
    private ArrayList<TextNode> textNodes;

    @Override
    public void startFetching(ScreenFetcher.OnFetchingListener listener) {
        this.listener = listener;

        textNodes = null;

        // 判断辅助服务跑起来没
        Activity topActivity = BaseActivity.getTopActivity();
        if (topActivity != null && AccessibilityServiceUtil.isAccessServiceOpen(
                                topActivity,MyAccessibilityService.class.getCanonicalName())
                                && MyAccessibilityService.instance != null){
            startFetchingAsync();
        }else {
            listener.onError("未开启辅助服务");
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:     // success
                    listener.onSuccess(textNodes);
                    break;
                case 1:     // error
                    listener.onError("未获取到服务");
                    break;
                case 2:     // error
                    listener.onError("未抓取到有效信息");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 开始屏幕取词
     */
    private void startFetchingAsync(){
        // 开启子线程有效避免卡顿
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 重置数据
                windowNodeList = new ArrayList<>();
                textNodes = new ArrayList<>();

                rootWindow = MyAccessibilityService.instance.getRootInActiveWindow();

                Message msg = new Message();
                // 如果获取不到根窗口，隔100毫秒再获取
                if(rootWindow == null){
                    msg.what = 1;
                }else {
                    // 成功获取到根窗口则获取根窗口的所有节点信息
                    ergodicAllWindowNodes(rootWindow);
                    getAllNodesContent();

                    if (textNodes == null){
                        msg.what = 2;
                    }else {
                        msg.what = 0;
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }).start();
    }


    /**
     * 遍历根窗口下的所有子节点（子窗口），add到全局变量windowNodeList中
     */
    private void ergodicAllWindowNodes(@NonNull AccessibilityNodeInfo rootInActiveWindow){
        for(int i=0;i<rootInActiveWindow.getChildCount();i++){
            windowNodeList.add(rootInActiveWindow.getChild(i));
            ergodicAllWindowNodes(rootInActiveWindow.getChild(i));
        }
    }


    /**
     * 遍历子窗口数组，获取每个窗口的文本和区域，放进TextNode对像并加进数组
     */
    private void getAllNodesContent(){
        for(int i=0;i<windowNodeList.size();i++){
            AccessibilityNodeInfo windowNode = windowNodeList.get(i);
            String content = null;
            /*
             * 有些window可以直接通过getText()来获取文本，有些则需要通过
             * getContentDescription()来获取
             */
            if(windowNode.getText() != null){
                content = windowNode.getText().toString();
            }else if(windowNode.getContentDescription() != null){
                content = windowNode.getContentDescription().toString();
            }

            Rect bound = new Rect();
            windowNode.getBoundsInScreen(bound);

            if(bound.width() > 0 && bound.height() > 0
                    && content != null && !content.equals("")){
                textNodes.add(new TextNode(bound,content));
            }
        }
    }
}
