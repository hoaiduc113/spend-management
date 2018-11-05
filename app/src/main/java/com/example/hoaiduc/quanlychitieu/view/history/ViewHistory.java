package com.example.hoaiduc.quanlychitieu.view.history;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;

import java.util.List;

/**
 * Created by hoaiduc on 4/22/2018.
 */

public interface ViewHistory
{
    void displayHistory(List<HistoryMoneyIntOut> list);
    void displayOverView(List<HistoryMoneyIntOut> list);
}
