package com.example.hoaiduc.quanlychitieu.view.category.Fragment;

import android.content.Context;
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
import com.example.hoaiduc.quanlychitieu.presenter.category.PresenterLogicCateGory;
import com.example.hoaiduc.quanlychitieu.view.category.ViewCategory;

import java.util.List;

/**
 * Created by hoaiduc on 3/12/2018.
 */

public class FragmentExpend extends Fragment implements ViewCategory {
    Context context;
    int userid;
    PresenterLogicCateGory presenterLogicCateGory;
    ExpandableListView listView;
    Toolbar toolbar;
    String name;
    private String TAG = "FragmentExpend";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragmet_expend, container, false);
        listView = (ExpandableListView) view.findViewById(R.id.epmenu);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarcategory);

        // get value userid from giaodichactivit
        Bundle bundle = getArguments();
        if (bundle != null) {
            userid = bundle.getInt("aaa", -1);

        } else {
            Toast.makeText(getActivity(), "loi", Toast.LENGTH_SHORT).show();
        }
        presenterLogicCateGory = new PresenterLogicCateGory(this);
        //ham dung tra danh muc cha arrylist(category)
        //truyen cai list nay vao constructor expanadapter
        // sau do tiep tiep tuc gui request de lay danh muc con
        presenterLogicCateGory.layDanhSach(userid);
        //  fragmentCommnuicator=(FragmentCommnuicator) getActivity();
        return view;
    }

    @Override
    public void hienThiDanhSach(final List<Category> categoryList) {
        ExpanAdapter expanAdapter = new ExpanAdapter(getActivity(), categoryList, userid, 0, listView);
        listView.setAdapter(expanAdapter);
        expanAdapter.notifyDataSetChanged();

    }

    @Override
    public void suKien(final List<Category> categoryList) {
//        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int i) {
//                name = categoryList.get(i).getName();
//                Log.i(TAG, "onGroupExpand: " + name);
//                Intent myIntent = new Intent();
//                myIntent.setAction(Constant.SentData);
//                myIntent.putExtra(Constant.NameExpend,name);
//                getActivity().sendBroadcast(myIntent);
//                getActivity().onBackPressed();
//
//            }
//        });
//        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//
//                Toast.makeText(getActivity(), "ten con" + categoryList.get(i).getCategoryList().get(i1).getName(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }
}
