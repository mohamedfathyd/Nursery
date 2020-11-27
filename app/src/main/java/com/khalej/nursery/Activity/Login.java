package com.khalej.nursery.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_userinfo;
import com.khalej.nursery.R;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    AppCompatButton Naccount;
    AppCompatButton login,appCompatButtonbb,textViewLinkRegisterrr,textViewLinkRegisterr;
    TextInputEditText textInputEditTextphone,textInputEditTextpassword;
    TextInputLayout textInputLayoutphone,textInputLayoutpassword;
    private List<contact_userinfo> contactList;
    private apiinterface_home apiinterface;
        private SharedPreferences sharedpref;
        private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    TextView forget,name;
    login_ login_;
    String lang;
    Switch swtch;
    Intent i;

   Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
       lang=sharedpref.getString("language","").trim();
        if(lang.equals(null)){
            edt.putString("language","ar");
            lang="en";
            edt.apply();
        }
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_login);

        forget=findViewById(R.id.forget);
        name=findViewById(R.id.name);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Forget_Password.class));
            }
        });
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        swtch=findViewById(R.id.swtch);



    textInputLayoutphone=(TextInputLayout)findViewById(R.id.textInputLayoutphone);
        textInputLayoutpassword=(TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputEditTextphone=(TextInputEditText)findViewById(R.id.textInputEditTextphone);


        appCompatButtonbb=findViewById(R.id.appCompatButtonbb);
        appCompatButtonbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });




        if(sharedpref.getString("remember","").trim().equals("yes")){
            edt.putFloat("totalprice",0);
            edt.apply();
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(sharedpref.getString("language","").trim().equals("ar")){
                   edt.putString("language","en");
                   edt.apply();
                   startActivity(new Intent(Login.this,Login.class));
                   finish();
               }
               else
               {
                   edt.putString("language","ar");
                   edt.apply();
                   startActivity(new Intent(Login.this,Login.class));
                   finish();
               }
            }

        });
        textInputEditTextpassword=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        Naccount=findViewById(R.id.textViewLinkRegisterr);
       textViewLinkRegisterrr=findViewById(R.id.textViewLinkRegister);
        Naccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                intent.putExtra("type" , 1);
                startActivity(intent);
            }
        });
        textViewLinkRegisterrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registration.class);
                intent.putExtra("type" , 2);
                startActivity(intent);
            }
        });
        login=(AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputEditTextphone.getText().toString().length()!=10) {

                    textInputLayoutphone.setError("رقم الهاتف يجب ان يتكون من 10 أرقام");
                       return;
                }
                if(textInputEditTextphone.getText().toString().equals("1234567890")&&textInputEditTextpassword.getText().toString().equals("9876543210")){
                    startActivity(new Intent(Login.this,MainAdmin.class));

                }
                else {
                    fetchInfo();
                }
            }
        });

        i=getIntent();
        name.setText(i.getStringExtra("name"));
        if(i.getIntExtra("type",0)==1){
            textViewLinkRegisterrr.setVisibility(View.GONE);
        }
        if(i.getIntExtra("type",0)==2){
            Naccount.setVisibility(View.GONE);
        }
        if(i.getIntExtra("type",0)==3){
            Naccount.setVisibility(View.GONE);
            textViewLinkRegisterrr.setVisibility(View.GONE);
        }
    }
    public void fetchInfo(){
        progressDialog = ProgressDialog.show(Login.this,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();
        String phone=textInputEditTextphone.getText().toString();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_userinfo>> call= apiinterface.getcontacts_login(phone,
                textInputEditTextpassword.getText().toString() , 1);
        call.enqueue(new Callback<List<contact_userinfo>>() {
            @Override
            public void onResponse(Call<List<contact_userinfo>> call, Response<List<contact_userinfo>> response) {
                if(response.isSuccessful()){

                    contactList = response.body();
                    try {
                        progressDialog.dismiss();
                        edt.putInt("id",contactList.get(0).getId());
                        edt.putString("name",contactList.get(0).getName());
                        edt.putString("phone",contactList.get(0).getPhone());
                        edt.putString("address",contactList.get(0).getmaddress());
                        edt.putString("password",contactList.get(0).getPassword());
                        edt.putString("points",contactList.get(0).getPoints());
                        edt.putString("charge",contactList.get(0).getPoints());
                        edt.putString("date", String.valueOf(contactList.get(0).getAge()));
                        edt.putInt("type", contactList.get(0).getType());
                        edt.putFloat("totalprice",0);
                        edt.putString("remember","yes");
                        edt.apply();
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Login.this);
                        dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                        dlgAlert.setTitle("nursery");
                        dlgAlert.setIcon(R.drawable.log);
                        dlgAlert.setPositiveButton("حسنا", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        if(sharedpref.getInt("type",0)==2){
                            startActivity(new Intent(Login.this,MainCnsumer.class));
                        }
                        else{
                        startActivity(new Intent(Login.this,MainActivity.class));}}
                    catch (Exception e){
                        Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري ",Toast.LENGTH_LONG).show();

                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<contact_userinfo>> call, Throwable t) {
                Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري",Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });
    }
}
