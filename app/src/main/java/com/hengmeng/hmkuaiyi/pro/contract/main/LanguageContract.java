package com.hengmeng.hmkuaiyi.pro.contract.main;

import com.hengmeng.hmkuaiyi.mvp.model.main.BaseLanguageModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.main.BaseLanguageView;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

/**
 * 语种部分的契约接口
 */
public interface LanguageContract {
    interface LanguageModel extends BaseLanguageModel<TransObject>{
        List<String> loadAllLanguageAbb();
    }

    interface LanguageView extends BaseLanguageView{

        // 加载支持的所有语种并创建视图
        void createAllLgView(List<String> languages);

        // 显示源语种
        void showAllFromLg(boolean show);

        // 显示目标语种
        void showAllToLg(boolean show);
    }

    abstract class LanguagePresenter extends BasePresenter<LanguageModel,LanguageView>{
        // 加载用户设置的源语种和目标语种
        protected abstract void loadLgSettingsData();

        // 加载支持的所有语种
        protected abstract void loadAllLanguage();

        // 用户选择了一个源语种后调用
        protected abstract void selectFromLg(String fromLgAbb);

        // 用户选择了一个目标语种后调用
        protected abstract void selectToLg(String toLgAbb);
    }
}
