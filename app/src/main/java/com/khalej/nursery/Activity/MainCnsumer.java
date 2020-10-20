package com.khalej.nursery.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.khalej.nursery.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainCnsumer extends AppCompatActivity {
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Button Login1,Login2,Login3,Login4;
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consumer);

        sharedpref = getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        Login1=findViewById(R.id.login1);
        Login2=findViewById(R.id.login2);
        Login3=findViewById(R.id.login3);
        Login4=findViewById(R.id.login4);
        Login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainCnsumer.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainCnsumer.this,Sahb.class);
                startActivity(intent);
            }
        });
        Login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainCnsumer.this,Sahb_b.class);
                startActivity(intent);
            }
        });

        Login4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainCnsumer.this,Sahb_bb.class);
                startActivity(intent);
            }
        });
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("id",0);
                edt.putFloat("totalprice",0);
                edt.putString("name","");
                edt.putString("phone","");
                edt.putString("address","");
                edt.putString("password","");
                edt.putString("points","");
                edt.putString("country","");
                edt.putString("date", "");
                edt.putString("remember","");
                edt.putInt("type",0);
                edt.apply();
                startActivity(new Intent(MainCnsumer.this,AfterLogin.class));
                finish();
            }
        });
    }
}
