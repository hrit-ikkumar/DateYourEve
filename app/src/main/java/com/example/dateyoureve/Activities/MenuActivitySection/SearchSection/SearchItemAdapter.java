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
        holder.itemImage.setImageResource(searchItemList.get(position).getEventImage());
        holder.eventName.setText(searchItemList.get(position).getEventName());
        holder.eventLocation.setText(searchItemList.get(position).getEventLocation());
        holder.entryFee.setText(searchItemList.get(position).getEntryFee());
        holder.entryType.setText(searchItemList.get(position).getEntryType());
        holder.eventMaxSeats.setText(searchItemList.get(position).getEventMaxSeats());
        holder.eventStartTime.setText(searchItemList.get(position).getEventStartTime());
        holder.eventEndTime.setText(searchItemList.get(position).getEventEndTime());
        //holder.eventDescription.setText(searchItemList.get(position).getEventDescription());
        holder.registrationStartTime.setText(searchItemList.get(position).getRegistrationStartTime());
        holder.registrationEndTime.setText(searchItemList.get(position).getRegistrationEndTime());
    }

    @Override
    public int getItemCount() {
        return searchItemList.size();
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
            itemImage = itemView.findViewById(R.id.searchItemImage);
            eventName = itemView.findViewById(R.id.searchItemName);
            eventLocation = itemView.findViewById(R.id.searchItemEventLocation);
            entryFee = itemView.findViewById(R.id.searchItemEventFee);
            entryType = itemView.findViewById(R.id.searchItemEvenType);
            eventMaxSeats = itemView.findViewById(R.id.searchItemEventMaxSeats);
            eventStartTime = itemView.findViewById(R.id.searchItemEventStart);
            eventEndTime = itemView.findViewById(R.id.searchItemEventEnd);
            //eventDescription = itemView.findViewById(R.id.searchItemEventDescription);
            registrationStartTime = itemView.findViewById(R.id.searchItemEventRegistrationStart);
            registrationEndTime = itemView.findViewById(R.id.searchItemEventRegistrationEnd);
        }
    }
}