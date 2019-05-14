package com.hengmeng.hmkuaiyi.pro.ui.function;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.accessibility.AccessibilityNodeInfo;

import com.hengmeng.hmkuaiyi.pro.bean.TextNode;

import java.util.ArrayList;

public class ScreenFetchingHandler {
    private AccessibilityService service;

    private OnFetchingListener listener;

    private AccessibilityNodeInfo rootWindow;
    private ArrayList<AccessibilityNodeInfo> windowNodeList;
    private ArrayList<TextNode> textNodes;

    public ScreenFetchingHandler(AccessibilityService service){
        this.service = service;
    }

    public void startFetching(OnFetchingListener listener){
        this.listener = listener;

        startFetching();
    }

    public interface OnFetchingListener{
        void onSuccess(ArrayList<TextNode> textNodes);

        void onError();
    }

    /**
     * 开始屏幕取词
     */
    private int fetchNum = 0;
    private void startFetching(){
        // 重置数据
        windowNodeList = new ArrayList<>();
        textNodes = new ArrayList<>();

        // 最多获取十次
        if(fetchNum >= 10){
            fetchNum = 0;

            listener.onError();// 获取失败
            return;
        }else {
            fetchNum++;
        }

        rootWindow = service.getRootInActiveWindow();

        // 如果获取不到根窗口，隔100毫秒再获取
        if(rootWindow == null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startFetching();
                }
            },100);
        }else {
            // 成功获取到根窗口则获取根窗口的所有节点信息
            fetchNum = 0;
            ergodicAllWindowNodes(rootWindow);
            getAllNodesContent();

            listener.onSuccess(textNodes);
        }
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
