package com.example.hoaiduc.quanlychitieu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Statistical;
import com.example.hoaiduc.quanlychitieu.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by hoaiduc on 4/20/2018.
 */

public class StatisticaAdapter extends RecyclerView.Adapter<StatisticaAdapter.MyViewHolder>
{

    private List<Statistical> statisticalList;
    private Context context;

    public StatisticaAdapter(List<Statistical> statisticalList, Context context) {
        this.statisticalList = statisticalList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_statistical, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        DecimalFormat formatter = new DecimalFormat("#,###,###,###");
        Statistical statistical=statisticalList.get(position);

        String date =statistical.getCreteday();
        String[] calend = date.split("-");
        int mon = Integer.parseInt(calend[1]);
        int year=Integer.parseInt(calend[0]);
        holder.txtSymbol.setText(statistical.getCurSymbol());
        holder.txtMonth.setText(String.valueOf("Tháng"+mon));
        holder.txtYear.setText(String.valueOf("Năm"+year));
        holder.txtMoney.setText(String.valueOf(formatter.format(statistical.getMoney())));
    }

    @Override
    public int getItemCount() {
        return statisticalList==null?0:statisticalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMonth, txtYear, txtMoney,txtSymbol;

        public MyViewHolder(View view) {
            super(view);
            txtMonth = (TextView) view.findViewById(R.id.smonth);
            txtMoney= (TextView) view.findViewById(R.id.smoney);
            txtYear= (TextView) view.findViewById(R.id.syear);
            txtSymbol=(TextView) view.findViewById(R.id.symbol);

        }
    }
}