package com.khalej.nursery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalej.nursery.Activity.Show;
import com.khalej.nursery.Model.contact_home;
import com.khalej.nursery.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerAdapter_second extends RecyclerView.Adapter<RecyclerAdapter_second.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<contact_home> contactslist;


    public RecyclerAdapter_second(Context context, List<contact_home> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_circle_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        myTypeface = Typeface.createFromAsset(context.getAssets(), "Nasser.otf");

        holder.Name.setText(contactslist.get(position).getname());
        holder.Name.setTypeface(myTypeface);
        Glide.with(context).load(contactslist.get(position).getImg()).error(R.drawable.log).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=contactslist.get(position).getId();
                String name=contactslist.get(position).getname();
                String price=contactslist.get(position).getPrice();
                String link=contactslist.get(position).getDescription();
                String image=contactslist.get(position).getImg();
                Intent intent=new Intent(context, Show.class);
                intent.putExtra("name",name);
                intent.putExtra("image",image);
                intent.putExtra("description",link);
                intent.putExtra("id",id);
                intent.putExtra("price",price);
                intent.putExtra("error",contactslist.get(position).getError());
                context.startActivity(intent);
            }

        });

    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView)itemView.findViewById(R.id.photo);

        }
    }}