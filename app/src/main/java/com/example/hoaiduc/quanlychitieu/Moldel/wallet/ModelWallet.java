package com.example.hoaiduc.quanlychitieu.Moldel.wallet;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 2/20/2018.
 */

public class ModelWallet {
    public boolean wallet(Wallet wallet) {
        boolean check=false;

        String duongDan = Connect.localhost+"/quanlychitieu/wallet.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> userid = new HashMap<>();
        userid.put("userid", String.valueOf(wallet.getUserid()));
        HashMap<String, String> name = new HashMap<>();
        name.put("name", wallet.getName());
        HashMap<String, String> money = new HashMap<>();
        money.put("money", String.valueOf(wallet.getMoney()));
        HashMap<String, String> curid = new HashMap<>();
        curid.put("curid",String.valueOf(wallet.getCurid()));
        HashMap<String, String> status = new HashMap<>();
        status.put("status",String.valueOf(-1));
        attrs.add(userid);
        attrs.add(name);
        attrs.add(money);
        attrs.add(curid);
        attrs.add(status);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);

        downloadJSON.execute();

        try
        {

            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            int success=jsonObject.getInt("success");
            if(success==1)
            {
                check= true;
            }
            else if(success==0)
            {
                check= false;
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
       // return walletList;
        return check;
    }
    public List<Wallet> displayWallet(int userId)
    {
        Wallet wallet=null;
        List<Wallet> walletList=new ArrayList<Wallet>();
        String duongDan = Connect.localhost+"/quanlychitieu/displaywallet.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> userid = new HashMap<>();
        userid.put("userid", String.valueOf(userId));
        attrs.add(userid);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);

        downloadJSON.execute();

        try
        {
            String dulieu = downloadJSON.get();
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {
                wallet=new Wallet();
                JSONObject object=jsonArray.getJSONObject(i);
                int wltid= object.getInt("walletid");
                int wluserid=object.getInt("userid");
                String wlname=object.getString("name");
                int wlmoney=object.getInt("money");
                String wlcreateday=object.getString("createday");
                String wlmodifyday=object.getString("modifydate");
                int wlcurid=object.getInt("curid");
                wallet.setUserid(wluserid);
                wallet.setWalletid(wltid);
                wallet.setName(wlname);
                wallet.setMoney(wlmoney);
                wallet.setCreateday(wlcreateday);
                wallet.setModifydate(wlmodifyday);
                wallet.setCurid(wlcurid);
                walletList.add(wallet);
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
        return walletList;
    }
    public void pickWallet(int userid,int walletId)
    {
        String duongDan = Connect.localhost+"/quanlychitieu/pickwallet.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> userId = new HashMap<>();
        HashMap<String, String> walletID = new HashMap<>();

        walletID.put("walletid", String.valueOf(walletId));
        userId.put("userid",String.valueOf(userid));
        attrs.add(userId);
        attrs.add(walletID);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();

    }
}
