package com.hengmeng.hmkuaiyi.pro.view.function;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.function.ChoiceTextContract;
import com.hengmeng.hmkuaiyi.pro.entity.TextNode;
import com.hengmeng.hmkuaiyi.pro.widget.TextNodeView;
import com.hengmeng.hmkuaiyi.pro.widget.floatwindow.BaseFloatWindow;
import com.hengmeng.hmkuaiyi.pro.widget.floatwindow.TransResultFloatWindow;
import com.xiaoxi.screenutil.ScreenInfoUtil;
import com.xiaoxi.translate.Language;
import com.xiaoxi.translate.bean.TransObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ChoiceTextActivity extends Activity implements ChoiceTextContract.ChoiceTextView {

    private Context context = this;

/*-*************************************** Activity抽象方法实现 ***************************************************-*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicetextnode);

        if (ScreenTranslatorService.instance != null){
            ScreenTranslatorService.instance.destroyTriggerFloat();
        }

        // 透明状态栏
        setStatusBarFullTransparent();

        FrameLayout fl_textNodesContainer = findViewById(R.id.fl_textNodesContainer);
        fl_textNodesContainer.getBackground().mutate().setAlpha(60);

        initTextNodes();

        // 销毁取词悬浮窗
        if (ScreenTranslatorService.instance != null) {
            ScreenTranslatorService.instance.destroyTriggerFloat();
        }

        handleDragParent();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 为了用户体验，活动切换到后台时关掉悬浮窗
        TransResultFloatWindow.getInstance(this).destroyHide();
    }

    @Override
    protected void onDestroy() {
        if (ScreenTranslatorService.instance != null){
            ScreenTranslatorService.instance.createShowTriggerFloat();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (TransResultFloatWindow.getInstance(this).isShowing()){
            TransResultFloatWindow.getInstance(this).destroyHide();
        }else{
            super.onBackPressed();
        }
    }

    /*-*************************************** mvp-view抽象方法实现 ***************************************************-*/

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void showResult(TransObject transResult) {

    }

/*-*************************************** 对抓取到的信息进行遍历，排序，显示 **************************************-*/
    /**
     * 直接调用此方法加载和展示屏幕上抓取的信息
     */
    private void initTextNodes(){
        // 获取Activity传递的数据
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            finish();
            return;
        }
        bundle.setClassLoader(TextNode.class.getClassLoader());
        // 获取屏幕词节点List，加载view
        List<TextNode> textNodeList = bundle.getParcelableArrayList("text_nodes");
        if(textNodeList!=null && textNodeList.size()>0){
            TextNode[] textNodes =  textNodeList.toArray(new TextNode[0]);
            showTextNodes(textNodes);
        }
    }

    /**
     * 显示textNodes到布局容器中，并设置其点击事件的实现
     */
    private void showTextNodes(TextNode[] textNodes) {
        final FrameLayout fl_textNodesContainer = findViewById(R.id.fl_textNodesContainer);

        // 面积从大到小排序，这样从大到小的添加View，最终小的在上面，方便点击
        Arrays.sort(textNodes,new TextNodeComparator());
        for (final TextNode textNode : textNodes) {
            TextNodeView textNodeView = new TextNodeView(this, textNode);
            textNodeView.addViewToContainer(fl_textNodesContainer);
            textNodeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 在一些界面比较复杂的地方，会绘制很多node，将其隐藏会避免悬浮窗开启动画卡顿
                    fl_textNodesContainer.setVisibility(View.GONE);

                    TransResultFloatWindow transResultFloatWindow = TransResultFloatWindow.getInstance(context);
                    transResultFloatWindow.show(textNode.getContent(),
                            Language.LANGUAGEABB_AUTODET,Language.LANGUAGEABB_AUTODET);
                    transResultFloatWindow.setOnActionListener(new TransResultFloatWindow.OnActionListener() {
                        @Override
                        public void onTransSuccess(TransObject transResult, String jsonResult) {

                        }

                        @Override
                        public void onTransError(String errorCode, String jsonResult) {

                        }

                        @Override
                        public void onFromTextCopy(boolean success) {
                            if (success){
                                Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"复制失败",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onToTextCopy(boolean success) {
                            if (success){
                                Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"复制失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    transResultFloatWindow.setOnDestroyHideListener(new BaseFloatWindow.OnDestroyHideListener() {
                        @Override
                        public void onDestroy() {
                            fl_textNodesContainer.setVisibility(View.VISIBLE);// 悬浮窗关闭了就将容器设置为可见
                        }
                    });
                }
            });
        }
    }


    /**
     * 面积从大到小排序的实现
     */
    private class TextNodeComparator implements Comparator<TextNode> {

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

/*-***************************************** 手指左滑结束活动的实现 *********************************************-*/

    @SuppressLint("ClickableViewAccessibility")
    private void handleDragParent(){
        final int screenWidth = ScreenInfoUtil.getScreenWidth(this);

        final LinearLayout llt_choice_parent = findViewById(R.id.llt_choice_parent);
        llt_choice_parent.setClickable(true);
        llt_choice_parent.setFocusable(true);
        llt_choice_parent.setOnTouchListener(new View.OnTouchListener() {
            int offRawX = 0;
            int downRawX = 0;
            int rawX = 0;
            int moveRawX = 0;

            // 当用户手指抬起的时候，这个值代表了子view最终是左移还是右移
            boolean isChildrenMoveToLeft = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downRawX = (int) event.getRawX();
                        rawX = (int) event.getRawX();
                        break;

                    case MotionEvent.ACTION_MOVE:
//                        if (downRawX < screenWidth/2){
//                            return false;
//                        }
                        int nowRawX = (int) event.getRawX();
                        offRawX = (int) (event.getRawX() - downRawX);
                        moveRawX = nowRawX - rawX;

                        if (Math.abs(offRawX) > 7){
                            llt_choice_parent.scrollBy(-moveRawX,0);
                        }
                        rawX = nowRawX;
                        break;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        ValueAnimator animator;
                        if (moveRawX < 0){
                            isChildrenMoveToLeft = true;
                            animator = ValueAnimator.ofInt(llt_choice_parent.getScrollX(),screenWidth);
                        }else {
                            isChildrenMoveToLeft = false;
                            animator = ValueAnimator.ofInt(llt_choice_parent.getScrollX(),0);
                        }
                        animator.setCurrentPlayTime(0);
                        animator.setDuration(200);
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int val = (int) animation.getAnimatedValue();

                                llt_choice_parent.scrollTo(val,0);
                            }
                        });
                        animator.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (isChildrenMoveToLeft){
                                    // 这里的finish就不要什么效果了
                                    ChoiceTextActivity.this.finish();

                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                }
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                        animator.start();

                        rawX = 0;
                        downRawX = 0;
                        moveRawX = 0;
                        break;
                }
                return false;
            }
        });
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
