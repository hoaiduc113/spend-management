package com.example.hoaiduc.quanlychitieu.view.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoaiduc.quanlychitieu.CustomView.CurrencyEditText;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.ObjectIntend;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.wallet.PresenterLogicWallet;
import com.example.hoaiduc.quanlychitieu.view.TrangChu.TrangChuActivity;

import java.util.List;

public class WalletActivity extends AppCompatActivity implements View.OnClickListener,ViewWallet{
    CurrencyEditText extmoney;
    Button btnfinish;
    Intent intent;
    int userid,curid;
    EditText namewallet,keyboard;
    int money;
    String name;
    boolean check;
    Toolbar toolbar;
    PresenterLogicWallet presenterLogicWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        presenterLogicWallet=new PresenterLogicWallet(this);
        extmoney = (CurrencyEditText) findViewById(R.id.money);
        btnfinish = (Button) findViewById(R.id.finish);
        namewallet=(EditText)findViewById(R.id.wallet);
        toolbar=(Toolbar)   findViewById(R.id.toolbarcategory);
        keyboard=(EditText)findViewById(R.id.password_field);
        money=0;
        intent=getIntent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ObjectIntend getObjectIntend=(ObjectIntend) intent.getExtras().getSerializable("object");
        userid=getObjectIntend.getUserid();
        curid=getObjectIntend.getCurid();
        btnfinish.setOnClickListener(this);
        extmoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        switch (id)
        {
            case R.id.finish:
                procressWallet();
                break;



        }
    }
    private void procressWallet()
    {
        if(checkData())
        {

            String getMoney=extmoney.getText().toString().trim();
            String []split=getMoney.split(",");
            String moneys="";
            for (int i=0;i<split.length;i++)
            {
                moneys+=split[i];
            }
            Wallet wallet=new Wallet();
            money=Integer.parseInt(moneys);
            name=namewallet.getText().toString().trim();
            wallet.setUserid(userid);
            wallet.setCurid(curid);
            wallet.setName(name);
            wallet.setMoney(money);
            presenterLogicWallet.insertWallet(wallet);

        }
        else
        {
            Toast.makeText(this, "vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void thanhCong()
    {
        Toast.makeText(this, "thành công", Toast.LENGTH_SHORT).show();
        Intent myIntent=new Intent(this, TrangChuActivity.class);
        myIntent.putExtra("userid",userid);
        startActivity(myIntent);
        finish();

    }
    @Override
    public void thatBai()
    {
        Toast.makeText(this, "tên ví của bạn đã tồn tại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayWallet(List<Wallet> list) {

    }

    @Override
    public void event()
    {

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean checkData()
    {
        if(extmoney.getText().toString().trim().equals("")||
           extmoney.getText().toString().trim().equals(null)||
           namewallet.getText().toString().trim().equals(null)||
           namewallet.getText().toString().trim().equals(""))
        {
            return false;
        }
        return true;
    }

}
