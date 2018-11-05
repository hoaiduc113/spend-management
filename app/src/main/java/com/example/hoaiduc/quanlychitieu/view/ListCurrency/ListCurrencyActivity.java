package com.example.hoaiduc.quanlychitieu.view.ListCurrency;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.hoaiduc.quanlychitieu.Adapter.CustomAdapter;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Currency;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.ObjectIntend;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.currency.PresenterLogicListCurrency;
import com.example.hoaiduc.quanlychitieu.view.wallet.WalletActivity;

import java.util.ArrayList;
import java.util.List;


public class ListCurrencyActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,ViewListCurrency{
    public List<Currency> CustomListView ;
    Button btntieptup;
    Toolbar toolbar;
    Spinner spinner;
    CustomAdapter adapter;
    Intent getIntent;
    ObjectIntend objectIntend;
    int userid;
    PresenterLogicListCurrency logicListCurrency;
    int curid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_currency);
        btntieptup=(Button) findViewById(R.id.btncontinue);
        spinner=(Spinner)   findViewById(R.id.spinner);
        toolbar=(Toolbar)   findViewById(R.id.toolbarcategory);
        logicListCurrency=new PresenterLogicListCurrency();
        getDataSpinner();
        btntieptup.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id)
        {
            case R.id.btncontinue:
                getIntent=getIntent();
                userid=getIntent.getIntExtra("userid",-1);
                objectIntend=new ObjectIntend();
                objectIntend.setCurid(curid);
                objectIntend.setUserid(userid);
                Intent myIntent=new Intent(this,WalletActivity.class);
                myIntent.putExtra("object",objectIntend);
                startActivity(myIntent);
                finish();
                break;
            case R.id.spinner:
                break;

        }

    }

    public void getDataSpinner()
    {
        CustomListView=new ArrayList<Currency>();
        Resources res = getResources();
        CustomListView=logicListCurrency.getListCurrency();
        adapter = new CustomAdapter(this, R.layout.spinner_rows,CustomListView,res);
        spinner.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
                curid=CustomListView.get(i).getCurid();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
