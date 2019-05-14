package com.hengmeng.hmkuaiyi.pro.contract.main;

import com.hengmeng.hmkuaiyi.mvp.model.main.BaseTransModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.main.BaseTransView;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.xiaoxi.translate.bean.TransObject;

public interface TransContract {
    interface TransModel extends BaseTransModel<TransObject, TransListener> {

    }

    interface TransView extends BaseTransView<TransObject> {

    }

    abstract class TransPresenter extends BasePresenter<TransModel,TransView> {
        protected abstract void startTranslating(TransObject transObject);
    }
}
