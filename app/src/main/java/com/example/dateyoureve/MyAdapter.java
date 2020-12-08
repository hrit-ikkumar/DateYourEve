package com.example.dateyoureve;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

public class MyAdapter extends FirebaseRecyclerAdapter<Event,MyAdapter.MyViewHolder>{

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Event> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Event model) {
        holder.heading.setText(model.getEventName());
        holder.description.setText(model.getEventDescription());
        holder.starttime.setText(model.getEventStartTime()+"");
        holder.fees.setText(model.getEntryFee()+"");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView heading,description,starttime,fees;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            heading=(TextView)itemView.findViewById(R.id.heading);
            description=(TextView)itemView.findViewById(R.id.description);
            starttime=(TextView)itemView.findViewById(R.id.starttime);
            fees=(TextView)itemView.findViewById(R.id.fees);
        }
    }

}
