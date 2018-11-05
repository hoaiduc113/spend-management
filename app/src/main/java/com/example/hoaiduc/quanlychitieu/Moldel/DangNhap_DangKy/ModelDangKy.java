package com.example.hoaiduc.quanlychitieu.Moldel.DangNhap_DangKy;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 2/13/2018.
 */

public class ModelDangKy {
    public int DangKyThanhVien(User user) {
        int ketQua = 0;
        String duongDan =Connect.localhost+"/quanlychitieu/dangky.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsTen = new HashMap<>();
        hsTen.put("fullname", user.getFullname());

        HashMap<String, String> email = new HashMap<>();
        email.put("email", user.getEmail());
        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("password", user.getPassword());
        attrs.add(hsTen);
        attrs.add(email);
        attrs.add(hsMatKhau);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);

        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            ketQua = jsonObject.getInt("success");

            Log.d("kiemtra", ketQua + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        return ketQua;
    }


}
