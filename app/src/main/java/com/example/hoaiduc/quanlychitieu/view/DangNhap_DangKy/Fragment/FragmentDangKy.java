package com.example.hoaiduc.quanlychitieu.view.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.DangNhap_DangKy.PresenterLogicDangKy;
import com.example.hoaiduc.quanlychitieu.view.DangNhap_DangKy.ViewDangKy;

/**
 * Created by hoaiduc on 2/11/2018.
 */

public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener{
    Button dangky;
    EditText email,fullname,passwordd,repassword;
    TextInputLayout txtemail,txtmatkhau,txtnmatkhau,txtfullname;
    PresenterLogicDangKy presenterLogicDangKy;
    boolean kiemtrathongtin=false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_fragment_dangky,container,false);
        presenterLogicDangKy=new PresenterLogicDangKy(this);
        dangky=(Button)view.findViewById(R.id.btndangdy);
        email=(EditText)view.findViewById(R.id.extemail);
        fullname=(EditText)view.findViewById(R.id.exthoten);
        passwordd=(EditText)view.findViewById(R.id.extpassword);
        repassword=(EditText)view.findViewById(R.id.extnhaplaipassword);
        txtemail=(TextInputLayout)view.findViewById(R.id.input_email);
        txtmatkhau=(TextInputLayout)view.findViewById(R.id.input_password);
        txtnmatkhau=(TextInputLayout)view.findViewById(R.id.input_nhaplaipassword);
        txtfullname=(TextInputLayout)view.findViewById(R.id.input_ehotendangky);
        dangky.setOnClickListener(this);
        email.setOnFocusChangeListener(this);
        repassword.setOnFocusChangeListener(this);
        fullname.setOnFocusChangeListener(this);
        return view;
    }
    @Override
    public void dangKyThanhCong()
    {
        Toast.makeText(getActivity(), "Đăng ký thành công ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dangKyThatBai()
    {
        Toast.makeText(getActivity(), "Đăng ký thất bại ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        switch (id)
        {
            case R.id.btndangdy:
                btnDangKy();
                break;
        }
    }
    private void btnDangKy()
    {
        String name=fullname.getText().toString();
        String mail=email.getText().toString();
        String matkhau=passwordd.getText().toString();
        String nmatkhau=repassword.getText().toString();
        if(kiemtrathongtin)
        {
            User user=new User();
            user.setFullname(name);
            user.setEmail(mail);
            user.setPassword(matkhau);
            presenterLogicDangKy.thuchien(user);

        }
        else
        {
            Toast.makeText(getActivity(), "bạn vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFocusChange(View view, boolean b)
    {
        int id=view.getId();
        switch (id)
        {
            case R.id.extemail:
                String chuoi;
                if(!b)//mất focus
                {
                     chuoi=((EditText)view).getText().toString();
                    if(chuoi.trim().equals("")||chuoi.equals(null))
                    {
                        txtemail.setErrorEnabled(true);
                        txtemail.setError("bạn chưa nhập mục này");
                        kiemtrathongtin=false;
                    }
                    else
                    {
                        boolean kiemtraemail= Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail)
                        {
                            txtemail.setErrorEnabled(true);
                            txtemail.setError("bạn nhập không đúng địa chỉ email");
                            kiemtrathongtin=false;
                        }
                        else
                        {
                            txtemail.setErrorEnabled(false);
                            txtemail.setError("");
                            kiemtrathongtin=true;
                        }
                    }

                }

                break;
            case R.id.exthoten:
                String hoten;
                if(!b)
                {
                    hoten=((EditText)view).getText().toString().trim();
                    if(hoten.trim().equals("")||hoten.equals(null))
                    {
                        txtfullname.setErrorEnabled(true);
                        txtfullname.setError("bạn chưa nhập mục này");
                        kiemtrathongtin=false;
                    }
                    else
                    {
                        txtemail.setErrorEnabled(false);
                        txtemail.setError("");
                        kiemtrathongtin=true;
                    }
                }

                break;
            case R.id.extpassword:
                String password;
                if(!b)
                {
                    password=((EditText) view).getText().toString().trim();
                    if(password.equals("")||password.equals(null))
                    {
                        txtmatkhau.setErrorEnabled(true);
                        txtmatkhau.setError("bạn chưa nhập mục này");
                        kiemtrathongtin=false;
                    }
                    else
                    {
                        txtmatkhau.setErrorEnabled(false);
                        txtmatkhau.setError("");
                        kiemtrathongtin=true;
                    }
                }

                break;
            case R.id.extnhaplaipassword:
                String npass,pass;

                if(!b)
                {
                    npass = ((EditText) view).getText().toString().trim();
                    pass = passwordd.getText().toString().trim();
                    if (!pass.equals(npass))
                    {
                        txtmatkhau.setErrorEnabled(true);
                        txtnmatkhau.setError("mật khẩu không trùng khớp với mật khẩu  trên");
                        kiemtrathongtin = false;
                    } else {
                        txtmatkhau.setErrorEnabled(false);
                        txtnmatkhau.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;

        }
    }
}
