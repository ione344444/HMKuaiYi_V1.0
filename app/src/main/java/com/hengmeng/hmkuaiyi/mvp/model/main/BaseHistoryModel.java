package com.hengmeng.hmkuaiyi.mvp.model.main;

import java.util.List;

public interface BaseHistoryModel<TH> {
    List<TH> loadHistoryData();
}
