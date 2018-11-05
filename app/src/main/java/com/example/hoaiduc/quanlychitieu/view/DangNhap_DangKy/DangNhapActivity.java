package com.example.hoaiduc.quanlychitieu;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hoaiduc.quanlychitieu.Adapter.ViewPageAdapterDangNhap;


/**
 * Created by hoaiduc on 2/10/2018.
 */

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_dangnhap);
        tabLayout=(TabLayout) findViewById(R.id.tabdangnhap);
        viewPager=(ViewPager) findViewById(R.id.viewpagedangnhap);
        toolbar=(Toolbar)     findViewById(R.id.toolbardangnhap);
        setSupportActionBar(toolbar);


        ViewPageAdapterDangNhap viewPageAdapterDangNhap=new ViewPageAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapterDangNhap);
        viewPageAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }

}

