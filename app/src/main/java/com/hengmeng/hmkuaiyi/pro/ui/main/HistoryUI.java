package com.hengmeng.hmkuaiyi.pro.ui.main;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.contract.main.HistoryContract;
import com.hengmeng.hmkuaiyi.pro.model.main.HistoryModelImpl;
import com.hengmeng.hmkuaiyi.pro.presenter.main.HistoryPresenterImpl;
import com.hengmeng.hmkuaiyi.pro.ui.adapter.TransHistoryAdapter;
import com.hengmeng.hmkuaiyi.pro.model.data.SP_TransHistoryData;
import com.hengmeng.hmkuaiyi.pro.bean.TransHistoryObject;
import com.xiaoxi.mbox.MBoxView;

import java.util.Collections;
import java.util.List;

public class HistoryUI implements HistoryContract.HistoryView {
    private MainActivity mainActivity;

    private TransHistoryAdapter historyAdapter;

    private HistoryModelImpl model;

    private HistoryPresenterImpl presenter;

    // 显示历史的RecyclerView
    private RecyclerView rv_historys;

    public HistoryUI(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        model = new HistoryModelImpl(mainActivity);
        presenter = new HistoryPresenterImpl(mainActivity);
        presenter.onAttach(model,this);

        presenter.loadHistoryData();
    }

    // 删除一条历史数据，并且更新视图
    private void deleteHistory(int i,TransHistoryObject transHistoryObject) {
        if (historyAdapter  == null){
            return;
        }
        historyAdapter.removeData(i);
        historyAdapter.notifyItemRemoved(i);
        historyAdapter.notifyItemRangeChanged(i,historyAdapter.getItemCount() - i);

        SP_TransHistoryData transHistoryData = new SP_TransHistoryData(mainActivity);
        transHistoryData.deleteHistoryUseId(transHistoryObject.getHistoryId());
    }

    @Override
    public void showHistory(List<TransHistoryObject> historyObjects) {
        rv_historys = mainActivity.findViewById(R.id.main_rv_transHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        rv_historys.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Collections.reverse(historyObjects);

        // 处理Adapter
        historyAdapter = new TransHistoryAdapter(mainActivity,historyObjects);
        rv_historys.setAdapter(historyAdapter);
        historyAdapter.setItemActionListener(new TransHistoryAdapter.ItemActionListener() {
            @Override
            public void onClickItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onLongClickItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onDeleteClick(MBoxView mBoxView, TransHistoryObject transHistoryObject, int i) {

            }

            @Override
            public void onOpenedItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onClosedItem(MBoxView mBoxView, int i) {

            }

            @Override
            public void onMoveItem(MBoxView mBoxView, int i) {

            }
        });

        rv_historys.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refreshHistory(List<TransHistoryObject> historyObjects) {
        showHistory(historyObjects);
    }
}
