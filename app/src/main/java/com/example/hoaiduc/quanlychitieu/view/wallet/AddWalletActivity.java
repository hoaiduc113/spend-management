package com.example.hoaiduc.quanlychitieu.view.wallet;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hoaiduc.quanlychitieu.Adapter.CustomAdapter;
import com.example.hoaiduc.quanlychitieu.CustomView.CurrencyEditText;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Currency;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.currency.PresenterLogicListCurrency;
import com.example.hoaiduc.quanlychitieu.presenter.wallet.PresenterLogicWallet;
import com.example.hoaiduc.quanlychitieu.view.ListCurrency.ViewListCurrency;
import com.example.hoaiduc.quanlychitieu.view.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;

public class AddWalletActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener,ViewListCurrency,ViewWallet {

    EditText etNameWallet;
    int moneys;
    Spinner spinner;
    CurrencyEditText etMoney;
    Toolbar toolbar;
    CustomAdapter adapter;
    public List<Currency> CustomListView ;
    PresenterLogicListCurrency logicListCurrency;
    PresenterLogicWallet presenterLogicWallet;
    private IntentFilter filter;
    int curid,userId;
    Intent myIntents;
    Wallet myWallet;
    String nameWallet;
    int tam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        etMoney=(CurrencyEditText) findViewById(R.id.money);
        toolbar=(Toolbar) findViewById(R.id.toolbarcategory);
        spinner=(Spinner) findViewById(R.id.currency);
        etNameWallet=(EditText)findViewById(R.id.walletname);
        logicListCurrency=new PresenterLogicListCurrency();
        presenterLogicWallet=new PresenterLogicWallet(this);
        myIntents=getIntent();
        myWallet=new Wallet();
        userId=myIntents.getIntExtra("123",-1);
        if(userId!=-1 && userId!=0)
        {
           tam=userId;
        }
        getDataSpinner();
        etMoney.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner.setOnItemSelectedListener(this);


//        if(nameWallet.equals(null)||nameWallet.equals(""))
//        {
//
//            check=false;
//        }


    }


    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        switch (id)
        {


            case R.id.currency:
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        curid=CustomListView.get(i).getCurid();
        myWallet.setCurid(curid);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();

        switch (id)
        {

            case R.id.saveMoney:
                if(checkData())
                {
                    String getMoney=etMoney.getText().toString().trim();
                    String []split=getMoney.split(",");
                    String moneys="";
                    for (int i=0;i<split.length;i++)
                    {
                        moneys+=split[i];
                    }
                    nameWallet=etNameWallet.getText().toString().trim();
                    myWallet.setName(nameWallet);
                    myWallet.setUserid(tam);
                    myWallet.setMoney(Integer.parseInt(moneys));
                    presenterLogicWallet.insertWallet(myWallet);

                }
                else
                {
                    Toast.makeText(this, "vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void thanhCong() {
        Intent intent=new Intent(this, TrangChuActivity.class);
        intent.putExtra("userid",tam);
        startActivity(intent);
        finish();
    }

    @Override
    public void thatBai() {
        Toast.makeText(this, "Tên ví đã tồn tại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayWallet(List<Wallet> list) {

    }

    @Override
    public void event() {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_inputmoney,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
    private boolean checkData()
    {
        if(etMoney.getText().toString().equals(null)||
           etMoney.getText().toString().equals("")||
           etNameWallet.getText().toString().equals(null)||
           etNameWallet.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }
}
