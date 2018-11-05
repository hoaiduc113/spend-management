package com.example.hoaiduc.quanlychitieu.view.GiaoDichActivity;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoaiduc.quanlychitieu.CustomView.CurrencyEditText;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.MoneyIntOut;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.Transaction.PresenterLogicTransaction;
import com.example.hoaiduc.quanlychitieu.ultil.Constant;
import com.example.hoaiduc.quanlychitieu.view.category.CategoryActivity;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener,ViewTransaction {
    String a = "1756.6655";
    int userid,id,walletId,iconid;
    Intent myIntent;
    TextView txtcalendar;
    TextView giaodich;
    int REQUEST_CODE_EDIT = 123;
    Category category;
    int user;
    Intent onBack;
    private final String Tag = getClass().getSimpleName();
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;
    String name = "",creadate;
    EditText Note;
    CurrencyEditText money;
    private String TAG = "TransactionActivity";
    MoneyIntOut moneyIntOut;
    PresenterLogicTransaction presenterLogicTransaction;
    Toolbar toolbar;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat simpleDateFormat;
    Calendar calendar;
    ImageView imWallet;
    int group;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dich);
        BigDecimal parsed = new BigDecimal(a);
        presenterLogicTransaction=new PresenterLogicTransaction(TransactionActivity.this);

        myIntent = getIntent();
        userid = myIntent.getIntExtra("userid", -2);
        user = userid;
        imWallet=(ImageView)findViewById(R.id.imgwallett) ;
        txtcalendar = (TextView)findViewById(R.id.calendar);
        giaodich = (TextView)   findViewById(R.id.txtgiaodich);
        txtcalendar.setOnClickListener(this);
        toolbar=(Toolbar)       findViewById(R.id.toolbarcategory);
        money=(CurrencyEditText)findViewById(R.id.extmoney);
        Note=(EditText)        findViewById(R.id.extnote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        giaodich.setOnClickListener(this);
        broadcastReceiverlsn();
        txtcalendar.setText(DateFormat.getDateInstance().format(new Date()));
    }
    private void broadcastReceiverlsn()
    {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase(Constant.SentData)) {
                    category=(Category)intent.getSerializableExtra(Constant.NameExpend);
                    name = category.getName();

                    group=category.getGroup();
                    iconid=category.getIconid();
                    giaodich.setText(name);
                   String image= presenterLogicTransaction.showImage(iconid);
                    Log.i(TAG, "onReceive: " + image);
                    Picasso.with(TransactionActivity.this).load(image)
                            .resize(64,64)
                            .placeholder(R.drawable.cpb_background)
                            .error(R.drawable.facebookiconsmall)
                            .into(imWallet);

                }
            }
        };
        filter = new IntentFilter();
        filter.addAction(Constant.SentData);
        registerReceiver(broadcastReceiver,filter);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.calendar:
                chonNgay();
                break;
            case R.id.txtgiaodich:

                Intent intent = new Intent(this, CategoryActivity.class);
                intent.putExtra("userfragment", user);
                startActivity(intent);
                broadcastReceiverlsn();
                break;
        }
    }

    private void chonNgay()
    {
        calendar = Calendar.getInstance();
        int thu = calendar.get(calendar.DAY_OF_MONTH);
        int ngay = calendar.get(calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);

                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                txtcalendar.setText(simpleDateFormat.format(calendar.getTime()));

                //creadate=simpleDateFormat.format(calendar.getTime());

            }
        }, nam, thang, ngay);

        datePickerDialog.show


                ();
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void successFul()
    {
        onBackPressed();
    }

    @Override
    public void failure()
    {
        Toast.makeText(this, "thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_giaodich,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {

            case R.id.save:
                if(checkData())
                {
                    putData();
                }
                else
                {
                    Toast.makeText(this, "bạn vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void putData()
    {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        creadate=txtcalendar.getText().toString().trim();
        Date newdate=null;
        try {
            newdate=simpleDateFormat.parse(creadate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat=new  SimpleDateFormat("yyyy-MM-dd");
        String output=simpleDateFormat.format(newdate);
        String getMoney=money.getText().toString().trim();
        String []split=getMoney.split(",");
        String moneys="";
        for (int i=0;i<split.length;i++)
        {
            moneys+=split[i];
        }
        String note=Note.getText().toString().trim();
        category.setMoney(Integer.parseInt(moneys));
        category.setCreateday(output);
        category.setNote(note);
        category.setName(name);

        category.setIconid(iconid);
        category.setUserid(userid);
        category.setGroup(group);
        presenterLogicTransaction.perFormInsertCategory(category);
        id=presenterLogicTransaction.getId();
        moneyIntOut=new MoneyIntOut();

        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        moneyIntOut.setCreateDay(output);
       // moneyIntOut.setWalletid(walletId);
        if(group==0)
        {
            moneyIntOut.setMoneyInt(0);
            moneyIntOut.setMoneyOut(Integer.parseInt(moneys));
        }
        else if(group==1)
        {
            moneyIntOut.setMoneyInt(Integer.parseInt(moneys));
            moneyIntOut.setMoneyOut(0);
        }
        moneyIntOut.setGroup(group);
        presenterLogicTransaction.perFormInsertMoney(moneyIntOut,userid);
    }
    private boolean checkData()
    {

        if(giaodich.getText().toString().equals(null)
           ||giaodich.getText().toString().equals("")||
           money.getText().toString().equals(null)||
           money.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }

}
