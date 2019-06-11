package com.hengmeng.hmkuaiyi.pro.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.pro.entity.TextNode;
import com.hengmeng.hmkuaiyi.pro.widget.floatwindow.TransResultFloatWindow;
import com.xiaoxi.translate.Language;
import com.xiaoxi.translate.bean.TransObject;

public class TextNodeView extends View {
    private Context context;
    private Rect bound;
    private String content;

    private Paint mPaint;


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // 开启硬件加速，否则画不出虚线效果
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        canvas.drawLine(0,0,getWidth(),0,mPaint);
        canvas.drawLine(0,0,0,getHeight(),mPaint);
        canvas.drawLine(getWidth(),0,getWidth(),getHeight(),mPaint);
        canvas.drawLine(0,getHeight(),getWidth(),getHeight(),mPaint);
    }

    public TextNodeView(Context context) {
        super(context);
        this.context = context;
    }

    public TextNodeView(final Context context, TextNode textNode){
        super(context);
        this.context = context;
        this.bound = textNode.getBound();
        this.content = textNode.getContent();

        createPaint();

    }

    public void addViewToContainer(FrameLayout fl_textNodesContainer){
        LayoutParams lpAddView = new LayoutParams(bound.width(),bound.height());
        lpAddView.leftMargin = bound.left;
        lpAddView.topMargin = bound.top;
        lpAddView.width = bound.width();
        lpAddView.height = bound.height();
        fl_textNodesContainer.addView(this,0,lpAddView);
    }

    private void createPaint(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF0000"));
        mPaint.setStrokeWidth(4);
        // 画虚线
        mPaint.setPathEffect(new DashPathEffect(new float[]{6,6},0));
    }

}
