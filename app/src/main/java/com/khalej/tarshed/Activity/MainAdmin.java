package com.khalej.tarshed.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.khalej.tarshed.Model.apiinterface_home;
import com.khalej.tarshed.R;

public class MainAdmin extends AppCompatActivity {
    Button add,delete,addannonce,deleteannonce,adddouc,deletedouc,
            orders,noti,offer,offer_img,tager,del_tager,reg_tager,offerdelet,sahb;
    apiinterface_home apiinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        add=findViewById(R.id.add);
        delete=findViewById(R.id.delete);
        addannonce=findViewById(R.id.addannonce);
        orders=findViewById(R.id.orders);
        deleteannonce=findViewById(R.id.deleteannonce);
        sahb=findViewById(R.id.sahb);
        sahb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(MainAdmin.this, Sahb.class));
                                    }
                                });
                orders.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainAdmin.this, Orders.class));

                    }
                });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,Add_category_.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,Delete_category.class));

            }
        });

        addannonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,Add_annonce.class));
            }
        });
        deleteannonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,Delete_annonce.class));
            }
        });
    }
}
