package com.example.hoaiduc.quanlychitieu.presenter.Transaction;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.MoneyIntOut;

/**
 * Created by hoaiduc on 4/8/2018.
 */

public interface PresenternsertCategory
{
    void perFormInsertCategory(Category category);
    int getId();
    void perFormInsertMoney(MoneyIntOut moneyIntOut,int userid);
    String showImage(int iconId);
}
