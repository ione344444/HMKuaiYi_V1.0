package com.hengmeng.hmkuaiyi.pro.ui.main;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.main.LanguageContract;
import com.hengmeng.hmkuaiyi.pro.model.main.LanguageModelImpl;
import com.hengmeng.hmkuaiyi.pro.presenter.main.LanguagePresenterImpl;
import com.xiaoxi.translate.Language;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

public class LanguageUI implements LanguageContract.LanguageView {
    private MainActivity mainActivity;

    private TextView tv_fromLg,tv_toLg;

    private PopupMenu popupMenuFromLg,popupMenuToLg;

    private LanguagePresenterImpl presenter;

    private String fromLgAbb, toLgAbb;

    public LanguageUI(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        presenter = new LanguagePresenterImpl(mainActivity);

        LanguageModelImpl model = new LanguageModelImpl(mainActivity);
        presenter.onAttach(model,this);

        tv_fromLg = mainActivity.findViewById(R.id.main_tv_fromLg);
        tv_toLg = mainActivity.findViewById(R.id.main_tv_toLg);

        tv_fromLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllFromLg(true);
            }
        });

        tv_toLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllToLg(true);
            }
        });

        presenter.loadLgSettingsData();
        presenter.loadAllLanguage();
    }

    @Override
    public void createAllLgView(final List<String> languageAbbes) {
        if (popupMenuFromLg == null){
            popupMenuFromLg = new PopupMenu(mainActivity,tv_fromLg);
        }

        if (popupMenuToLg == null){
            popupMenuToLg = new PopupMenu(mainActivity,tv_toLg);
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
                presenter.selectFromLg(Language.getLanguageAbb(fromLg));
                return false;
            }
        });
        popupMenuToLg.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String toLg = (String) item.getTitle();
                presenter.selectToLg(Language.getLanguageAbb(toLg) );
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
        if (!toLgAbb.equals("")){
            this.toLgAbb = toLgAbb;
            tv_toLg.setText(Language.getLanguageUseAbb(toLgAbb));
        }else {
            this.toLgAbb = Language.LANGUAGEABB_AUTODET;
            tv_toLg.setText(Language.LANGUAGE_AUTODET);
        }
    }


    /**
     * 返回当前的语种设置
     */
    public TransObject getLgSettings(){
        return new TransObject(fromLgAbb,"",toLgAbb,"");
    }
}
