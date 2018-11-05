package com.example.hoaiduc.quanlychitieu.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoaiduc on 3/13/2018.
 */

public class ViewPageAdapterCategory extends FragmentPagerAdapter {
    private List<Fragment> fragmentList=new ArrayList<Fragment>();
    public ViewPageAdapterCategory(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
    public void addFragments(Fragment fragment)
    {
        fragmentList.add(fragment);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Chi Tiêu";
            case 1:
                return "Thu nhập";
            default:return null;

        }

    }
}
