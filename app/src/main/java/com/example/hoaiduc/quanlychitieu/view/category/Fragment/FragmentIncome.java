package com.example.hoaiduc.quanlychitieu.view.category.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.hoaiduc.quanlychitieu.Adapter.ExpanAdapter;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.category.PresenterLogicInCome;
import com.example.hoaiduc.quanlychitieu.view.category.ViewCategory;

import java.util.List;

/**
 * Created by hoaiduc on 3/12/2018.
 */

public class FragmentIncome extends Fragment implements ViewCategory
{
    PresenterLogicInCome logicInCome;
    Toolbar toolbar;
    ExpandableListView listViewIncome;
    int userid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_income,container,false);
        toolbar=(Toolbar)view.findViewById(R.id.toolbarcategory);
        Bundle bundle =getArguments();
        if(bundle!=null)
        {
            userid=bundle.getInt("aaa",-1);
            Toast.makeText(getActivity(), "userid"+userid, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), "loi", Toast.LENGTH_SHORT).show();
        }
        listViewIncome=(ExpandableListView)view.findViewById(R.id.lisviewincome);
        logicInCome=new PresenterLogicInCome(this);
        logicInCome.layDanhSach(userid);

        return view;


    }

    @Override
    public void hienThiDanhSach(List<Category> categoryList)
    {
        ExpanAdapter expanAdapter=new ExpanAdapter(getActivity(),categoryList,userid,1,listViewIncome);
        listViewIncome.setAdapter(expanAdapter);
        expanAdapter.notifyDataSetChanged();

    }

    @Override
    public void suKien(final List<Category> categoryList) {
        listViewIncome.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

                Toast.makeText(getActivity(), "tencha"+categoryList.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        listViewIncome.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                Toast.makeText(getActivity(), "ten con"+categoryList.get(i).getCategoryList().get(i1).getName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
