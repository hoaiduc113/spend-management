package com.example.hoaiduc.quanlychitieu.Moldel.category;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Currency;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 2/23/2018.
 */

public class ModelCategory
{
    public List<Category> displayCategory(String dulieu )
    {
        List<Category> categoryList  =new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject object=jsonArray.getJSONObject(i);
                Category categories=new Category();
                int iconid=object.getInt("iconid");
                String image=object.getString("image");
                Log.d("category",image);
                //int userid=object.getInt("userid");
                //int walletid=object.getInt("walletid");
                int parentid=object.getInt("parentid");
                String name=object.getString("name");
                int group=object.getInt("group");
                String createday=object.getString("createday");
                String modifyday=object.getString("modifyday");
                int defaults=object.getInt("default");
                int categoryid=object.getInt("categoryid");
                categories.setImage(image);
                categories.setCreateday(createday);
                //categories.setWalletid(walletid);
                categories.setCategoryid(categoryid);
                categories.setName(name);
                categories.setParentid(parentid);
                categories.setDefaults(defaults);
//                categories.setUserid(defaults);
                categories.setModifyday(modifyday);
                categories.setIconid(iconid);
                categories.setGroup(group);
                categoryList.add(categories);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        return categoryList;
    }
    public List<Category> danhSachmuccon(int maloai,int userid,int group)
    {
        List<Category> loaiSanPham =new ArrayList<>();
        String duongDan = Connect.localhost+"/quanlychitieu/displaycategory.php";
        String dataJson="";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsmaloai = new HashMap<>();
        HashMap<String, String> hsuserid = new HashMap<>();
        HashMap<String, String> hsgroup = new HashMap<>();
        hsmaloai.put("default",String.valueOf(userid));
        hsuserid.put("maloaicha",String.valueOf(maloai));
        hsgroup.put("group",String.valueOf(group));
        attrs.add(hsmaloai);
        attrs.add(hsuserid);
        attrs.add(hsgroup);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan,attrs);
        downloadJSON.execute();
        try
        {
            dataJson=downloadJSON.get();//dữ liệu trả ra ngay doinbackground
            ModelCategory  modelCategory=new ModelCategory();
            loaiSanPham=modelCategory.displayCategory(dataJson);//gọi phương thức put dữ liệu trong model
            Log.d("category",loaiSanPham+"");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        return loaiSanPham;
    }



}
