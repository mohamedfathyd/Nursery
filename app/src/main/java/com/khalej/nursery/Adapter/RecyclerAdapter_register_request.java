package com.khalej.nursery.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khalej.nursery.Activity.New_charge;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_userinfo;
import com.khalej.nursery.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter_register_request extends RecyclerView.Adapter<RecyclerAdapter_register_request.MyViewHolder> {
    Typeface myTypeface;
    int points;
    private Context context;
    List<contact_userinfo> contactslist;

    apiinterface_home apiinterface;

    public RecyclerAdapter_register_request(Context context, List<contact_userinfo> contactslist){
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
        if(contactslist.get(position).getType()==1){
        holder.finish_date.setText("نوع المستخدم :   ولي أمر");}
        else{
            holder.finish_date.setText("نوع المستخدم  :  مشرف");
        }
        holder.price.setText(contactslist.get(position).getPhone()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("nursery")
                        .setMessage("هل انت متأكد  من قبول طلب هذا المشترك ؟")
                        .setIcon(R.drawable.log)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                int id=contactslist.get(position).getId();
                                Toast.makeText(context,"تم قبول الطلب بنجاح",Toast.LENGTH_LONG).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
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