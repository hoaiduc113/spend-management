package com.example.hoaiduc.quanlychitieu.presenter.DangNhap_DangKy;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.Moldel.DangNhap_DangKy.ModelDangNhap;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.view.DangNhap_DangKy.ViewDangNhap;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoaiduc on 2/17/2018.
 */

public class PresenterLogicDangNhap implements PresenterDangNhap
{
    ViewDangNhap viewDangNhap;
    ModelDangNhap modelDangNhap;
    public PresenterLogicDangNhap(ViewDangNhap viewDangNhap)
    {
        this.viewDangNhap=viewDangNhap;
        modelDangNhap=new ModelDangNhap();
    }

    @Override
    public List<User> thucHienDangNhap(User user)
    {
        int success=-1;
        User  myUser=new User();
        List<User> listresult=new ArrayList<User>();
        listresult=modelDangNhap.dangNhap(user);
        for(int i=0;i<listresult.size();i++)
        {
            success=listresult.get(i).getSuccess();
            Log.d("success","success"+success);

        }
       // success=listresult.get(1).getSuccess();



        if(success==1)
        {
            viewDangNhap.dangNhapThanhcong();
        }
        else
        {
            viewDangNhap.dangNhapThatBai();
        }
        return  listresult;
    }

    @Override
    public boolean kiemTraVi(User user)
    {
        boolean check=modelDangNhap.checkWallet(user);
        if(check)
        {
            viewDangNhap.viTonTai();
        }
        else
        {
            viewDangNhap.viKhongTonTai();
        }
        return check;
    }

    @Override
    public AccessToken getUserNameFacebook() {
        AccessToken accessToken= modelDangNhap.getTokenFaceBook();


        return accessToken;
    }

    @Override
    public void perform(User user) {
        modelDangNhap.DangKyThanhVien(user);
    }

    @Override
    public int logInGoogle(User user) {
       int userId= modelDangNhap.logInGoogle(user);
        return userId;
    }

    @Override
    public boolean checkWallet(User user) {
        boolean check=modelDangNhap.checkWallet(user);
        return check;
    }

}
