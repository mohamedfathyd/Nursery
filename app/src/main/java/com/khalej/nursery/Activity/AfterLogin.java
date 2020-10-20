package com.khalej.nursery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.khalej.nursery.R;

public class AfterLogin extends AppCompatActivity {
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Button Login1,Login2,Login3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_after_login);

        sharedpref = getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        Login1=findViewById(R.id.login1);
        Login2=findViewById(R.id.login2);
        Login3=findViewById(R.id.login3);
        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AfterLogin.this,Login.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });
        Login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AfterLogin.this,Login.class);
                intent.putExtra("type",2);
                startActivity(intent);
            }
        });
        Login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AfterLogin.this,Login.class);
                intent.putExtra("type",3);
                startActivity(intent);
            }
        });
        Login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AfterLogin.this,Login.class);
                intent.putExtra("type",3);
                startActivity(intent);
            }
        });

        if(sharedpref.getString("remember","").trim().equals("yes")){
            edt.putFloat("totalprice",0);
            edt.apply();
            startActivity(new Intent(AfterLogin.this,MainActivity.class));
            finish();
        }
    }
}
