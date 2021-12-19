package com.example.mymoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Stats_Expenses_Adapter extends RecyclerView.Adapter<Stats_Expenses_Adapter.ViewHolder> {

    private List<Stat_expenses_Item> listItems;
    private Context mContext;

    public Stats_Expenses_Adapter(List<Stat_expenses_Item> listItems, Context mContext)
    {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stat_expenses_item, parent, false);
        return new Stats_Expenses_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Stats_Expenses_Adapter.ViewHolder holder, int position) {
        final Stat_expenses_Item itemList = listItems.get(position);
        if(itemList!=null) {
            holder.expensesItem.setText(itemList.getNameOfExpensesItem());
            holder.expensesPercent.setText(itemList.getPercentOfExpensesItem());
        }
    }


    @Override
    public int getItemCount() {
        if(listItems.size() >= 1) return listItems.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView expensesItem;
        public TextView expensesPercent;

        public ViewHolder(View itemView)
        {
            super(itemView);
            expensesItem = (TextView) itemView.findViewById(R.id.expenses_item);
            expensesPercent = (TextView) itemView.findViewById(R.id.expenses_percent);
        }

    }

    public void setList(List<Stat_expenses_Item> list)
    {
        listItems = list;
        notifyDataSetChanged();
    }

}
