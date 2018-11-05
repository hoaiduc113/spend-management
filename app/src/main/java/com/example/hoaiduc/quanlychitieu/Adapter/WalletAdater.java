package com.example.hoaiduc.quanlychitieu.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Wallet;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.view.wallet.CallBack;

import java.text.DecimalFormat;
import java.util.List;
/**
 * Created by hoaiduc on 4/24/2018.
 */

public class WalletAdater extends RecyclerView.Adapter<WalletAdater.MyViewHolder>
{
    private List<Wallet> walletList;
    private SparseBooleanArray selectedItems;
    private Context context;
    CallBack mListern;
    private int focusedItem = 0;
    public WalletAdater(CallBack mListern,List<Wallet> walletList, Context context)
    {
        this.walletList = walletList;
        this.context = context;
        this.mListern=mListern;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iTemView= LayoutInflater.from(parent.getContext())
             .inflate(R.layout.layout_custom_wallet,parent,false);
        return new MyViewHolder(iTemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        DecimalFormat format=new DecimalFormat("##,###,###,###");
        Wallet wallet=walletList.get(position);
        holder.tvName.setText(wallet.getName());
        holder.tvTotalMoney.setText(String.valueOf(format.format(wallet.getMoney())));
        holder.itemView.setSelected(focusedItem == position);
    }

    @Override
    public int getItemCount() {
        return walletList==null?0:walletList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView tvName, tvTotalMoney;

        public MyViewHolder(View view) {
            super(view);
           tvName=view.findViewById(R.id.walletname);
           tvTotalMoney=view.findViewById(R.id.money);

           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mListern.onItemClick(getPosition());
                   notifyItemChanged(focusedItem);
                   focusedItem = getLayoutPosition();
                   notifyItemChanged(focusedItem);
               }
           });
        }


    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();

                // Return false if scrolled to the bounds and allow focus to move off the list
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                        return tryMoveSelection(lm, 1);
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        return tryMoveSelection(lm, -1);
                    }
                }

                return false;
            }
        });
    }
    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {
        int tryFocusItem = focusedItem + direction;

        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (tryFocusItem >= 0 && tryFocusItem < getItemCount()) {
            notifyItemChanged(focusedItem);
            focusedItem = tryFocusItem;
            notifyItemChanged(focusedItem);
            lm.scrollToPosition(focusedItem);
            return true;
        }

        return false;
    }

}
