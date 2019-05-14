package com.xiaoxi.mbox;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;


/**
 * @author 落暮晓曦
 *
 * @version 2.2
 *
 * MBoxView是一个侧滑控件，你可以为其添加一个隐藏的视图，
 * 当用户滑动时才会将其展现
 *
 * MBoxView继承自LinearLayout，这使得它使用起来非常简单，你只需像使用LinearLayout那样使用它
 * 值得注意的是，MBoxView会自动获取第一个子View/ViewGroup来作为显示布局，获取其余的View/ViewGroup
 * 作为隐藏布局，所以你需要将前者的宽设置为match_parent,后者的宽设置为确切值
 *
 * 更新内容：
 * - 修复了阻塞式交互导致父布局无法接受点击事件的bug
 * - 新增hasOnOpenChangeListener()方法,用于获取当前MBoxView是否注册了滑动事件
 */
public class MBoxView extends LinearLayout {
    private Context context;
    private int endViewWidth;
    private View startView;
    private OnOpenChangeListener onOpenChangeListener;
    private boolean isStartClickAble = true;
    private boolean canSlidAble = true;

    public MBoxView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public MBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public MBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
        setClickable(true);
        setFocusable(true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        endViewWidth = 0;
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            if (i==0){
                startView = getChildAt(i);
            }else {
                View view = getChildAt(i);
                endViewWidth += view.getMeasuredWidth();
            }
        }
        setStartClickAble(isStartClickAble);
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 为滑动布局注册滑动监听事件，是否注册将影响滑动布局是否可以滑动
     *
     * @param onOpenChangeListener 滑动监听接口引用
     */
    public void setOnOpenChangeListener(OnOpenChangeListener onOpenChangeListener) {
        this.onOpenChangeListener = onOpenChangeListener;
    }

    /**
     * 获取用户是否注册了滑动监听事件的布尔值
     *
     * @return 是否注册了滑动监听事件的布尔值
     */
    public boolean hasOnOpenChangeListener() {
        return onOpenChangeListener != null;
    }

    public interface OnOpenChangeListener{
        void onOpened();

        void onClosed();

        void onMove();
    }

    /**
     * 该方法主要用于阻塞式交互,是否屏蔽显示部分（包括所有子视图）的事件
     *
     * @param able 是否禁止
     */
    public void setStartClickAble(boolean able) {
        isStartClickAble = able;
        setClickable(able);
        if (startView != null){
            setChildrenClickAble(startView,able);
        }

    }

    /**
     * 是否屏蔽显示部分（包括所有子视图）的事件
     *
     * @return 是否禁止
     */
    public boolean isStartClickAble() {
        return isStartClickAble;
    }

    private void setChildrenClickAble(View view, boolean able) {
        // 如果用户没有对该控件注册点击事件，那么依旧屏蔽该控件点击事件，避免影响父布局事件
        view.setClickable(able && view.hasOnClickListeners());

        ViewGroup viewGroup;
        try {
            viewGroup = (ViewGroup) view;
        }catch (Exception e){
            return;
        }
        int count = viewGroup.getChildCount();
        for (int i=0;i<count;i++){
            setChildrenClickAble(viewGroup.getChildAt(i),able);
        }
    }

    /**
     * 设置当前布局是否可滑动
     *
     * @param canSlidAble 是否可滑动
     */
    public void setCanSlidAble(boolean canSlidAble) {
        this.canSlidAble = canSlidAble;
    }

    /**
     * 获取当前布局是否可滑动
     *
     * @return 是否可滑动
     */
    public boolean getCanClickAble(){
        return canSlidAble;
    }

    private boolean areOpened = false;
    private int downRawX,downRaxY;
    private int offRawX,offRawY;
    private int oldRawX;
    private int downScrollX = 0;
    private int closeScrollX = 0,openScrollX;

    /// 控件是否开始滑动
    boolean transSlide = false;
    // 辨别控件的滑动方向，是否向左滑
    boolean leftSlid;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (onOpenChangeListener == null && !canSlidAble){
            return super.onTouchEvent(event);
        }

        /*
        * 处理控件滑动的代码
        * 注意：LinearLayout自带的scrollTo()函数改变的是其内容的坐标，并且scrollX等属性正负都
        * 与触摸坐标值相反，在阅读代码时应注意这点，不然容易产生歧义
        */
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downRawX = (int) event.getRawX();
                downRaxY = (int) event.getRawY();
                downScrollX = getScrollX();
                oldRawX = downRawX;

                openScrollX = closeScrollX + endViewWidth;
                break;
            case MotionEvent.ACTION_MOVE:
                int nowRawX = (int) event.getRawX();
                int nowRawY = (int) event.getRawY();
                offRawX = nowRawX - downRawX;
                offRawY = nowRawY - downRaxY;

                leftSlid = oldRawX >= nowRawX;
                // 判断是否为横滑
                if (!transSlide && Math.abs(offRawX)>5 && Math.abs(offRawY)<10){
                    transSlide = true;
                    Log.e("MBoxView","transSlide");
                    Log.e("MBoxView","endViewWidth"+endViewWidth);
                }

                if (transSlide){
                    onOpenChangeListener.onMove();
                    getParent().requestDisallowInterceptTouchEvent(true);

                    // 取消点击事件
                    MotionEvent cancelEvent = MotionEvent.obtain(event);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL |
                            (event.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    onTouchEvent(cancelEvent);

                    int moveScrollX = -nowRawX+oldRawX;
                    int newScrollX = getScrollX() + moveScrollX;
                    /* 对滑动范围的限制，以及对滑动过程中打开状态的监听 */
                    if (newScrollX >= openScrollX && nowRawX < oldRawX){
                        newScrollX = openScrollX;
                        if (!areOpened){
                            areOpened = true;
                            onOpenChangeListener.onOpened();
                        }
                    }else if (newScrollX <= closeScrollX && nowRawX > oldRawX){
                        newScrollX = closeScrollX;
                        if (areOpened){
                            onOpenChangeListener.onClosed();
                            areOpened = false;
                        }
                    }
                    scrollTo(newScrollX,0);

                    oldRawX = nowRawX;
                }
                break;

            case MotionEvent.ACTION_UP:
                int openStand = endViewWidth/10*3;

                if (getScrollX()>=openStand && getScrollX()<=(endViewWidth-openStand)){
                    if (leftSlid)
                        openBoxAnimator();
                    else
                        closeBoxAnimator();
                }else if (getScrollX() > (endViewWidth - openStand)){
                    openBoxAnimator();
                }else if (getScrollX() < openStand) {
                    closeBoxAnimator();
                }

                transSlide = false;
                oldRawX = 0;
                break;
        }
        return super.onTouchEvent(event);
    }

    private void openBoxAnimator(){
        moveScrollAnimator(getScrollX(),openScrollX);
    }

    private void closeBoxAnimator(){
        moveScrollAnimator(getScrollX(),closeScrollX);
    }

    private void moveScrollAnimator(int start, final int end){
        ValueAnimator anim = ValueAnimator.ofInt(start,end);
        anim.setDuration(100);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();

                scrollTo(currentValue,0);

                requestLayout();

                if (currentValue == end && currentValue == openScrollX && !areOpened){
                    onOpenChangeListener.onOpened();
                    areOpened = true;
                }else if(currentValue == end && currentValue == closeScrollX && areOpened){
                    onOpenChangeListener.onClosed();
                    areOpened = false;
                }
            }
        });
        anim.start();
    }
}
