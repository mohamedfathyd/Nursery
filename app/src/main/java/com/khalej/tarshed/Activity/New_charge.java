package com.khalej.tarshed.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.khalej.tarshed.Model.Apiclient_home;
import com.khalej.tarshed.Model.apiinterface_home;
import com.khalej.tarshed.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_charge extends AppCompatActivity {
TextInputEditText textInputEditTextName;
AppCompatButton appCompatButtonRegister;
    private apiinterface_home apiinterface;
    ProgressDialog progressDialog;
    SharedPreferences sharedpref;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_charge);
        textInputEditTextName=findViewById(R.id.textInputEditTextName);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
       this.setTitle("");
        sharedpref =getSharedPreferences("tarched", Context.MODE_PRIVATE);
        appCompatButtonRegister=findViewById(R.id.appCompatButtonRegister);

        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata();
            }
        });
    }
    public void fetchdata() {
        progressDialog = ProgressDialog.show(New_charge.this, "جاري تسجيل طلبك", "Please wait...", false, false);
        progressDialog.show();


        //  String currentTime = Calendar.getInstance().getTime().toString();
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_order(sharedpref.getString("name",""),sharedpref.getString("phone",""),
                sharedpref.getString("address",""),sharedpref.getInt("id",0), Double.parseDouble(textInputEditTextName.getText().toString()));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Toast.makeText(Shopping_car.this,details,Toast.LENGTH_LONG).show();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(New_charge.this);
                dlgAlert.setMessage(R.string.success );
                dlgAlert.setTitle("ترشيد");
                dlgAlert.setPositiveButton("حسنا", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                finish();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //   Toast.makeText(get_order.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
