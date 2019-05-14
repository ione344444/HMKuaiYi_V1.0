package com.hengmeng.hmkuaiyi.pro.contract.main;

import com.hengmeng.hmkuaiyi.mvp.model.main.BaseHistoryModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.main.BaseHistoryView;
import com.hengmeng.hmkuaiyi.pro.bean.TransHistoryObject;

public interface HistoryContract {
    interface HistoryModel extends BaseHistoryModel<TransHistoryObject>{

    }

    interface HistoryView extends BaseHistoryView<TransHistoryObject> {

    }

    abstract class HistoryPresenter extends BasePresenter<HistoryModel,HistoryView>{
        protected abstract void loadHistoryData();
    }
}
