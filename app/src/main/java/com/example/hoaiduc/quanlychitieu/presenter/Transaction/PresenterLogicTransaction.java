package com.example.hoaiduc.quanlychitieu.presenter.Transaction;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.MoneyIntOut;
import com.example.hoaiduc.quanlychitieu.Moldel.transaction.ModelMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.view.GiaoDichActivity.ViewTransaction;

/**
 * Created by hoaiduc on 4/8/2018.
 */

public class PresenterLogicTransaction implements PresenternsertCategory
{

    ViewTransaction viewTransaction;
        ModelMoneyIntOut modelMoneyIntOut;
    public PresenterLogicTransaction(ViewTransaction viewTransaction)
    {
        this.viewTransaction=viewTransaction;
        modelMoneyIntOut=new ModelMoneyIntOut();
    }

    @Override
    public void perFormInsertCategory(Category category)
    {
        modelMoneyIntOut.insertCategory(category);
//        if(check==1)
//        {
//            viewTransaction.success();
//        }
//        else
//        {
//            viewTransaction.failure();
//        }
    }
    @Override
    public int getId()
    {
        int idHistory=modelMoneyIntOut.getIdHistoryCategory();
        return idHistory;
    }

    @Override
    public void perFormInsertMoney(MoneyIntOut moneyIntOut,int userId)
    {
      int test=modelMoneyIntOut.insertMoneyIntOut(moneyIntOut,userId);

        if(test==1)
        {
            viewTransaction.successFul();
        }

        else
        {
            viewTransaction.failure();
        }
    }

    @Override
    public String showImage(int iconId) {
        String image=modelMoneyIntOut.getIconId(iconId);
        return image;
    }
}
