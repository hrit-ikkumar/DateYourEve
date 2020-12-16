package com.example.dateyoureve.Activities.MenuActivitySection.HomeSection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dateyoureve.R;

import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {
    List<HomeModel> hoemItemList;
    public HomeItemAdapter(List<HomeModel> homeItemList)  {
        this.hoemItemList = homeItemList;
    }

    @NonNull
    @Override
    public HomeItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemAdapter.ViewHolder holder, int position) {
        holder.itemImage.setImageResource(hoemItemList.get(position).getImage());
        holder.itemText.setText(hoemItemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return hoemItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  itemImage;
        TextView itemText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.homeItemImage);
            itemText = itemView.findViewById(R.id.homeItemName);
        }
    }
}
