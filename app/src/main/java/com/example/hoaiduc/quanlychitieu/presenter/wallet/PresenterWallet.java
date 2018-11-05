package com.example.hoaiduc.quanlychitieu.presenter.wallet;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;

/**
 * Created by hoaiduc on 2/20/2018.
 */

public interface PresenterWallet
{
    void insertWallet(Wallet wallet);
    void displayWallet(int userid);
    void pickWallet(int userId,int walletId);

}
