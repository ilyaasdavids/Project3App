package com.example.project3app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class appointmentAdapter extends RecyclerView.Adapter<appointmentAdapter.MyViewHolder> {
    Context context;
    ArrayList<appointmentDomain> list;


    public appointmentAdapter(Context context, ArrayList<appointmentDomain> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.app_entry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull appointmentAdapter.MyViewHolder holder, int position) {
        appointmentDomain domain = list.get(position);
        holder.str_date.setText(domain.getDate());
        holder.str_time.setText(domain.getTime());
    }
//
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView str_date,str_time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            str_date = itemView.findViewById(R.id.textDate);
            str_time = itemView.findViewById(R.id.textTime);
        }
    }
}
