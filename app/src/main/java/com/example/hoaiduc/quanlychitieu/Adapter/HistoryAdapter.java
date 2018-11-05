package com.example.hoaiduc.quanlychitieu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.HistoryMoneyIntOut;
import com.example.hoaiduc.quanlychitieu.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoaiduc on 4/18/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int ITEM = 1;
    private String TAG="HistoryAdapter";
    List<HistoryMoneyIntOut> stringArrayList = new ArrayList<>();
    Context context;

    public HistoryAdapter(List<HistoryMoneyIntOut> stringArrayList, Context context) {
        this.stringArrayList = stringArrayList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HEADER) return HEADER;
        else return ITEM;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);
            return new HeaderHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if(stringArrayList.size()>0 )
        {
            if (position == 0)
            {
                DecimalFormat format=new DecimalFormat("##,###,###,###");
                HeaderHolder headerHolder = (HeaderHolder) holder;

                headerHolder.tvMoneyInput.setText(String.valueOf(format.format(stringArrayList.get(position).getMoneyInput())));
                headerHolder.tvMoneyOuput.setText(String.valueOf(format.format(stringArrayList.get(position).getMoneyOutput())));
                headerHolder.tvMoney.setText(String.valueOf(format.format(stringArrayList.get(position).getWalletmoney())));
                headerHolder.tvDate.setText(stringArrayList.get(position).getCreateday());
                headerHolder.tvSymbol.setText(stringArrayList.get(position).getCursymbol());
                headerHolder.tvSymbol1.setText(stringArrayList.get(position).getCursymbol());
                headerHolder.tvSymbol2.setText(stringArrayList.get(position).getCursymbol());
            }
            else
            {
//            case 1:
                DecimalFormat format=new DecimalFormat("##,###,###,###");
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.tvName.setText(stringArrayList.get(position-1).getName());
                Log.d(TAG,"name"+stringArrayList.get(position-1).getName());
                itemHolder.tvMoney.setText(String.valueOf(format.format(stringArrayList.get(position-1).getMoney())));
                itemHolder.tvSymbol3.setText(stringArrayList.get(position-1).getCursymbol());
                String note=stringArrayList.get(position-1).getNote();
                if(note.equals(null))
                {
                    itemHolder.tvNote.setText("");
                }
                else
                {
                    itemHolder.tvNote.setText(stringArrayList.get(position-1).getNote());
                }
                Picasso.with(context).load(stringArrayList.get(position-1).getImage())
                        .placeholder(R.drawable.cpb_background)
                        .error(R.drawable.facebookiconsmall)
                        .into( itemHolder.imageView);
//                break;
            }
        }

    }



    @Override
    public int getItemCount() {
        return stringArrayList==null ?0:stringArrayList.size()+1;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder{
       TextView tvDate,tvMoneyInput,tvMoneyOuput,tvMoney,tvSymbol,tvSymbol1,tvSymbol2;

        public HeaderHolder(View itemView) {
            super(itemView);
           tvDate= (TextView) itemView.findViewById(R.id.txtdate);
           tvMoneyInput= (TextView) itemView.findViewById(R.id.numberInt);
           tvMoneyOuput= (TextView) itemView.findViewById(R.id.numberoutput);
           tvMoney=(TextView)       itemView.findViewById(R.id.txttotalmoney);
           tvSymbol=(TextView)       itemView.findViewById(R.id.symbol);
           tvSymbol1=(TextView)       itemView.findViewById(R.id.symbol1);
           tvSymbol2=(TextView)       itemView.findViewById(R.id.symbol2);

        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvMoney,tvSymbol3,tvNote;
        ImageView imageView;
        public ItemHolder(View itemView)
        {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvMoney= (TextView) itemView.findViewById(R.id.money);
            imageView=(ImageView)itemView.findViewById(R.id.img);
            tvSymbol3=(TextView)itemView.findViewById(R.id.symbol3);
            tvNote=(TextView)   itemView.findViewById(R.id.note);
        }
    }


}
