package com.example.hoaiduc.quanlychitieu.presenter.wallet;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.Moldel.wallet.ModelWallet;
import com.example.hoaiduc.quanlychitieu.view.wallet.ViewWallet;

import java.util.List;

/**
 * Created by hoaiduc on 2/20/2018.
 */

public class PresenterLogicWallet implements PresenterWallet {
    ViewWallet viewWallet;
    ModelWallet modelWallet;
    public PresenterLogicWallet(ViewWallet viewWallet)
    {
        this.viewWallet=viewWallet;
        modelWallet=new ModelWallet();
    }

    @Override
    public void insertWallet(Wallet wallet)
    {
        boolean success=false;
        success=modelWallet.wallet(wallet);
        if(success)
        {
            viewWallet.thanhCong();
        }
        else
        {
            viewWallet.thatBai();
        }
    }

    @Override
    public void displayWallet(int userid) {
        List<Wallet> walletList=modelWallet.displayWallet(userid);
        viewWallet.displayWallet(walletList);
    }

    @Override
    public void pickWallet(int userId, int walletId) {
        modelWallet.pickWallet(userId,walletId);
        viewWallet.event();

    }


}
