package com.example.hoaiduc.quanlychitieu.view.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hoaiduc.quanlychitieu.Adapter.WalletAdater;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.wallet.PresenterLogicWallet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoaiduc on 4/24/2018.
 */

public class DisplayWallet extends AppCompatActivity implements ViewWallet,CallBack,View.OnClickListener
{

    RecyclerView recyclerView;
    PresenterLogicWallet presenterLogicWallet;
    Intent myIntent;
    List<Wallet> walletList=new ArrayList<>();
    int userId;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wallet);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        toolbar=(Toolbar)       findViewById(R.id.toolbarcategory);
        floatingActionButton=(FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        presenterLogicWallet=new PresenterLogicWallet(this);
        myIntent=getIntent();
        userId=myIntent.getIntExtra("userid",-1);
        presenterLogicWallet.displayWallet(userId);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Chọn Ví");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void thanhCong() {

    }

    @Override
    public void thatBai() {

    }

    @Override
    public void displayWallet(List<Wallet> list)
    {
        walletList=list;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayout);
        WalletAdater walletAdater=new WalletAdater(this,list,getApplicationContext());
        recyclerView.setAdapter(walletAdater);

    }
    @Override
    public void event()
    {
        onBackPressed();
    }

    @Override
    public void onItemClick(int position) {
        presenterLogicWallet.pickWallet(userId,walletList.get(position).getWalletid());
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.fab:
                Intent myIntent=new Intent(this,AddWalletActivity.class);
                myIntent.putExtra("123",userId);
                startActivity(myIntent);
        }
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
}
