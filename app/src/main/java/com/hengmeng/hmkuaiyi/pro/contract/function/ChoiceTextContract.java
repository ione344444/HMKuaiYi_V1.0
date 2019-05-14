package com.hengmeng.hmkuaiyi.pro.contract.function;

import com.hengmeng.hmkuaiyi.mvp.model.function.BaseChoiceTextModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.function.BaseChoiceTextView;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.xiaoxi.translate.bean.TransObject;

public interface ChoiceTextContract {
    interface ChoiceTextModel extends BaseChoiceTextModel<TransObject, TransListener>{

    }

    interface ChoiceTextView extends BaseChoiceTextView<TransObject>{

    }

    abstract class ChoiceTextPresenter extends BasePresenter<ChoiceTextModel,ChoiceTextView>{

    }
}
