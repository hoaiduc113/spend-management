package com.example.hoaiduc.quanlychitieu.view.statistical;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Adapter.StatisticaAdapter;
import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Statistical;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.presenter.statistical.PresenterLogicStatistical;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hoaiduc on 4/19/2018.
 */

public class FragmentStatistical extends Fragment implements ViewStatistical,View.OnClickListener,RadioGroup.OnCheckedChangeListener {
        List<Statistical> list=new ArrayList<>();
        RecyclerView recyclerView;
        BarChart barChart;
        int month,endMonth;
        TextView numberPicker,numberPicker2;
        PresenterLogicStatistical presenterLogicStatistical;
        Statistical statistical;
        RadioGroup rdGroup;
        int userId;
        RadioButton rdExpense,rdIncome;
        int years,months;
        int year,group;
    StatisticaAdapter statisticaAdapter;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_fragment_statical, container, false);
            barChart = (BarChart) view.findViewById(R.id.barChart);
            numberPicker = (TextView) view.findViewById(R.id.number_picker);
            numberPicker2=(TextView)view.findViewById(R.id.number_picker2);
            rdGroup=(RadioGroup) view.findViewById(R.id.group);
            recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
            rdExpense=(RadioButton)view.findViewById(R.id.expense);
            rdIncome=(RadioButton)view.findViewById(R.id.income);
            userId=getArguments().getInt("userid");
            group=0;

            //set event
            numberPicker.setOnClickListener(this);
            numberPicker2.setOnClickListener(this);
            rdGroup.setOnCheckedChangeListener(this);
            year= Calendar.getInstance().get(Calendar.YEAR);
            years= Calendar.getInstance().get(Calendar.YEAR);
            month= Calendar.getInstance().get(Calendar.MONTH)+1;
            months= Calendar.getInstance().get(Calendar.MONTH)+1;
            statistical=new Statistical();
            numberPicker.setText("Tháng "+month+ " năm "+year);
            numberPicker2.setText("Tháng "+months+ " năm "+year);
            presenterLogicStatistical=new PresenterLogicStatistical(this);


            statistical.setGroup(group);
            statistical.setYeard(year);
            statistical.setUserid(userId);
            statistical.setFirstMonth(month);
            statistical.setEndMonth(months);
            presenterLogicStatistical.lisStatisticals(statistical);
            barChart.notifyDataSetChanged();

            return view;
        }
    @Override
    public void perForm(List<Statistical> statisticals)
    {
        list=statisticals;
        List<BarEntry> barEntries=new ArrayList<>();
// list=new ArrayList<>();
//        int size=list.size();
//        int count=0;

//        for(int i=0;i<list.size();i++)
//        {
//            count++;
//            if(count<size)
//            {
//
//                String date =simpleDateFormat.format(list.get(count).getCreteday());
//                String[] calend = date.split("-");
//                month = Integer.parseInt(calend[1]);
//                Toast.makeText(getActivity(), ""+list.get(count).getMoney(), Toast.LENGTH_SHORT).show();
//                barEntries.add(new BarEntry(list.get(count).getMoney(),month-1));
//            }
//            else if(count>size)
//            {
//                barEntries.add(new BarEntry(0,i));
//            }
//
//
//        }
        if(statisticals.equals(null)||statisticals.size()==0)
        {
            barChart.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else
        {
            int  a=statisticals.get(0).getMoney();
            for(int i=0;i<statisticals.size();i++)
            {
                barEntries.add(new BarEntry(statisticals.get(i).getMoney(),i));
            }
            List<String> theDates=new ArrayList<>();
            for(int j=0;j<statisticals.size();j++)
            {
                String date =statisticals.get(j).getCreteday();
                String[] calend = date.split("-");
                int mon = Integer.parseInt(calend[1]);
                theDates.add("tháng "+mon);

            }

         //   barChart.setMaxVisibleValueCount(1000000);
            BarDataSet barDataSet=new BarDataSet(barEntries,"thống kê");
            BarData data=new BarData(theDates,barDataSet);
            barChart.setData(data);
            barChart.setTouchEnabled(true);
            barChart.setDragEnabled(true);
            barChart.setScaleEnabled(true);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayout=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayout);
            StatisticaAdapter statisticaAdapter=new StatisticaAdapter(list,getActivity());
            recyclerView.setAdapter(statisticaAdapter);
        }

    }

    @Override
    public void onClick(View view)
    {
        int id=view.getId();
        switch (id)
        {
            case R.id.number_picker:


                View views=getLayoutInflater().inflate(R.layout.dialog_spinner,null);
                final Spinner spinner=(Spinner) views.findViewById(R.id.sp);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());

                alertDialog.setTitle("tháng"+String.valueOf(month)+"năm"+String.valueOf(year));
                String a[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,a);
                spinner.setAdapter(arrayAdapter);
                alertDialog.setPositiveButton("lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        month=Integer.parseInt(spinner.getSelectedItem().toString());
                            numberPicker.setText(" Tháng"+month+" năm "+year);
                        statistical.setFirstMonth(month);
                        presenterLogicStatistical.lisStatisticals(statistical);
                        if(list.equals(null)||list.size()==0)
                        {
                            barChart.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);

                        }
                        else
                        {
                            barChart.setVisibility(View.VISIBLE);
                            barChart.notifyDataSetChanged();
//                            statisticaAdapter.notifyDataSetChanged();
                        }


                    }
                });
                alertDialog.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.setView(views);
                AlertDialog dialog=alertDialog.create();
                dialog.show();
                break;
            case R.id.number_picker2:


                View viewss=getLayoutInflater().inflate(R.layout.dialog_spinner2,null);
                final Spinner spinners=(Spinner) viewss.findViewById(R.id.sp2);
                AlertDialog.Builder alertDialogs=new AlertDialog.Builder(getContext());

                alertDialogs.setTitle("tháng "+String.valueOf(months)+"  năm"+String.valueOf(years));
                String as[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
                ArrayAdapter<String> arrayAdapters=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,as);
                spinners.setAdapter(arrayAdapters);
                alertDialogs.setPositiveButton("lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        endMonth=Integer.parseInt(spinners.getSelectedItem().toString());
                        numberPicker2.setText(" Tháng  "+endMonth+"  năm "+years);
                        statistical.setEndMonth(endMonth);
                        presenterLogicStatistical.lisStatisticals(statistical);
                        if(list.equals(null)||list.size()==0)
                        {
                            barChart.setVisibility(View.INVISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);

                        }
                        else
                        {
                            barChart.setVisibility(View.VISIBLE);
                            barChart.notifyDataSetChanged();
    //                        statisticaAdapter.notifyDataSetChanged();
                        }


                    }
                });
                alertDialogs.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialogs.setView(viewss);
                AlertDialog dialogs=alertDialogs.create();
                dialogs.show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i)
        {
            case R.id.expense:
                group=0;
                statistical.setGroup(group);
                presenterLogicStatistical.lisStatisticals(statistical);
                if(list.equals(null)||list.size()==0)
                {
                    barChart.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);

                }
                else
                {
                    barChart.setVisibility(View.VISIBLE);
                    barChart.notifyDataSetChanged();
 //                   statisticaAdapter.notifyDataSetChanged();


                }

                break;
            case R.id.income:
                group=1;
                statistical.setGroup(group);
                presenterLogicStatistical.lisStatisticals(statistical);
                if(list.equals(null)||list.size()==0)
                {
                    barChart.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);

                }
                else
                {
                    barChart.setVisibility(View.VISIBLE);
                    barChart.notifyDataSetChanged();
  //                  statisticaAdapter.notifyDataSetChanged();
                }

                break;
        }
    }
}
