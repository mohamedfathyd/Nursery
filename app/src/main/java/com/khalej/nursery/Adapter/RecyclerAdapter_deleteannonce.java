package com.khalej.nursery.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.khalej.nursery.Activity.Details;
import com.khalej.nursery.Activity.Update_annonce;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_annonce;
import com.khalej.nursery.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapter_deleteannonce extends RecyclerView.Adapter<RecyclerAdapter_deleteannonce.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<contact_annonce> contactslist;
     apiinterface_home apiinterface;
    public RecyclerAdapter_deleteannonce(Context context, List<contact_annonce> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_delete_annonce,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        Glide.with(context).load(contactslist.get(position).getImage()).error(R.drawable.log).into(holder.image);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Intent intent=new Intent(context, Details.class);
               intent.putExtra("id",contactslist.get(position).getId());
               intent.putExtra("type",3);
               intent.putExtra("image",contactslist.get(position).getImage());

               intent.putExtra("details",contactslist.get(position).getName()+
                       "\n \n\n"+contactslist.get(position).getDetails()+"\n \n\n"+
                       contactslist.get(position).getDate()+"  "+contactslist.get(position).getTime());
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

        ImageView delete;
    public MyViewHolder(View itemView) {
        super(itemView);

        image=(ImageView)itemView.findViewById(R.id.imageView3);


    }
}
    public void fetchInfo(int id) {

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.delete_annonce(id);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(context,"Deleted Done",Toast.LENGTH_LONG).show();
                ((Activity)context).finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }
}