package com.example.mymoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Stats_Diagrams_Adapter extends RecyclerView.Adapter<Stats_Diagrams_Adapter.ViewHolder> {
    private List<Stat_diagram_Item> listItems;
    private Context mContext;

    public Stats_Diagrams_Adapter(List<Stat_diagram_Item> listItems, Context mContext)
    {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Stats_Diagrams_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stat_diagram_item, parent, false);
        return new Stats_Diagrams_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Stats_Diagrams_Adapter.ViewHolder holder, int position) {
        final Stat_diagram_Item itemList = listItems.get(position);
        if(itemList!=null) {
            holder.DiagramItem.setText(itemList.getNameOfDiagramItem());
            holder.DiagramPercent.setMax(itemList.getItemBudget());
            holder.DiagramPercent.setProgress(itemList.getPercentOfDiagramItem());
        }
    }

    @Override
    public int getItemCount() {
        if(listItems.size() >= 1) return listItems.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView DiagramItem;
        public ProgressBar DiagramPercent;

        public ViewHolder(View itemView)
        {
            super(itemView);
            DiagramItem = (TextView) itemView.findViewById(R.id.ItemCategoryName);
            DiagramPercent = (ProgressBar) itemView.findViewById(R.id.ItemProgressBar);
        }

    }

    public void setList(List<Stat_diagram_Item> list)
    {
        listItems = list;
        notifyDataSetChanged();
    }


}
