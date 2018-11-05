package com.example.hoaiduc.quanlychitieu.presenter.trangchu;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.Moldel.ModelTrangChu.ModelTrangChu;
import com.example.hoaiduc.quanlychitieu.view.TrangChu.ViewTrangChu;
import com.facebook.AccessToken;

/**
 * Created by hoaiduc on 4/27/2018.
 */

public class PresenterLogicTrangChu implements PresenterTrangChu
{
    private String TAG="PresenterLogicTrangChu";
    ViewTrangChu viewTrangChu;
    ModelTrangChu modelTrangChu;
    public  PresenterLogicTrangChu(ViewTrangChu viewTrangChu)
    {
        this.viewTrangChu=viewTrangChu;
        modelTrangChu=new ModelTrangChu();
    }

    @Override
    public AccessToken getUserNameFacebook()
    {
        AccessToken accessToken= modelTrangChu.getTokenFaceBook();
        String name= modelTrangChu.getUserNameFacebook(accessToken);
        Log.d(TAG,accessToken.toString());
        return accessToken;
    }


}
