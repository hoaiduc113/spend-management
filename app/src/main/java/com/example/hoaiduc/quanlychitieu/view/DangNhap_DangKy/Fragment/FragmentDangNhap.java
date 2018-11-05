package com.example.hoaiduc.quanlychitieu.view.DangNhap_DangKy.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.User;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.DangNhap_DangKy.PresenterLogicDangNhap;
import com.example.hoaiduc.quanlychitieu.view.DangNhap_DangKy.ViewDangNhap;
import com.example.hoaiduc.quanlychitieu.view.ListCurrency.ListCurrencyActivity;
import com.example.hoaiduc.quanlychitieu.view.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hoaiduc on 2/11/2018.
 */

public class FragmentDangNhap extends Fragment implements ViewDangNhap,View.OnClickListener,View.OnFocusChangeListener {
    AlertDialog b;
    AlertDialog.Builder dialogBuilder;
    private String TAG = "FragmentDangNhap";
    boolean checkwallet;
    int useid;
    Intent intent;
    EditText emaildt, passworddt;
    TextInputLayout ip_email, ip_password;
    CircularProgressButton dangnhap;
    boolean check = false;
    PresenterLogicDangNhap presenterDangNhap;
    List<User> list;
    int test;
    Button btnFaceBook, btnGoogle;
    CallbackManager callbackManager;
    String userName,lastName,eMail;
    FirebaseAuth mAuth;
    public static int RC_SIGN_IN= 234;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseUser firebaseUser;
    private CallbackManager mCallbackManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        presenterDangNhap = new PresenterLogicDangNhap(this);
        btnFaceBook = (Button) view.findViewById(R.id.btndangnhapfacebook);
        emaildt = (EditText) view.findViewById(R.id.extemaildangnhap);
        passworddt = (EditText) view.findViewById(R.id.extmatkhaudangnhap);
        ip_email = (TextInputLayout) view.findViewById(R.id.input_emaildangnhap);
        ip_password = (TextInputLayout) view.findViewById(R.id.input_passworddangnhap);
        dangnhap = (CircularProgressButton) view.findViewById(R.id.btndangnhap);
        btnGoogle = (Button) view.findViewById(R.id.btndangnhapgoogle);
        dangnhap.setOnClickListener(this);
        emaildt.setOnFocusChangeListener(this);
        passworddt.setOnFocusChangeListener(this);
        btnFaceBook.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = presenterDangNhap.getUserNameFacebook();
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        try {
                                            User myUser=new User();
                                            userName =  object.getString("name");
                                            lastName =  object.getString("last_name");
                                           eMail    =  object.getString("email");


                                            if(eMail.equals(null)||eMail.equals(""))
                                            {
                                                myUser.setEmail(lastName);
                                            }
                                            else
                                            {
                                                myUser.setEmail(eMail);
                                            }
                                            myUser.setFullname(userName);
                                            myUser.setPassword("qwertyA1"+lastName);
                                            presenterDangNhap.perform(myUser);
                                            int userId=presenterDangNhap.logInGoogle(myUser);
                                            myUser.setId(userId);
                                            boolean wallet=presenterDangNhap.checkWallet(myUser);
                                            if(wallet)
                                            {

                                                Intent myIntent=new Intent(getActivity(),TrangChuActivity.class);
                                                myIntent.putExtra("userid",userId);
                                                HideProgressDialog();
                                                startActivity(myIntent);
                                               // HideProgressDialog();


                                            }
                                            else
                                            {
                                                ShowProgressDialog();
                                                Intent myIntent=new Intent(getActivity(),ListCurrencyActivity.class);
                                                myIntent.putExtra("userid",userId);
                                                HideProgressDialog();
                                                startActivity(myIntent);
                                                 //HideProgressDialog();

                                            }
                                            Log.d(TAG, userName);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,last_name");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "error" + exception);
                    }
                });
        // Initialize Facebook Login button
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = view.findViewById(R.id.btndangnhapfacebook);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//                // ...
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//                // ...
//            }
//        });

// ...


        //log in facebook
        mAuth=FirebaseAuth.getInstance();
        //login gooogle
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
         FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser!=null)
//        {
//            updateUI();
//        }

    }
