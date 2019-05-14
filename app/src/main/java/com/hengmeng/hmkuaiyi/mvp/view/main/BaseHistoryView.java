package com.hengmeng.hmkuaiyi.mvp.view.main;

import java.util.List;

public interface BaseHistoryView<TH> {
    void showHistory(List<TH> historyObjects );

    void refreshHistory(List<TH> historyObjects);
}
