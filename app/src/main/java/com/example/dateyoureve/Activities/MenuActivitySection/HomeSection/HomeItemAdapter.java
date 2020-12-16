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
    List<HomeModel> homeItemList;
    public HomeItemAdapter(List<HomeModel> homeItemList)  {
        this.homeItemList = homeItemList;
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
        holder.itemImage.setImageResource(homeItemList.get(position).getEventImage());
        holder.eventName.setText(homeItemList.get(position).getEventName());
        holder.eventLocation.setText(homeItemList.get(position).getEventLocation());
        holder.entryFee.setText(homeItemList.get(position).getEntryFee());
        holder.entryType.setText(homeItemList.get(position).getEntryType());
        holder.eventMaxSeats.setText(homeItemList.get(position).getEventMaxSeats());
        holder.eventStartTime.setText(homeItemList.get(position).getEventStartTime());
        holder.eventEndTime.setText(homeItemList.get(position).getEventEndTime());
        //holder.eventDescription.setText(homeItemList.get(position).getEventDescription());
        holder.registrationStartTime.setText(homeItemList.get(position).getRegistrationStartTime());
        holder.registrationEndTime.setText(homeItemList.get(position).getRegistrationEndTime());
    }

    @Override
    public int getItemCount() {
        return homeItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  itemImage;
        TextView eventName;
        TextView eventLocation;
        TextView entryFee;
        TextView entryType;
        TextView eventMaxSeats;
        TextView eventStartTime;
        TextView eventEndTime;
        TextView eventDescription;
        TextView registrationStartTime;
        TextView registrationEndTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.homeItemImage);
            eventName = itemView.findViewById(R.id.homeItemName);
            eventLocation = itemView.findViewById(R.id.homeItemEventLocation);
            entryFee = itemView.findViewById(R.id.homeItemEventFee);
            entryType = itemView.findViewById(R.id.homeItemEvenType);
            eventMaxSeats = itemView.findViewById(R.id.homeItemEventMaxSeats);
            eventStartTime = itemView.findViewById(R.id.homeItemEventStart);
            eventEndTime = itemView.findViewById(R.id.homeItemEventEnd);
            //eventDescription = itemView.findViewById(R.id.homeItemEventDescription);
            registrationStartTime = itemView.findViewById(R.id.homeItemEventRegistrationStart);
            registrationEndTime = itemView.findViewById(R.id.homeItemEventRegistrationEnd);
        }
    }
}
