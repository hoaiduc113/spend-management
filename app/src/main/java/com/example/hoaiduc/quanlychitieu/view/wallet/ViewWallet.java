package com.example.hoaiduc.quanlychitieu.view.wallet;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;

import java.util.List;

/**
 * Created by hoaiduc on 2/20/2018.
 */

public interface ViewWallet
{
    void thanhCong();
    void thatBai();
    void displayWallet(List<Wallet> list);
    void event();
}
