package com.example.hoaiduc.quanlychitieu.presenter.DangNhap_DangKy;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.facebook.AccessToken;

import java.util.List;

public interface PresenterDangNhap
{
    List<User> thucHienDangNhap(User user);
    boolean kiemTraVi(User user);
    AccessToken getUserNameFacebook();
    void perform(User user);
    int logInGoogle(User user);
    boolean checkWallet(User user);
}
