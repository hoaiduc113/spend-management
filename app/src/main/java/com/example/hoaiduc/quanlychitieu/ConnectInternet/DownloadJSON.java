package com.example.hoaiduc.quanlychitieu.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DownloadJSON extends AsyncTask<String, Void,String> {
    String duongDan;
    List<HashMap<String,String>> attrs;
    StringBuilder duLieu;
    boolean method = true;
    //true is method get
    //false is method post
    public DownloadJSON(String duongDan){//method get
        this.duongDan = duongDan;
        method = true;
    }
    public DownloadJSON(String duongDan, List<HashMap<String, String>> attrs){
        this.duongDan = duongDan;
        this.attrs = attrs;
        method = false;
    }
    public DownloadJSON()
    {

    }
    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            URL url = new URL(duongDan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(!method){
                data = methodPost(httpURLConnection);
            }else {
                data = methodGet(httpURLConnection);
            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            Log.d("dulieumoi","loi"+e);
        } catch (IOException e)
        {
            e.printStackTrace();
            Log.d("dulieumoi","loi"+e);
        }
        Log.d("dulieumoi",data);
        return data;
    }
    private String methodGet(HttpURLConnection httpURLConnection){
        String data = "";
        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);//read
            BufferedReader bufferedReader = new BufferedReader(reader);//write

            duLieu = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) !=null){
                duLieu.append(line);
            }

            data = duLieu.toString();
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    private String methodPost(HttpURLConnection httpURLConnection){
        String data = "";
        String key = "";
        String value ="";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);//mở chuỗi
            httpURLConnection.setDoInput(true);
            Uri.Builder builder = new Uri.Builder();
            int count = attrs.size();
            for ( int i = 0; i < count; i++){
                for(Map.Entry<String,String> values : attrs.get(i).entrySet()){
                    key = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key,value);
            }
            String query = builder.build().getEncodedQuery();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);
            writer.write(query);
            writer.flush();
            writer.close();
            streamWriter.close();
            outputStream.close();
            data = methodGet(httpURLConnection);


        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.d("dulieumoi","loi"+e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("dulieumoi","loi"+e);
        }

        return  data;
    }
}
