package com.example.hoaiduc.quanlychitieu.Moldel.transaction;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.MoneyIntOut;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelMoneyIntOut
{
    public int insertMoneyIntOut(MoneyIntOut moneyIntOut,int userid)
    {
        int ketQua=0;
        String duongDan = Connect.localhost+"/quanlychitieu/insertmoneyintout.php";
        String dataJson="";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsgroup = new HashMap<>();
        HashMap<String, String> hsMoneynInput = new HashMap<>();
        HashMap<String, String> hsMoneyOutput = new HashMap<>();
        HashMap<String, String> hsUserId = new HashMap<>();
        HashMap<String, String> hsCreateday = new HashMap<>();
        hsMoneynInput.put("moneyinput",String.valueOf(moneyIntOut.getMoneyInt()));
        hsCreateday.put("createday",String.valueOf(moneyIntOut.getCreateDay()));
        hsMoneyOutput.put("moneyoutput",String.valueOf(moneyIntOut.getMoneyOut()));
        hsgroup.put("group",String.valueOf(moneyIntOut.getGroup()));
        hsUserId.put("userid",String.valueOf(userid));
        attrs.add(hsMoneynInput);
        attrs.add(hsMoneyOutput);
        attrs.add(hsCreateday);
        attrs.add(hsgroup);
        attrs.add(hsUserId);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();
        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            ketQua = jsonObject.getInt("success");

            Log.d("money", ketQua + "");
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
    public  int getIdHistoryCategory( )
    {
        String id="";
        int historycategoryid=-1;
        String duongDan =Connect.localhost+"/quanlychitieu/getid.php";
        DownloadJSON downloadJSON = new DownloadJSON(duongDan);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            Log.d("kiemtra","dulieu"+dulieu);
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject object=jsonArray.getJSONObject(i);

                id=object.getString("historycategoryid");
            }
            historycategoryid=Integer.parseInt(id);


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
        return historycategoryid;
    }
    public int insertCategory(Category category)
    {
        int ketQua=0;
        String duongDan = Connect.localhost+"/quanlychitieu/inserthistorycatecory.php";
        String dataJson="";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hscategoryid = new HashMap<>();
        HashMap<String, String> hscateuserid = new HashMap<>();
        HashMap<String, String> hsiconid = new HashMap<>();
        HashMap<String, String> hsmoney = new HashMap<>();
        HashMap<String, String> hsnote = new HashMap<>();
        HashMap<String, String> hscreateday = new HashMap<>();

        hscategoryid.put("categoryid",String.valueOf(category.getCategoryid()));
        hscateuserid.put("userid",String.valueOf(category.getUserid()));
        hsiconid.put("iconid",String.valueOf(category.getIconid()));
        hsmoney.put("money",String.valueOf(category.getMoney()));
        hsnote.put("note",category.getNote());
        hscreateday.put("createday",category.getCreateday());

        attrs.add(hscategoryid);
        attrs.add(hscateuserid);
        attrs.add(hsiconid);
        attrs.add(hsmoney);
        attrs.add(hsnote);
        attrs.add(hscreateday);

        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();
        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            ketQua = jsonObject.getInt("success");

            Log.d("money", ketQua + "");
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
    public String getIconId(int iconId)
    {
        String image="";
        String duongDan = Connect.localhost+"/quanlychitieu/getimage.php";
        String dataJson="";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsiconId = new HashMap<>();
        hsiconId.put("iconid",String.valueOf(iconId));
        attrs.add(hsiconId);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();
        try {
            String dulieu = downloadJSON.get();
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject object=jsonArray.getJSONObject(i);
                image = object.getString("image");
            }


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
        return image;
    }
}
