package com.example.hoaiduc.quanlychitieu.view.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoaiduc.quanlychitieu.Adapter.OverViewAdapter;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.history.PresenterLogicHisotory;

import java.util.List;

/**
 * Created by hoaiduc on 5/1/2018.
 */

public class FragmentOverView extends Fragment implements ViewHistory
{
    RecyclerView recyclerView;
    PresenterLogicHisotory presenterHistory;
    int userId;
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_overview, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recylerview);
        userId=getArguments().getInt("userid");
        presenterHistory=new PresenterLogicHisotory(this);
        presenterHistory.showOverview(userId);
        return view;
    }

    @Override
    public void displayHistory(List<HistoryMoneyIntOut> list) {

    }

    @Override
    public void displayOverView(List<HistoryMoneyIntOut> list)
    {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayout);
        OverViewAdapter overViewAdapter=new OverViewAdapter(list,getActivity());
        recyclerView.setAdapter(overViewAdapter);
    }
}
