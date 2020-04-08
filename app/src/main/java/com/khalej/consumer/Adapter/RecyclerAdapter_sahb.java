package com.khalej.consumer.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khalej.consumer.Activity.MainAdmin;
import com.khalej.consumer.Model.Apiclient_home;
import com.khalej.consumer.Model.apiinterface_home;
import com.khalej.consumer.Model.contact_userinfo;
import com.khalej.consumer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter_sahb extends RecyclerView.Adapter<RecyclerAdapter_sahb.MyViewHolder> {
    Typeface myTypeface;
    int points;
    private Context context;
    List<contact_userinfo> contactslist;

    apiinterface_home apiinterface;

    public RecyclerAdapter_sahb(Context context, List<contact_userinfo> contactslist){
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

        holder.finish_date.setText("رقم العداد :" +contactslist.get(position).getmaddress());
        holder.idd.setText(contactslist.get(position).getPoints()+"");
         holder.price.setText(contactslist.get(position).getPhone()+"");

       holder.rased.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               fetchInfo_update_points(contactslist.get(position).getId());
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
    public void fetchInfo_update_points(final int id) {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = dialogBuilder.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit, null);

        dialogBuilder.setTitle("كوزمر");

        dialogBuilder.setCancelable(false);


        final EditText etComments = (EditText) dialogView.findViewById(R.id.etComments);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                points = Integer.parseInt(etComments.getText().toString());
                fetch(id,points);
            }
        });


        dialogBuilder.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();

    }
    public void fetch(int id,int point){
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.getpoints(id, point);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(context,"تم التعديل",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, MainAdmin.class));
                ((Activity)context).finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}