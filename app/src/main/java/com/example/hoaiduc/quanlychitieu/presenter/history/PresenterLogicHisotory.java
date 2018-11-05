package com.example.hoaiduc.quanlychitieu.presenter.history;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.Moldel.history.ModelHistory;
import com.example.hoaiduc.quanlychitieu.view.history.ViewHistory;

import java.util.List;

/**
 * Created by hoaiduc on 4/22/2018.
 */

public class PresenterLogicHisotory implements PresenterHistory{
    ViewHistory viewHistory;
    ModelHistory modelHistory;
    public PresenterLogicHisotory(ViewHistory viewHistory)
    {
        this.viewHistory=viewHistory;
        modelHistory=new ModelHistory();

    }
    @Override
    public void perFormHistory(int userId,int group,String createDay )
    {
        List<HistoryMoneyIntOut> list=modelHistory.moneyIntOuts(userId,group,createDay );
        viewHistory.displayHistory(list);
    }

    @Override
    public void showOverview(int userId)
    {
        List<HistoryMoneyIntOut> list=modelHistory.displayOverview(userId);
        viewHistory.displayOverView(list);
    }
}
