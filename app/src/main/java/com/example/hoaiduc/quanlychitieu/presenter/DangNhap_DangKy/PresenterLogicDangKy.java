package com.example.hoaiduc.quanlychitieu.presenter.DangNhap_DangKy;

import com.example.hoaiduc.quanlychitieu.Moldel.DangNhap_DangKy.ModelDangKy;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.view.DangNhap_DangKy.ViewDangKy;

/**
 * Created by hoaiduc on 2/13/2018.
 */

public class PresenterLogicDangKy implements PresnterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy)
    {
        this.viewDangKy = viewDangKy;
        modelDangKy=new ModelDangKy();
    }

    @Override
    public void thuchien(User user)
    {
        int kiemtra=modelDangKy.DangKyThanhVien(user);
        if(kiemtra==1)
        {
            viewDangKy.dangKyThanhCong();
        }
        else
        {
            viewDangKy.dangKyThatBai();
        }
    }
}
