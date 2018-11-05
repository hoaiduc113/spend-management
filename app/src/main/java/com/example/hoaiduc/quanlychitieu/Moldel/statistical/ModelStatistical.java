package com.example.hoaiduc.quanlychitieu.Moldel.statistical;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Statistical;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 4/19/2018.
 */

public class ModelStatistical
{
    private String Tag="ModelStatistical";
    public List<Statistical> statisticalList(Statistical statistical)
    {

        String duongDan = Connect.localhost+"/quanlychitieu/statistical.php";
        String dataJson="";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsyear = new HashMap<>();
        HashMap<String, String> hsuserid = new HashMap<>();
        HashMap<String, String> hsgroup = new HashMap<>();
        HashMap<String, String> hsFirstMonth = new HashMap<>();
        HashMap<String, String> hsEndMonth = new HashMap<>();
        hsyear.put("year",String.valueOf(statistical.getYeard()));
        hsuserid.put("userid",String.valueOf(statistical.getUserid()));
        hsgroup.put("group",String.valueOf(statistical.getGroup()));
        hsFirstMonth.put("firstmonth",String.valueOf(statistical.getFirstMonth()));
        hsEndMonth.put("endmonth",String.valueOf(statistical.getEndMonth()));
        attrs.add(hsyear);
        attrs.add(hsuserid);
        attrs.add(hsgroup);
        attrs.add(hsFirstMonth);
        attrs.add(hsEndMonth);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan,attrs);
        downloadJSON.execute();
        List<Statistical> statisticalList=new ArrayList<>();
        try
        {
            String dulieu = downloadJSON.get();
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                int money=jsonObject.getInt("money");
                String date=jsonObject.getString("createday");
                String cursymbol=jsonObject.getString("cursymbol");
                Statistical statisticals=new Statistical();
                statisticals.setMoney(money);
                statisticals.setCreteday(date);
                statisticals.setCurSymbol(cursymbol);
                statisticalList.add(statisticals);
                Log.d(Tag,cursymbol+"");
                Log.d(Tag,date+"");
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return statisticalList;
    }
}

