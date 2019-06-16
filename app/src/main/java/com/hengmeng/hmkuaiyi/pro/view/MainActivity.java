package com.hengmeng.hmkuaiyi.pro.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.entity.TransHistoryObject;
import com.hengmeng.hmkuaiyi.pro.constant.BaiduAppid;
import com.hengmeng.hmkuaiyi.pro.contract.MainContract;
import com.hengmeng.hmkuaiyi.pro.model.MainModelImpl;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_TransHistoryData;
import com.hengmeng.hmkuaiyi.pro.presenter.MainPresenterImpl;
import com.hengmeng.hmkuaiyi.pro.adapter.TransHistoryAdapter;
import com.hengmeng.hmkuaiyi.pro.view.function.FunctionActivity;
import com.hengmeng.hmkuaiyi.pro.util.ClipboardUtil;
import com.xiaoxi.floatpermission.FloatPerUtil;
import com.xiaoxi.mbox.MBoxView;
import com.xiaoxi.translate.BaiduTransApi;
import com.xiaoxi.translate.Language;
import com.xiaoxi.translate.bean.TransObject;

import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity implements MainContract.MainView {
    // 用于显示源语种和目标语种
    private PopupMenu popupMenuFromLg,popupMenuToLg;

    // presenter
    private MainPresenterImpl presenter = new MainPresenterImpl(this);

    // 当前的源语种和目标语种设置
    private String fromLgAbb;
    private String toLgAbb;

    private TransHistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStatusBarColor();

        // 初始化翻译功能
        BaiduTransApi.initTmp( BaiduAppid.APPID,BaiduAppid.SECURITYKEY);

        presenter.attach(new MainModelImpl(this),this);
        presenter.loadAllLanguageAbbUpdateUI();
        presenter.loadLgSettingsData();
        presenter.loadHistoryData();

        initViews();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /*-********************************************** Translating **************************************************-*/

    @Override
    public void showToast(String text, int duration) {
        Toast.makeText(this,text,duration).show();
    }

    @Override
    public void showTransLoading(boolean show) {

    }

    @Override
    public void showResult(TransObject transObject) {
        EditText edt_toText = this.findViewById(R.id.main_edit_toText);
        edt_toText.setText(transObject.getToText());

        presenter.addHistoryDataUpdateUI(transObject);
    }

/*-********************************************** Language **************************************************-*/

    @Override
    public void createAllLgView(final List<String> languageAbbes) {
        TextView tv_fromLg = findViewById(R.id.main_tv_fromLg);
        TextView tv_toLg = findViewById(R.id.main_tv_toLg);

        if (popupMenuFromLg == null){
            popupMenuFromLg = new PopupMenu(this,tv_fromLg);
        }

        if (popupMenuToLg == null){
            popupMenuToLg = new PopupMenu(this,tv_toLg);
        }

        if (languageAbbes == null){
            return;
        }

        popupMenuFromLg.getMenu().clear();
        popupMenuToLg.getMenu().clear();
        for (String title:languageAbbes){
            popupMenuFromLg.getMenu().add(Language.getLanguageUseAbb(title));
            popupMenuToLg.getMenu().add(Language.getLanguageUseAbb(title));
        }

        popupMenuFromLg.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String fromLg = (String) item.getTitle();
                presenter.saveFromLgSettingsUpdateUI(Language.getLanguageAbb(fromLg));
                return false;
            }
        });
        popupMenuToLg.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String toLg = (String) item.getTitle();
                presenter.saveToLgSettingsUpdateUI(Language.getLanguageAbb(toLg) );
                return false;
            }
        });
    }

    @Override
    public void showAllFromLg(boolean show) {
        if (popupMenuFromLg == null){
            return;
        }

        if (show){
            popupMenuFromLg.show();
        }else {
            popupMenuFromLg.dismiss();
        }
    }

    @Override
    public void showAllToLg(boolean show) {
        if (popupMenuToLg == null){
            return;
        }

        if (show){
            popupMenuToLg.show();
        }else {
            popupMenuToLg.dismiss();
        }
    }

    @Override
    public void showFromLgSettings(String fromLgAbb) {
        TextView tv_fromLg = findViewById(R.id.main_tv_fromLg);
        if (!fromLgAbb.equals("")){
            this.fromLgAbb = fromLgAbb;
            tv_fromLg.setText(Language.getLanguageUseAbb(fromLgAbb));
        }else {
            this.fromLgAbb = Language.LANGUAGEABB_AUTODET;
            tv_fromLg.setText(Language.LANGUAGE_AUTODET);
        }
    }

    @Override
    public void showToLgSettings(String toLgAbb) {
        TextView tv_toLg = findViewById(R.id.main_tv_toLg);
        if (!toLgAbb.equals("")){
            this.toLgAbb = toLgAbb;
            tv_toLg.setText(Language.getLanguageUseAbb(toLgAbb));
        }else {
            this.toLgAbb = Language.LANGUAGEABB_AUTODET;
            tv_toLg.setText(Language.LANGUAGE_AUTODET);
        }
    }

