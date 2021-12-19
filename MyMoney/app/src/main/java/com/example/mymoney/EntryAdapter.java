package com.example.mymoney;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {

    private List<RecyclerItem> listItems;
    private Context mContext;

    public EntryAdapter(List<RecyclerItem> listItems, Context mContext)
    {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RecyclerItem itemList = listItems.get(position);
        if(itemList!=null) {
            holder.title.setText(itemList.getTitle());
            holder.description.setText(itemList.getDescription());
            holder.cost.setText(Float.toString(itemList.getCost()));
        }
    }


    @Override
    public int getItemCount() {
        if(listItems.size() >= 1) return listItems.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView description;
        public TextView cost;

        public ViewHolder(View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Name_of_entry);
            description = (TextView) itemView.findViewById(R.id.description_of_entry);
            cost = (TextView) itemView.findViewById(R.id.num_of_spent_entry);
        }

    }

    public void setList(List<RecyclerItem> list)
    {
        listItems = list;
        notifyDataSetChanged();
    }

}
