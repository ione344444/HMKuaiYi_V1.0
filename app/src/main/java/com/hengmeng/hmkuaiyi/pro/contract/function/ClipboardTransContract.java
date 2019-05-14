package com.hengmeng.hmkuaiyi.pro.contract.function;

import com.hengmeng.hmkuaiyi.mvp.model.function.BaseClipboardTransModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.function.BaseClipboardTransView;
import com.hengmeng.hmkuaiyi.pro.contract.TransListener;
import com.xiaoxi.translate.bean.TransObject;

public interface ClipboardTransContract {
    interface ClipboardTransModel extends BaseClipboardTransModel<TransObject, TransListener>{

    }

    interface ClipboardTransView extends BaseClipboardTransView {

    }

    abstract class ClipboardTransPresenter extends BasePresenter<ClipboardTransModel, ClipboardTransView>{

    }
}