/*-********************************************** History **************************************************-*/

    @Override
    public void showHistory(List<TransHistoryObject> historyObjects) {
        RecyclerView rv_historys = findViewById(R.id.main_rv_transHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_historys.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 对历史数据进行逆序操作，最新历史的在最上面
        Collections.reverse(historyObjects);

        // 处理Adapter
        historyAdapter = new TransHistoryAdapter(this,historyObjects);
        rv_historys.setAdapter(historyAdapter);
        historyAdapter.setItemActionListener(new TransHistoryAdapter.ItemActionListener() {
            @Override
            public void onClickItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onLongClickItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onDeleteClick(MBoxView mBoxView, TransHistoryObject transHistoryObject, int i) {

            }

            @Override
            public void onOpenedItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onClosedItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onMovingItem(MBoxView mBoxView, int i) {

            }
        });

        rv_historys.setItemAnimator(new DefaultItemAnimator());
    }


    /**
     * 刷新翻译历史的实现
     */
    @Override
    public void refreshHistory(List<TransHistoryObject> historyObjects) {
        showHistory(historyObjects);
    }


    /**
     * 开始翻译的实现
     */
    private void startTranslating(){
        EditText edt_fromText = findViewById(R.id.main_edit_fromText);
        TransObject transObject = new TransObject(fromLgAbb,
                edt_fromText.getText().toString(),toLgAbb,"");
        presenter.startTranslating(transObject);
    }


    /**
     * initViews
     */
    private void initViews() {
        // 功能界面跳转按钮
        findViewById(R.id.main_ib_function).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FloatPerUtil.isFloatPerOpen(MainActivity.this)){
                    startActivity(new Intent(MainActivity.this, FunctionActivity.class));
                }else {
                    startActivity(new Intent(MainActivity.this,ApplyPermissionActivity.class));
                }
            }
        });

        // 设置界面跳转按钮
        findViewById(R.id.main_ib_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 实现源语种和目标语种设置菜单的展开
        TextView tv_fromLg = findViewById(R.id.main_tv_fromLg);
        TextView tv_toLg = findViewById(R.id.main_tv_toLg);
        tv_fromLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupMenuFromLg != null) {
                    popupMenuFromLg.show();
                }
            }
        });
        tv_toLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupMenuToLg != null) {
                    popupMenuToLg.show();
                }
            }
        });

        // 点击语种交换按钮进行源语种与目标语种的互换
        ImageButton ib_exchangeLgSettings = findViewById(R.id.main_ib_exchange_lgSettings);
        ib_exchangeLgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchangeLgSettingsTranslatingAgain();
            }
        });

        // 监听翻译按钮
        FloatingActionButton fab_translating = findViewById(R.id.main_fab_translating);
        fab_translating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTranslating();
            }
        });

        final EditText edt_fromText = findViewById(R.id.main_edit_fromText);
        edt_fromText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {//     只处理UP事件
                        startTranslating();
                    }
                    // 拦截回车事件
                    return true;
                }
                return false;
            }
        });

        // 监听清空文本框按钮
        ImageButton ib_clearFromText = findViewById(R.id.main_ib_clear);
        ib_clearFromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_fromText.setText("");
            }
        });


        final EditText edt_toText = findViewById(R.id.main_edit_toText);
        // 监听复制按钮
        ImageButton ib_copyToText = findViewById(R.id.main_ib_copy);
        ib_copyToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardUtil.setClipboardText(MainActivity.this, edt_toText.getText().toString());

                Toast.makeText(MainActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 将源语种设置与目标语种设置互换的实现，并且带动画的更新UI，再次翻译
     */
    private void exchangeLgSettingsTranslatingAgain() {
        final TextView tv_fromLg = findViewById(R.id.main_tv_fromLg);
        final TextView tv_toLg = findViewById(R.id.main_tv_toLg);

        String var = fromLgAbb;
        fromLgAbb = toLgAbb;
        toLgAbb = var;

        ValueAnimator animator = ValueAnimator.ofFloat(1,0);
        animator.setDuration(300);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();

                tv_fromLg.setAlpha(val);
                tv_toLg.setAlpha(val);
            }
        });
        animator.start();
        // 在动画开始重复时（也就是TextView完全透明时），保存语种设置并更新UI
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                presenter.saveFromLgSettingsUpdateUI(fromLgAbb);
                presenter.saveToLgSettingsUpdateUI(toLgAbb);
            }
        });

        EditText edt_fromText = findViewById(R.id.main_edit_fromText);
        presenter.startTranslating(new TransObject(
                fromLgAbb,edt_fromText.getText().toString(),toLgAbb,""));
    }

    /**
     * 删除一条历史数据并且更新UI的实现
     */
    private void deleteHistory(int i,TransHistoryObject transHistoryObject) {
        if (historyAdapter  == null){
            return;
        }
        historyAdapter.removeData(i);
        historyAdapter.notifyItemRemoved(i);
        historyAdapter.notifyItemRangeChanged(i,historyAdapter.getItemCount() - i);

        SP_TransHistoryData.getInstance(this).deleteHistoryUseId(transHistoryObject.getHistoryId());
    }

    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.coolGray));// 冷灰色
    }
}