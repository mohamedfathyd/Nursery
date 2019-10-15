package com.khalej.tarshed.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.khalej.tarshed.Model.Apiclient_home;
import com.khalej.tarshed.Model.apiinterface_home;
import com.khalej.tarshed.Model.contact_userinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_ {
    private List<contact_userinfo> contactList;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    public void fetchInfo(final Context context, String phone, String password){
        sharedpref = context.getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        progressDialog = ProgressDialog.show(context,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_userinfo>> call= apiinterface.getcontacts_login(phone,
               password , 1);
        call.enqueue(new Callback<List<contact_userinfo>>() {
            @Override
            public void onResponse(Call<List<contact_userinfo>> call, Response<List<contact_userinfo>> response) {
                contactList = response.body();
                progressDialog.dismiss();
            try{

                edt.putInt("id",contactList.get(0).getId());
                edt.putFloat("totalprice",0);
                edt.putString("name",contactList.get(0).getName());
                edt.putString("phone",contactList.get(0).getPhone());
                edt.putString("address",contactList.get(0).getmaddress());
                edt.putString("password",contactList.get(0).getPassword());
                edt.putString("points",contactList.get(0).getPoints());
                edt.putString("charge",contactList.get(0).getPoints());
                edt.putString("date", String.valueOf(contactList.get(0).getAge()));
                edt.putString("remember","yes");
                edt.apply();
                context.startActivity(new Intent(context, MainActivity.class));}
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<contact_userinfo>> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
