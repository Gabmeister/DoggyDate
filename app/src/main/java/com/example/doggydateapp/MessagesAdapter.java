package com.example.doggydateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Messages> messages;

    public MessagesAdapter(Context context, ArrayList<Messages> messages) {

    }

    @NonNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerviewmessages, parent, false);
        return new MessagesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MyViewHolder holder, int position) {
        //assigning values to views in layoutfile
        //based on the position of the recycler view
        holder.messageView.setText(messages.get(position).getMessage());
        holder.imageview.setImageResource(messages.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //number of items in the recycler view
        return messages.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //getting views for recycler view

        ImageView imageview;
        TextView messageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageView);
            messageView = itemView.findViewById(R.id.TextViewMessage);
        }
    }
}