package com.example.hoaiduc.quanlychitieu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by hoaiduc on 5/1/2018.
 */

public class OverViewAdapter  extends RecyclerView.Adapter<OverViewAdapter.MyViewHolder>
{
    private List<HistoryMoneyIntOut> historyMoneyIntOutList;
    private Context context;

    public OverViewAdapter(List<HistoryMoneyIntOut> historyMoneyIntOutList, Context context) {
        this.historyMoneyIntOutList = historyMoneyIntOutList;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_overview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        DecimalFormat format=new DecimalFormat("##,###,###,###");
        SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        holder.tvName.setText(historyMoneyIntOutList.get(position).getName());
      //  Log.d(TAG,"name"+stringArrayList.get(position-1).getName());
        holder.tvMoney.setText(String.valueOf(format.format(historyMoneyIntOutList.get(position).getMoney())));
        holder.tvSymbol3.setText(historyMoneyIntOutList.get(position).getCursymbol());
        holder.tvDate.setText(historyMoneyIntOutList.get(position).getCreateday());
        Picasso.with(context).load(historyMoneyIntOutList.get(position).getImage())
                .placeholder(R.drawable.cpb_background)
                .error(R.drawable.facebookiconsmall)
                .into( holder.imageView);
    }

    @Override
    public int getItemCount() {
        return historyMoneyIntOutList==null?0:historyMoneyIntOutList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvMoney,tvSymbol3,tvDate;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvMoney= (TextView) itemView.findViewById(R.id.money);
            imageView=(ImageView)itemView.findViewById(R.id.img);
            tvSymbol3=(TextView)itemView.findViewById(R.id.symbol3);
            tvDate=(TextView)   itemView.findViewById(R.id.date);


        }
    }
}
