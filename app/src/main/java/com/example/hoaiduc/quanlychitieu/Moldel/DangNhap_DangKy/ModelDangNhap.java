package com.example.hoaiduc.quanlychitieu.Moldel.DangNhap_DangKy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.hoaiduc.quanlychitieu.ConnectInternet.DownloadJSON;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.ultil.Connect;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hoaiduc on 2/17/2018.
 */

public class ModelDangNhap
{

    String userName;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    CallbackManager callbackManager;
    public List<User> dangNhap(User user)
    {
        List<User> userList=new ArrayList<User>();

        int result = 0;
        String url = Connect.localhost+"/quanlychitieu/DangNhap.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> email = new HashMap<>();
        email.put("email", user.getEmail());
        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("password", user.getPassword());
        attrs.add(email);
        attrs.add(hsMatKhau);
        DownloadJSON downloadJSON = new DownloadJSON(url, attrs);

        downloadJSON.execute();

        try
        {
            String name;
            String dulieu = downloadJSON.get();//return in doinbackgroun
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {
                user=new User();
                JSONObject object=jsonArray.getJSONObject(i);
                result = object.getInt("success");
                int id=object.getInt("userid");
                name=object.getString("fullname");
                user.setSuccess(result);
                Log.d("kiemtra", "jsonarray" +jsonArray);
                user.setFullname(name);
                //int id=Integer.parseInt(tid);
                user.setId(id);
                userList.add(user);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }

        return userList;
    }
    public boolean checkWallet(User user)
    {
        boolean check=false;
        List<Wallet> walletList=new ArrayList<Wallet>();
        String duongDan =Connect.localhost+"/quanlychitieu/checkwallet.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> userid = new HashMap<>();
        userid.put("userid", String.valueOf(user.getId()));
        attrs.add(userid);
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
            else
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
        return check;
    }
    public AccessToken getTokenFaceBook()
    {
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken=currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }
    public void destroyToken()
    {
        accessTokenTracker.stopTracking();
    }
    public String getUserNameFacebook(AccessToken accessToken)
    {

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            userName=object.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
        return userName;
    }
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
    public GoogleSignInClient LayGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleSignInClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = GoogleSignIn.getClient(context, gso);

        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongDangNhapGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            return opr.get();
        }else{
            return null;
        }
    }
    public int logInGoogle(User user)
    {
        int id=0;
        List<User> userList=new ArrayList<User>();

        int result = 0;
        String url = Connect.localhost+"/quanlychitieu/DangNhap.php";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> email = new HashMap<>();
        email.put("email", user.getEmail());
        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("password", user.getPassword());
        attrs.add(email);
        attrs.add(hsMatKhau);
        DownloadJSON downloadJSON = new DownloadJSON(url, attrs);

        downloadJSON.execute();

        try
        {
            String name;
            String dulieu = downloadJSON.get();
            JSONArray jsonArray=new JSONArray(dulieu);
            for(int i=0;i<jsonArray.length();i++)
            {


                JSONObject object=jsonArray.getJSONObject(i);
                id=object.getInt("userid");


            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("kiemtra", "loi" + e);
        }
        return id;
    }
}
