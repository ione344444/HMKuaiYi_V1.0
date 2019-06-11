package com.hengmeng.hmkuaiyi.pro.contract;

import com.hengmeng.hmkuaiyi.mvp.model.BaseMainModel;
import com.hengmeng.hmkuaiyi.mvp.presenter.BasePresenter;
import com.hengmeng.hmkuaiyi.mvp.view.BaseMainView;
import com.hengmeng.hmkuaiyi.pro.entity.TransHistoryObject;
import com.xiaoxi.translate.bean.TransObject;

import java.util.List;

/**
 * 缔结MainModel与MainView的契约
 */
public interface MainContract {
    interface MainModel extends BaseMainModel<TransObject,TransHistoryObject, TransListener>{

    }

    interface MainView extends BaseMainView<TransObject, TransHistoryObject>{
        // 加载支持的所有语种并创建视图
        void createAllLgView(List<String> languages);

        // 显示源语种
        void showAllFromLg(boolean show);

        // 显示目标语种
        void showAllToLg(boolean show);
    }

    abstract class MainPresenter extends BasePresenter<MainModel,MainView>{
        // 加载用户设置的源语种和目标语种
        public abstract void loadLgSettingsData();

        // 获取支持的所有语种简写
        public abstract void loadAllLanguageAbbUpdateUI();

        // 用户选择了一个源语种后调用
        public abstract void selectFromLg(String fromLgAbb);

        // 用户选择了一个目标语种后调用
        public abstract void selectToLg(String toLgAbb);


        public abstract void startTranslating(TransObject transObject);


        // 添加一条历史数据到本地并更新UI
        public abstract void addHistoryDataUpdateUI(TransObject transObject);

        // 加载翻译历史并显示
        public abstract void loadHistoryData();
    }
}
