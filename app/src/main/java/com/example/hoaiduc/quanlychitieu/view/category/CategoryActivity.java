package com.example.hoaiduc.quanlychitieu.view.category;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hoaiduc.quanlychitieu.ultil.Constant;

import com.example.hoaiduc.quanlychitieu.Adapter.ViewPageAdapterCategory;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.view.category.Fragment.FragmentExpend;
import com.example.hoaiduc.quanlychitieu.view.category.Fragment.FragmentIncome;

public class CategoryActivity extends AppCompatActivity {
    SendDataCategory dataCategory;
    TabLayout tabLayout;
    int userid;
    ViewPager viewPager;
    Toolbar toolbar;
    Intent myIntent;
    private String TAG = "CategoryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        myIntent=getIntent();
        userid=myIntent.getIntExtra("userfragment",-1);
        Bundle bundle=new Bundle();
        bundle.putInt("aaa",userid);
        FragmentExpend fragmentExpend=new FragmentExpend();

        FragmentIncome fragmentIncome=new FragmentIncome();
        fragmentIncome.setArguments(bundle);
        fragmentExpend.setArguments(bundle);

        tabLayout=(TabLayout) findViewById(R.id.tabdcategory);
        viewPager=(ViewPager) findViewById(R.id.viewpagecategory);
        toolbar=(Toolbar)     findViewById(R.id.toolbarcategory);

        setSupportActionBar(toolbar);
        ViewPageAdapterCategory viewPageAdapterCategory=new ViewPageAdapterCategory(getSupportFragmentManager());
        viewPageAdapterCategory.addFragments(fragmentExpend);
        viewPageAdapterCategory.addFragments(fragmentIncome);

        viewPager.setAdapter(viewPageAdapterCategory);
        viewPageAdapterCategory.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
//            intent=getIntent();
            Category category =(Category) intent.getSerializableExtra(Constant.sencategory);

            if(category!=null || !category.equals(""))
            {
                Intent myIntent = new Intent();
                myIntent.setAction(Constant.SentData);
                myIntent.putExtra(Constant.NameExpend,category);
                sendBroadcast(myIntent);
                onBackPressed();
            }

        }
    };

}
