package com.example.hoaiduc.quanlychitieu.presenter.currency;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Currency;
import com.example.hoaiduc.quanlychitieu.Moldel.currency.ModelCurrency;

import java.util.List;

/**
 * Created by hoaiduc on 2/18/2018.
 */

public class PresenterLogicListCurrency implements PresenterListCurrency
{

    ModelCurrency modelCurrency;
    public PresenterLogicListCurrency()
    {

        modelCurrency=new ModelCurrency();
    }
    @Override
    public List<Currency> getListCurrency()
    {
        List<Currency> currencies=modelCurrency.layDanhSachTien();
        return currencies;
    }

}
