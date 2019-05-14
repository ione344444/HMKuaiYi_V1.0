package com.hengmeng.hmkuaiyi.pro.model.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.hengmeng.hmkuaiyi.pro.bean.TransHistoryObject;
import com.xiaoxi.translate.bean.TransObject;

import java.util.ArrayList;
import java.util.List;

public class SP_TransHistoryData {
    private final int MAX_HISTORY_NUMBER = 40;// 规定翻译历史的最大数量为40

    private final String FILE_HSITORY_NUMBER = "TransHistoryNumber";
    private final String KEY_HISTORY_NUMBER = "history_number";
    private final String FILENAME_HISTORY = "TransHistory";
    private final String KEYNAME_FROMTEXT = "from_text";
    private final String KEYNAME_TOTEXT = "to_text";
    private final String KEYNAME_FROMLG_ABB = "from_lg_abb";
    private final String KEYNAME_TOLG_ABB = "to_lg_abb";

    private Context context;

    public SP_TransHistoryData(Context context){
        this.context = context;
    }



    /**
     * 将翻译历史数据保存到本地（保存到末尾）的方法
     * 将原文译文保存到本地并且添加一个id，然后更新历史的数量
     *
     * @param fromText 原文
     * @param toText 译文
     */
    public void addHistoryAtEnd(TransObject transObject){
        // 如果本地历史数据数量还未达到40，则直接在末尾添加一条历史数据
        // 否则，先删除第一条历史数据，后一条数据替换前一条数据，以此类推，要添加的数据替换第40条数据
       if(getHistoryNum() < MAX_HISTORY_NUMBER){
           int historyId = getHistoryNum() + 1;

           setHistoryUseId(historyId,transObject);

           setHistoryNum(historyId);
       }else{
           deleteHistoryUseId(1);// 用后面的历史数据覆盖第一条数据，让最后一条数据接纳新的数据

           setHistoryUseId(MAX_HISTORY_NUMBER,transObject);
       }
    }

    /**
     * 该方法用于根据id删除一条历史数据，后面的前移
     * 其实这不是真的删除，只是用第二条数据覆盖了第一条数据，以此类推
     */
    public void deleteHistoryUseId(int historyId){
        for(int i=historyId;i<=getHistoryNum();i++){
            TransHistoryObject secondHistoryObject = getHistoryUseId(i+1);
            setHistoryUseId(i,secondHistoryObject);
        }

        setHistoryNum(getHistoryNum()-1);
    }


    /**
     * 该方法用于使用指定id来保存历史，会直接替换原有的历史
     */
    private void setHistoryUseId(int historyId, TransObject transObject){
        // 将翻译历史保存到本地，原文，译文
        SharedPreferences.Editor historySpEdit = context.getSharedPreferences(
                FILENAME_HISTORY+historyId,Context.MODE_PRIVATE).edit();
        historySpEdit.putString(KEYNAME_FROMTEXT,transObject.getFromText());
        historySpEdit.putString(KEYNAME_TOTEXT,transObject.getToText());
        historySpEdit.putString(KEYNAME_FROMLG_ABB,transObject.getFromLgAbb());
        historySpEdit.putString(KEYNAME_TOLG_ABB,transObject.getToLgAbb());

        historySpEdit.apply();
    }


    /**
     * 该方法用于使用指定id来获取历史数据
     *
     * @return 历史数据对象translationHistory.
     */
    public TransHistoryObject getHistoryUseId(int historyId){
        SharedPreferences historySp = context.getSharedPreferences(
                FILENAME_HISTORY+historyId,Context.MODE_PRIVATE);
        String fromLgAbb = historySp.getString(KEYNAME_FROMLG_ABB,"");
        String toLgAbb = historySp.getString(KEYNAME_TOLG_ABB,"");
        String fromText = historySp.getString(KEYNAME_FROMTEXT,"");
        String toText = historySp.getString(KEYNAME_TOTEXT,"");

        TransHistoryObject transHistoryObject = new TransHistoryObject(historyId,
                fromLgAbb,fromText,toLgAbb,toText);
        return transHistoryObject;
    }


    /**
     * 该方法用于保存历史的数量到本地
     *
     * @param number 翻译历史的数量
     */
    private void setHistoryNum(int number){
        SharedPreferences.Editor historyNumSpEdit = context.getSharedPreferences(
                FILE_HSITORY_NUMBER,Context.MODE_PRIVATE).edit();
        historyNumSpEdit.putInt(KEY_HISTORY_NUMBER,number);
        historyNumSpEdit.apply();
    }

    /**
     * 该方法用于获取本地历史数量
     *
     * @return 本地历史数量
     */
    public int getHistoryNum(){
        SharedPreferences historyNumPref = context.getSharedPreferences(FILE_HSITORY_NUMBER,Context.MODE_PRIVATE);
        return historyNumPref.getInt(KEY_HISTORY_NUMBER,0);
    }

    /**
     * 获取本地的所有历史数据
     *
     * @return TransHistoryObject对象的集合
     */
    public List<TransHistoryObject> getAllHistoryData() {
        List<TransHistoryObject> transHistoryObjects = new ArrayList<>();
        for (int i=1;i<=getHistoryNum();i++) {
            transHistoryObjects.add(getHistoryUseId(i));
        }
        return transHistoryObjects;
    }

}
