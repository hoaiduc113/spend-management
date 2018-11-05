package com.example.hoaiduc.quanlychitieu.view.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hoaiduc.quanlychitieu.DangNhapActivity;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.view.GiaoDichActivity.TransactionActivity;
import com.example.hoaiduc.quanlychitieu.view.history.FragmentExpense;
import com.example.hoaiduc.quanlychitieu.view.history.FragmentIncome;
import com.example.hoaiduc.quanlychitieu.view.history.FragmentOverView;
import com.example.hoaiduc.quanlychitieu.view.statistical.FragmentStatistical;
import com.example.hoaiduc.quanlychitieu.view.wallet.DisplayWallet;
import com.google.firebase.auth.FirebaseAuth;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,ViewTrangChu{
    FloatingActionButton floatingActionButton;
    int userid;
    Intent getIntent;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        getIntent=getIntent();
        userid=getIntent.getIntExtra("userid",-1);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton=(FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        test();
        event();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


        android.os.Process.killProcess(android.os.Process.myPid());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.vis:
                Intent intent=new Intent(this
                        , DisplayWallet.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return true;
    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        switch (id)
        {
            case R.id.fab:

                break;

        }
    }
    private void test()
    {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChuActivity.this,TransactionActivity.class);

                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
    }
    private void event()
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment  fragment=null;
                switch (item.getItemId())
                {
                    case R.id.soluocs:
                        Bundle bundleOverview=new Bundle();
                        bundleOverview.putInt("userid",userid);
                        fragment=new FragmentOverView();
                        fragment.setArguments(bundleOverview);
                        floatingActionButton.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.chiphi:
                        Bundle bundleExpense=new Bundle();
                        bundleExpense.putInt("userid",userid);
                        fragment=new FragmentExpense();
                        fragment.setArguments(bundleExpense);
                        floatingActionButton.setVisibility(View.VISIBLE);

        //                nav_Menu.findItem(R.id.getbackdate).setVisible(true);
                        break;
                    case R.id.thongke:
                        Bundle bundle=new Bundle();
                        bundle.putInt("userid",userid);
                        floatingActionButton.setVisibility(View.INVISIBLE);
                        fragment=new FragmentStatistical();
                        fragment.setArguments(bundle);

                        break;
                    case R.id.thunhap:
                        Bundle bundleIncome=new Bundle();
                        bundleIncome.putInt("userid",userid);
                        fragment=new FragmentIncome();
                        fragment.setArguments(bundleIncome);
                        floatingActionButton.setVisibility(View.VISIBLE);

      //                  nav_Menu.findItem(R.id.getbackdate).setVisible(true);
                        break;
                    case R.id.dangxuat:
                        FirebaseAuth.getInstance().signOut();
                        Intent myIntent=new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(myIntent);
                         break;
                    default: floatingActionButton.setVisibility(View.VISIBLE);
    //                    nav_Menu.findItem(R.id.getbackdate).setVisible(false)
                    ;
                }
                if(fragment!=null)
                {
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame,fragment);
                    fragmentTransaction.commit();
                }
                drawer.closeDrawer(Gravity.START);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);

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

}
