package com.hengmeng.hmkuaiyi.pro.contract.function;

import com.hengmeng.hmkuaiyi.mvp.model.function.BaseScreenFetchingModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.function.BaseScreenFetchingView;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.xiaoxi.translate.bean.TransObject;

public interface ScreenFetchingContract {
    interface ScreenFetchingModel extends BaseScreenFetchingModel<TransObject, TransListener>{

    }

    interface ScreenFetchingView extends BaseScreenFetchingView {

    }

    abstract class ScreenFetchingPresenter extends BasePresenter<ScreenFetchingModel, ScreenFetchingView>{

    }
}
