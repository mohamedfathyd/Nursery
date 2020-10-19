package com.khalej.nursery.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_charge extends AppCompatActivity {
TextInputEditText textInputEditTextName,month,details;
AppCompatButton appCompatButtonRegister;
    private apiinterface_home apiinterface;
    ProgressDialog progressDialog;
    SharedPreferences sharedpref;
    Toolbar toolbar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_charge);
        textInputEditTextName=findViewById(R.id.num_days);
        details=findViewById(R.id.details);
        month=findViewById(R.id.month);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        i=getIntent();
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
        progressDialog = ProgressDialog.show(New_charge.this, "جاري تسجيل الغياب", "Please wait...", false, false);
        progressDialog.show();


        //  String currentTime = Calendar.getInstance().getTime().toString();
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_add_absense(i.getIntExtra("id",0),i.getStringExtra("name"),
                details.getText().toString(),Integer.parseInt(month.getText().toString()),Integer.parseInt(textInputEditTextName.getText().toString()));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Toast.makeText(Shopping_car.this,details,Toast.LENGTH_LONG).show();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(New_charge.this);
                dlgAlert.setMessage(R.string.success );
                dlgAlert.setTitle("nursery");
                dlgAlert.setIcon(R.drawable.log);
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
