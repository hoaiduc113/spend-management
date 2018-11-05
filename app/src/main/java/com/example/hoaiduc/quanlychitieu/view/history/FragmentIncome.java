package com.example.hoaiduc.quanlychitieu.view.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Adapter.HistoryAdapter;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.history.PresenterLogicHisotory;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.github.badoualy.datepicker.MonthView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hoaiduc on 5/1/2018.
 */

public class FragmentIncome extends Fragment  implements ViewHistory
{
    private String TAG="FragmentExpense";
    RecyclerView recyclerView;
    DatePickerTimeline timeline;
    HistoryAdapter historyAdapter;
    PresenterLogicHisotory presenterLogicHisotory;
    List<HistoryMoneyIntOut> historyMoneyIntOutList=new ArrayList<>();
    TextView tvData;
    String date;
    int userId;
    int  dates;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_income,container,false);
        //get value userid
        userId=getArguments().getInt("userid");
        recyclerView=(RecyclerView)view.findViewById(R.id.rc);
        presenterLogicHisotory=new PresenterLogicHisotory(this);
        tvData=(TextView)view.findViewById(R.id.data);
        timeline=(DatePickerTimeline)view.findViewById(R.id.timelines);
        timeline.setFirstVisibleDate(2018, Calendar.JANUARY, Calendar.YEAR);
        timeline.setLastVisibleDate(2020,Calendar.JANUARY, Calendar.YEAR);
        timeline.setDateLabelAdapter(new MonthView.DateLabelAdapter() {
            @Override
            public CharSequence getLabel(Calendar calendar, int index) {
                return Integer.toString(calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) % 2000);
            }
        });

        //current day
        dates=Calendar.getInstance().get(Calendar.DATE);
        int  year=Calendar.getInstance().get(Calendar.YEAR);
        int month=Calendar.getInstance().get(Calendar.MONTH)+1;
        date=String.valueOf(year+"-"+(month)+"-"+dates);
        timeline.setSelectedDate(year, Calendar.MONTH+1,dates);
        Log.d(TAG,date+"");
        historyMoneyIntOutList.clear();
        presenterLogicHisotory.perFormHistory(userId,1,date);

        //display current date

        timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                int months=month+2;
                String days=String.valueOf(year+"-"+months+"-"+day);
                Log.d(TAG,""+date);
                historyMoneyIntOutList.clear();
                presenterLogicHisotory.perFormHistory(userId,1,days);
                if(historyMoneyIntOutList.size()>0)
                {
                    historyAdapter.notifyDataSetChanged();
                }

            }
        });
        return view;
    }
    @Override
    public void displayHistory(List<HistoryMoneyIntOut> list)
    {
        historyMoneyIntOutList=list;
        if(historyMoneyIntOutList.size()==0)
        {
            recyclerView.setVisibility(View.INVISIBLE);
            tvData.setVisibility(View.VISIBLE);

        }
        else
        {
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayout=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayout);
            DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getContext(),linearLayout.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
            historyAdapter=new HistoryAdapter(historyMoneyIntOutList,getActivity());
            recyclerView.setAdapter(historyAdapter);
            tvData.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            historyAdapter.notifyDataSetChanged();

        }
    }
    @Override
    public void displayOverView(List<HistoryMoneyIntOut> list) {

    }
    @Override
    public void onResume() {
        super.onResume();

        historyMoneyIntOutList.clear();
        presenterLogicHisotory.perFormHistory(userId,1,date);
        if(historyMoneyIntOutList.size()>0)
        {
            historyAdapter.notifyDataSetChanged();
        }

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        switch (id)
//        {
//            case R.id.getbackdate:
//                timeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
//                    @Override
//                    public void onDateSelected(int year, int month, int day, int index) {
//                        int months=month+1;
//                        date=String.valueOf(year+"-"+months+"-"+day);
//                        Log.d(TAG,""+date);
//                        historyMoneyIntOutList.clear();
//                        presenterLogicHisotory.perFormHistory(userId,1,date);
//                        if(historyMoneyIntOutList.size()>0)
//                        {
//                            historyAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//                });
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
}
