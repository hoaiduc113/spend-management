package com.example.hoaiduc.quanlychitieu.presenter.category;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.category.ModelCategory;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;
import com.example.hoaiduc.quanlychitieu.view.category.ViewCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 3/18/2018.
 */

public class PresenterLogicInCome implements PresenterCategory {
    ViewCategory viewCategory;
    public PresenterLogicInCome(ViewCategory viewCategory)
    {
        this.viewCategory=viewCategory;
    }
    @Override
    public void layDanhSach(int userid)
    {
        List<Category> loaiSanPham;
        String duongDan = Connect.localhost+"/quanlychitieu/displaycategory.php";
        String dataJson="";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsTen = new HashMap<>();
        HashMap<String, String> hsgroup = new HashMap<>();
        HashMap<String, String> hsuserid = new HashMap<>();
        hsTen.put("maloaicha","0");
        hsuserid.put("default",String.valueOf(userid));
        hsgroup.put("group",String.valueOf(1));
        attrs.add(hsTen);
        attrs.add(hsgroup);
        attrs.add(hsuserid);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan,attrs);
        downloadJSON.execute();
        try
        {
            dataJson=downloadJSON.get();//dữ liệu trả ra ngay doinbackground
            ModelCategory modelCategory=new ModelCategory();
            loaiSanPham=modelCategory.displayCategory(dataJson);//gọi đến phương thức để lấ json để hiện thị
            viewCategory.hienThiDanhSach(loaiSanPham);
            viewCategory.suKien(loaiSanPham);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
    }
}