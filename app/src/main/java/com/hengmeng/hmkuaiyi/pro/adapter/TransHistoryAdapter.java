package com.hengmeng.hmkuaiyi.pro.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hengmeng.hmkuaiyi.R;
import com.hengmeng.hmkuaiyi.pro.entity.TransHistoryObject;
import com.xiaoxi.mbox.MBoxView;

import java.util.List;

public class TransHistoryAdapter extends RecyclerView.Adapter<TransHistoryAdapter.VH>{
    private Context context;
    private List<TransHistoryObject> historys;
    private ItemActionListener itemActionListener;

    public TransHistoryAdapter(Context context, List<TransHistoryObject> historys) {
        this.context = context;
        this.historys = historys;
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tv_history_fromText,tv_history_toText;
        ImageButton ib_deleteHistory;
        private VH(@NonNull View itemView) {
            super(itemView);
            tv_history_fromText = itemView.findViewById(R.id.item_history_tv_fromText);
            tv_history_toText = itemView.findViewById(R.id.item_history_tv_toText);
            ib_deleteHistory = itemView.findViewById(R.id.item_history_ib_delete);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        MBoxView historyBoxView = (MBoxView) LayoutInflater.from(context)
                .inflate(R.layout.item_transhistory_mboxview,viewGroup,false);
        return new VH(historyBoxView);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH vh, int i) {
        final  int sub = i;
        final TransHistoryObject historyObject = historys.get(i);
        vh.tv_history_fromText.setText(historyObject.getFromText());
        vh.tv_history_toText.setText(historyObject.getToText());
        final MBoxView mBoxView = (MBoxView) vh.itemView;

        vh.ib_deleteHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemActionListener != null)
                    itemActionListener.onDeleteClick(mBoxView,historyObject,sub);
            }
        });

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemActionListener != null)
                    itemActionListener.onClickItem(mBoxView,sub);
            }
        });

        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemActionListener != null)
                    itemActionListener.onLongClickItem(mBoxView,sub);
                return false;
            }
        });

        mBoxView.setCanSlidAble(true);
        mBoxView.setOnOpenChangeListener(new MBoxView.OnOpenChangeListener() {
            @Override
            public void onOpened() {
                if (itemActionListener != null)
                    itemActionListener.onOpenedItem(mBoxView,sub);
            }

            @Override
            public void onClosed() {
                if (itemActionListener != null)
                    itemActionListener.onClosedItem(mBoxView,sub);
            }

            @Override
            public void onMove() {
                if (itemActionListener != null)
                    itemActionListener.onMovingItem(mBoxView,sub);
            }
        });


    }

    public void removeData(int i) {
        historys.remove(i);
    }

    @Override
    public int getItemCount() {
        return historys.size();
    }

    public void setItemActionListener(ItemActionListener itemActionListener) {
        this.itemActionListener = itemActionListener;
    }

    public interface ItemActionListener{
        void onClickItem(MBoxView mBoxView,int i);

        void onLongClickItem(MBoxView mBoxView,int i);

        void onDeleteClick(MBoxView mBoxView,TransHistoryObject transHistoryObject,int i);

        void onOpenedItem(MBoxView mBoxView,int i);

        void onClosedItem(MBoxView mBoxView,int i);

        void onMovingItem(MBoxView mBoxView, int i);
    }

}
