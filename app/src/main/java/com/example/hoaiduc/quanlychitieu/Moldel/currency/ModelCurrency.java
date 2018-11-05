package com.example.hoaiduc.quanlychitieu.Moldel.currency;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Currency;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 2/18/2018.
 */

public class ModelCurrency
{
    public List<Currency> layDanhSachTien() {
        int ketQua = 0;
        String duongDan =Connect.localhost+"/quanlychitieu/listcurrency.php";
        List<Currency>  currencyList=new ArrayList<>();
        DownloadJSON downloadJSON = new DownloadJSON(duongDan);

        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            Log.d("kiemtra","dulieu"+dulieu);
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject object=jsonArray.getJSONObject(i);
                Currency currency=new Currency();
                String image=object.getString("image");
                String curname=object.getString("curname");
                String cursymbol=object.getString("cursymbol");
                int curid=object.getInt("curid");
                currency.setImage(image);
                currency.setCurname(curname);
                currency.setCursymbol(cursymbol);
                currency.setCurid(curid);
                currencyList.add(currency);
            }

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
        return currencyList;
    }
}
