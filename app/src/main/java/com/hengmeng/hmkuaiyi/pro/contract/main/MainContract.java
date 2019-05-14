package com.hengmeng.hmkuaiyi.pro.contract.main;

import com.hengmeng.hmkuaiyi.mvp.model.main.BaseMainModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.main.BaseMainView;

/**
 * 缔结MainModel与MainView的契约
 */
public interface MainContract {
    interface MainModel extends BaseMainModel{

    }

    interface MainView extends BaseMainView{

    }

    abstract class MainPresenter extends BasePresenter<MainModel,MainView>{
        protected abstract void loadSettingsData();
    }
}
