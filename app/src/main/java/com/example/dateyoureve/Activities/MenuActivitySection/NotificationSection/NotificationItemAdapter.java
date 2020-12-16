package com.example.dateyoureve.Activities.MenuActivitySection.NotificationSection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dateyoureve.R;

import java.util.List;

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.ViewHolder> {
    List<NotificationModel> notificationItemList;

    public NotificationItemAdapter(List<NotificationModel> notificationItemList){
        this.notificationItemList = notificationItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notificationText.setText(notificationItemList.get(position).getNotification());
    }

    @Override
    public int getItemCount() {
        return notificationItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView notificationText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText = itemView.findViewById(R.id.notification_item);
        }
    }
}