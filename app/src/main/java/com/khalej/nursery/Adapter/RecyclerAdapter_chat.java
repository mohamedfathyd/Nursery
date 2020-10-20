package com.khalej.nursery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khalej.nursery.Activity.New_charge;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_chat;
import com.khalej.nursery.Model.contact_userinfo;
import com.khalej.nursery.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter_chat extends RecyclerView.Adapter<RecyclerAdapter_chat.MyViewHolder> {
    Typeface myTypeface;
    int points;
    private Context context;
    List<contact_chat> contactslist;

    apiinterface_home apiinterface;

    public RecyclerAdapter_chat(Context context, List<contact_chat> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_data,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

       if(contactslist.get(position).getSender()==1){
        holder.sender.setText(""+contactslist.get(position).getText());}
        else {
           holder.recive.setText(""+contactslist.get(position).getText());
       }


    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView recive,sender;

    public MyViewHolder(View itemView) {
        super(itemView);

        recive=(TextView)itemView.findViewById(R.id.recive);
        sender=(TextView)itemView.findViewById(R.id.sender);



    }
}

}