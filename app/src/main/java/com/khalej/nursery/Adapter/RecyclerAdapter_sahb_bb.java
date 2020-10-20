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
import com.khalej.nursery.Activity.chat_s;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_userinfo;
import com.khalej.nursery.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter_sahb_bb extends RecyclerView.Adapter<RecyclerAdapter_sahb_bb.MyViewHolder> {
    Typeface myTypeface;
    int points;
    private Context context;
    List<contact_userinfo> contactslist;

    apiinterface_home apiinterface;

    public RecyclerAdapter_sahb_bb(Context context, List<contact_userinfo> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sahb,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.Name.setText("اسم :"+contactslist.get(position).getName());

        holder.finish_date.setText("العنوان :" +contactslist.get(position).getmaddress());
        holder.price.setText(contactslist.get(position).getPhone()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, chat_s.class);
                intent.putExtra("id",contactslist.get(position).getId());
                intent.putExtra("name",contactslist.get(position).getName());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,finish_date,price,idd;
    LinearLayout rased;

    public MyViewHolder(View itemView) {
        super(itemView);

        Name=(TextView)itemView.findViewById(R.id.txt_fish_title);
        finish_date=(TextView)itemView.findViewById(R.id.txt_title);
        price=(TextView)itemView.findViewById(R.id.txt_);
        idd=itemView.findViewById(R.id.idd);
        rased=itemView.findViewById(R.id.rased);


    }
}

}