package com.example.dateyoureve;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class NotAdapter extends FirebaseRecyclerAdapter<Notification,NotAdapter.MyViewHolder> {
    
    public NotAdapter(@NonNull FirebaseRecyclerOptions<Notification> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotAdapter.MyViewHolder holder, int position, @NonNull Notification model) {
        
    }

    @NonNull
    @Override
    public NotAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate((R.layout.not_layout),parent,false);
        return new MyViewHolder(view);
    }
    
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView not_list;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            not_list=(TextView) itemView.findViewById(R.id.not_list);
        }
    }
    
    
}
