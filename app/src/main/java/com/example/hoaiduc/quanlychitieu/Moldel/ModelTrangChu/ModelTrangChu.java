package com.example.hoaiduc.quanlychitieu.Moldel.ModelTrangChu;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hoaiduc on 4/27/2018.
 */

public class ModelTrangChu
{
    String userName;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    CallbackManager callbackManager;
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
}