//    private void updateUI()
//    {
//        Toast.makeText(getActivity(), "you're logged in", Toast.LENGTH_SHORT).show();
//    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btndangnhap:

                if (dangnhap.getProgress() == 0) {
                    dangnhap.setProgress(30);
                } else if (dangnhap.getProgress() == -1) {
                    dangnhap.setProgress(0);
                }


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
                dangNhap();

                break;
            case R.id.btndangnhapfacebook:

                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
                ShowProgressDialog();
                break;
            case R.id.btndangnhapgoogle:

                signIn();
                ShowProgressDialog();

                break;
        }
    }

    private void dangNhap() {
        if (check) {
            String mail = emaildt.getText().toString().trim();
            String password = passworddt.getText().toString().trim();
            User user = new User();
            user.setEmail(mail);
            user.setPassword(password);
            list = new ArrayList<User>();
            list = presenterDangNhap.thucHienDangNhap(user);
            for (int i = 0; i < list.size(); i++) {
                useid = list.get(i).getId();

            }
            User myuser = new User();
            myuser.setId(useid);
            boolean check = presenterDangNhap.kiemTraVi(myuser);
            if (test == 1 && checkwallet) {
                intent = new Intent(getActivity(), TrangChuActivity.class);
                intent.putExtra("userid", useid);
                startActivity(intent);
            }
            if (test == 1 && !checkwallet) {
                intent = new Intent(getActivity(), ListCurrencyActivity.class);
                intent.putExtra("userid", useid);
                startActivity(intent);
            }
        } else {
            dangnhap.setProgress(-1);
            Toast.makeText(getActivity(), "vui lòng điền đầy đủ email và password", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id) {
            case R.id.extemaildangnhap:
                String email;
                if (!b) {
                    email = ((EditText) view).getText().toString().trim();
                    if (email.equals(null) || email.equals("")) {
                        ip_email.setErrorEnabled(true);
                        ip_email.setError("bạn chưa nhập email");
                        check = false;
                    } else {
                        check = true;
                    }
                }
                break;
            case R.id.extmatkhaudangnhap:
                String password;
                if (!b) {
                    password = ((EditText) view).getText().toString().trim();
                    if (password.equals(null) || password.equals("")) {
                        ip_email.setErrorEnabled(true);
                        ip_email.setError("bạn chưa nhập email");
                        check = false;
                    } else {
                        check = true;
                    }
                }
                break;

        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            User myUser=new User();
                            FirebaseUser user = mAuth.getCurrentUser();
                            myUser.setFullname(user.getDisplayName());
                            myUser.setEmail(user.getEmail());
                            myUser.setPassword("qwertyA1");
                            presenterDangNhap.perform(myUser);
                            int userId=presenterDangNhap.logInGoogle(myUser);
                            myUser.setId(userId);
                            boolean wallet=presenterDangNhap.checkWallet(myUser);
                            if(wallet)
                            {

                                Intent myIntent=new Intent(getActivity(),TrangChuActivity.class);
                                myIntent.putExtra("userid",userId);
                                HideProgressDialog();
                                startActivity(myIntent);



                            }
                            else
                            {
                                ShowProgressDialog();
                                Intent myIntent=new Intent(getActivity(),ListCurrencyActivity.class);
                                myIntent.putExtra("userid",userId);
                                HideProgressDialog();
                                startActivity(myIntent);


                            }


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            HideProgressDialog();
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void dangNhapThanhcong() {
        dangnhap.setProgress(100);
        test = 1;
        Toast.makeText(getActivity(), "đăng nhập thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void dangNhapThatBai() {
        test = 0;
        dangnhap.setProgress(-1);
        Toast.makeText(getActivity(), "bạn nhâp sai email hoặc password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void viTonTai() {
        checkwallet = true;
    }

    @Override
    public void viKhongTonTai() {
        checkwallet = false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
//                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//                if(result.isSuccess()){
//                    HideProgressDialog();
//                }
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                b.dismiss();
            }
        }


    }


    public void ShowProgressDialog() {
        dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View dialogView = inflater.inflate(R.layout.activity_animation, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();
        b.show();
    }

    public void HideProgressDialog()
    {

        b.dismiss();
    }


    //    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(getActivity(), "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI();
//                        }
//
//                        // ...
//                    }
//                });
//    }
}