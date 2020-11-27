package com.khalej.nursery.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.khalej.nursery.Activity.Up_annonce;
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

public class RecyclerAdapter_updateannonce extends RecyclerView.Adapter<RecyclerAdapter_updateannonce.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<contact_annonce> contactslist;
     apiinterface_home apiinterface;
    public RecyclerAdapter_updateannonce(Context context, List<contact_annonce> contactslist){
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

//               new AlertDialog.Builder(context)
//                       .setTitle("nursery")
//                       .setIcon(R.drawable.log)
//                       .setMessage("Are you sure want to delete ? ??")
//                       .setIcon(android.R.drawable.ic_dialog_alert)
//                       .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                           public void onClick(DialogInterface dialog, int whichButton) {
//                               int id=contactslist.get(position).getId();
//                               fetchInfo(id);
//                           }})
//                       .setNegativeButton(android.R.string.no, null).show();
//

               Intent intent=new Intent(context, Up_annonce.class);
               intent.putExtra("image",contactslist.get(position).getImage());
               intent.putExtra("id",contactslist.get(position).getId());
               intent.putExtra("details",contactslist.get(position).getDetails());
               intent.putExtra("name",contactslist.get(position).getName());
               intent.putExtra("date",contactslist.get(position).getDate());
               intent.putExtra("time",contactslist.get(position).getTime());
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