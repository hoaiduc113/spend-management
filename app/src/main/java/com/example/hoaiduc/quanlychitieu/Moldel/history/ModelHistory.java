package com.example.hoaiduc.quanlychitieu.Moldel.history;

import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 4/22/2018.
 */

public class ModelHistory
{
    private String TAG="ModelHistory";
    public List<HistoryMoneyIntOut> moneyIntOuts(int userid, int group,String createDay)
    {
        String duongDan = Connect.localhost+"/quanlychitieu/history.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsgroup = new HashMap<>();
        HashMap<String, String> hsUserId = new HashMap<>();
        HashMap<String, String> hsCreateday = new HashMap<>();
        hsgroup.put("group",String.valueOf(group));
        hsUserId.put("userid",String.valueOf(userid));
        hsCreateday.put("createday",createDay);
        attrs.add(hsgroup);
        attrs.add(hsUserId);
        attrs.add(hsCreateday);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();

        List<HistoryMoneyIntOut>  historyMoneyIntOuts  =new ArrayList<>();
        try {
            String dulieu = downloadJSON.get();
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject object=jsonArray.getJSONObject(i);
                HistoryMoneyIntOut historyMoneyIntOut=new HistoryMoneyIntOut();
                String createday=object.getString("createday");
                int money=object.getInt("money");
                int moneyinput=object.getInt("moneyinput");
                int moneyoutput=object.getInt("moneyoutput");
                String cursymbol=object.getString("cursymbol");
                String name=object.getString("name");
                String image=object.getString("image");
                int walletmoney=object.getInt("walletmoney");
                String note=object.getString("note");
                Log.d("123",name);
                Log.d("123",money+"");
                historyMoneyIntOut.setCreateday(createday);
                historyMoneyIntOut.setMoneyInput(moneyinput);
                historyMoneyIntOut.setMoneyOutput(moneyoutput);
                historyMoneyIntOut.setName(name);
                historyMoneyIntOut.setMoney(money);
                historyMoneyIntOut.setCursymbol(cursymbol);
                historyMoneyIntOut.setImage(image);
                historyMoneyIntOut.setWalletmoney(walletmoney);
                historyMoneyIntOut.setNote(note);
                historyMoneyIntOuts.add(historyMoneyIntOut);
            }
            Log.d(TAG,historyMoneyIntOuts.toString());
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
        return historyMoneyIntOuts;
    }
    public List<HistoryMoneyIntOut> displayOverview(int userid)
    {
        String duongDan = Connect.localhost+"/quanlychitieu/overview.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> hsUserId = new HashMap<>();
        hsUserId.put("userid",String.valueOf(userid));
        attrs.add(hsUserId);

        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attrs);
        downloadJSON.execute();

        List<HistoryMoneyIntOut>  historyMoneyIntOuts  =new ArrayList<>();
        try {
            String dulieu = downloadJSON.get();
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject object=jsonArray.getJSONObject(i);
                HistoryMoneyIntOut historyMoneyIntOut=new HistoryMoneyIntOut();
                String createday=object.getString("createday");
                int money=object.getInt("money");
                String cursymbol=object.getString("cursymbol");
                String name=object.getString("name");
                String image=object.getString("image");
                Log.d("123",name);
                Log.d("123",money+"");
                historyMoneyIntOut.setCreateday(createday);
                historyMoneyIntOut.setName(name);
                historyMoneyIntOut.setMoney(money);
                historyMoneyIntOut.setCursymbol(cursymbol);
                historyMoneyIntOut.setImage(image);
                historyMoneyIntOuts.add(historyMoneyIntOut);
            }
            Log.d(TAG,historyMoneyIntOuts.toString());
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
        return historyMoneyIntOuts;
    }
}
