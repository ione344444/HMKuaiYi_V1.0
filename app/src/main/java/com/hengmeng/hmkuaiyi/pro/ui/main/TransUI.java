package com.hengmeng.hmkuaiyi.pro.ui.main;

import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.main.TransContract;
import com.hengmeng.hmkuaiyi.pro.model.main.TransModelImpl;
import com.hengmeng.hmkuaiyi.pro.presenter.main.TransPresenterImpl;
import com.xiaoxi.screenutil.KeyboardOpenListenerUtil;
import com.xiaoxi.screenutil.ScreenInfoUtil;
import com.xiaoxi.translate.bean.TransObject;

public class TransUI implements TransContract.TransView {
    private MainActivity mainActivity;

    private ConstraintLayout cl_present;
    private LinearLayout main_llt_toText;
    private EditText edt_fromText,edt_toText;
    private ImageButton ib_copyToText;
    private ImageButton ib_clearFromText;
    private FloatingActionButton fab_translating;

    private TransPresenterImpl presenter;

    public TransUI(final MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        TransModelImpl model = new TransModelImpl(mainActivity);
        presenter = new TransPresenterImpl(mainActivity);
        presenter.onAttach(model,this);

        cl_present = mainActivity.findViewById(R.id.main_cl_present);
        main_llt_toText = mainActivity.findViewById(R.id.main_llt_toText);
        edt_fromText = mainActivity.findViewById(R.id.main_edit_fromText);
        edt_toText = mainActivity.findViewById(R.id.main_edit_toText);
        ib_clearFromText = mainActivity.findViewById(R.id.main_ib_clear);
        ib_copyToText = mainActivity.findViewById(R.id.main_ib_copy);
        fab_translating = mainActivity.findViewById(R.id.main_fab_translating);

        fab_translating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTranslating();
            }
        });


        new KeyboardOpenListenerUtil(mainActivity, new KeyboardOpenListenerUtil.OnKeyBoardOpenStateListener() {
            @Override
            public void onOpenStateChanged(boolean visible, int keyBoardHeight) {
                moveTransFabForKeyboard(keyBoardHeight);
            }
        });
    }


    @Override
    public void showTransLoading(boolean show) {

    }

    @Override
    public void showResult(TransObject transObject) {
        edt_toText.setText(transObject.getToText());
    }


    @Override
    public void clearText(boolean clearFrom, boolean clearTo) {

    }

    @Override
    public void showCopyResult(boolean successful) {

    }

    @Override
    public void showToast(String text, int duration) {
        Toast.makeText(mainActivity,text,duration).show();
    }

    /**
     * 避免软键盘的弹出遮挡了悬浮按钮，此方法可将悬浮按钮上移到软键盘之上或下移到正常状态
     */
    private void moveTransFabForKeyboard(int keyboardHeight){
        final int FAB_MARGIN = 60;

        int screenWidth = ScreenInfoUtil.getScreenWidth(mainActivity);
        int screenHeight = ScreenInfoUtil.getScreenHeight(mainActivity);
        int navigationBarHeight = ScreenInfoUtil.getNavigationBarHeight(mainActivity);
        int statusBarHeight = ScreenInfoUtil.getStatusBarHeight(mainActivity);
        int fabHeight = fab_translating.getHeight();
        // 不管是getY()还是setY()都是相对于父控件的坐标，fab亦是如此
        int parentY = (int) cl_present.getY();

        int fabX = screenWidth - FAB_MARGIN - fab_translating.getWidth();
        int fabY ;

        fabY = screenHeight - parentY - navigationBarHeight -
                keyboardHeight - FAB_MARGIN - fabHeight - statusBarHeight;

        // 来点动画
        ValueAnimator anim = ValueAnimator.ofInt((int) fab_translating.getY(),fabY);
        anim.setDuration(200);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();

                fab_translating.setY(currentValue);

                fab_translating.requestLayout();
            }
        });
        anim.start();

        fab_translating.setX(fabX);
    }


    private boolean firstWindowsFocusChanged = true;

    /**
     * 当MainActivity的onWindowsFocusChanged()函数回调时，会调用此方法，在此方法
     * 里可正常获取控件坐标和大小
     */
    public void onWindowsFocusChanged(boolean hasFocus){
        // 当windows焦点变化时，将fab移动到正确位置,
        // 我们只需要第一次变化（进入activity）时进行此操作即可
        if(firstWindowsFocusChanged){
            moveTransFabForKeyboard(0);
            firstWindowsFocusChanged = false;
        }
    }

    private void startTranslating() {
        TransObject transObject = mainActivity.languageUI.getLgSettings();
        transObject.setFromText(edt_fromText.getText().toString());
        presenter.startTranslating(transObject);
    }
}
