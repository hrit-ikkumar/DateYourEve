package com.example.dateyoureve.Activities.MenuActivitySection.SearchSection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dateyoureve.R;

import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {
    List<SearchModel> searchItemList;

    public SearchItemAdapter(List<SearchModel> searchItemList){
        this.searchItemList=searchItemList;
    }

    @NonNull
    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemImage.setImageResource(searchItemList.get(position).getEntryFee());
        holder.itemText.setText(searchItemList.get(position).getEventName());
    }

    @Override
    public int getItemCount() {
        return searchItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.searchItemImage);
            itemText = itemView.findViewById(R.id.searchItemName);
        }
    }
}