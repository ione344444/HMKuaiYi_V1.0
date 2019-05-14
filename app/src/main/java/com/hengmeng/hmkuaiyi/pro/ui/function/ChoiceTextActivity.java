package com.hengmeng.hmkuaiyi.pro.ui.function;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.function.ChoiceTextContract;
import com.hengmeng.hmkuaiyi.pro.bean.TextNode;
import com.hengmeng.hmkuaiyi.pro.view.TextNodeView;
import com.hengmeng.hmkuaiyi.pro.view.floatwindow.StartFetchFloatWindow;
import com.hengmeng.hmkuaiyi.pro.view.floatwindow.TransResultFloatWindow;
import com.xiaoxi.translate.bean.TransObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ChoiceTextActivity extends Activity implements ChoiceTextContract.ChoiceTextView {
    private ArrayList<TextNode> textNodeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicetextnode);

        setStatusBarFullTransparent();

        FrameLayout fl_textNodesContainer = findViewById(R.id.fl_textNodesContainer);
        fl_textNodesContainer.getBackground().mutate().setAlpha(60);
        // 获取Activity传递的数据
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            finish();
            return;
        }
        bundle.setClassLoader(TextNode.class.getClassLoader());
        // 获取屏幕词节点List，加载view
        textNodeList = bundle.getParcelableArrayList("text_nodes");
        if(textNodeList!=null && textNodeList.size()>0){
            initTextNodes();
        }
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showResult(TransObject transResult) {

    }

    @Override
    public void onBackPressed() {
        if (TransResultFloatWindow.getInstance(this).isShowing()){
            TransResultFloatWindow.getInstance(this).destroyHide();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (! StartFetchFloatWindow.getInstance(this).isShowing()){
            StartFetchFloatWindow.getInstance(this).show();
        }
        super.onDestroy();
    }

    public void initTextNodes(){
        FrameLayout fl_textNodesContainer = findViewById(R.id.fl_textNodesContainer);

        TextNode[] textNodes =  textNodeList.toArray(new TextNode[0]);
        // 面积从大到小排序，这样从大到小的添加View，最终小的在上面，方便点击
        Arrays.sort(textNodes,new TextNodeComparator());
        for (TextNode textNode : textNodes) {
            new TextNodeView(this, textNode)
                    .addViewToContainer(fl_textNodesContainer);
        }
    }

    public class TextNodeComparator implements Comparator<TextNode> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public int compare(TextNode o1, TextNode o2) {
            long o1Size = o1.getBoundSize();
            long o2Size = o2.getBoundSize();
            // o1Size < o2Size,return -1;
            // o1Size == o2Size,return 0;
            // o3Size > o3Size,return 1
            return Long.compare(o1Size, o2Size);
        }
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
