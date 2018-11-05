package com.example.hoaiduc.quanlychitieu.presenter.statistical;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Statistical;
import com.example.hoaiduc.quanlychitieu.Moldel.statistical.ModelStatistical;
import com.example.hoaiduc.quanlychitieu.view.statistical.ViewStatistical;

import java.util.List;

/**
 * Created by hoaiduc on 4/20/2018.
 */

public class PresenterLogicStatistical implements PresenterStatistical {
    ViewStatistical viewStatistical;
    ModelStatistical modelStatistical;
    public PresenterLogicStatistical(ViewStatistical viewStatistical)
    {
        this.viewStatistical=viewStatistical;
        modelStatistical=new ModelStatistical();
    }



    @Override
    public void lisStatisticals(Statistical statisticals) {

        List<Statistical> statistical=modelStatistical.statisticalList(statisticals);
        viewStatistical.perForm(statistical);
    }
}
