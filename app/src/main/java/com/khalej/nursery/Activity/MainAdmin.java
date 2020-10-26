package com.khalej.nursery.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainAdmin extends AppCompatActivity {
    Button add,delete,addannonce,deleteannonce,adddouc,deletedouc,addm,deletem,
            orders,noti,offer,offer_img,tager,del_tager,reg_tager,offerdelet,sahb,
    updateNews,updateOffer,updateEvent;
    ImageView logout;
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
        addm=findViewById(R.id.addm);
        deletem=findViewById(R.id.deletem);
        sahb=findViewById(R.id.sahb);
        logout=findViewById(R.id.logout);
        updateEvent=findViewById(R.id.updatetem);
        updateOffer=findViewById(R.id.update);
        updateNews=findViewById(R.id.updateannonce);
        logout=findViewById(R.id.logout);
        updateOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAdmin.this,Update_category.class));
            }
        });
        updateNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAdmin.this,Update_annonce.class));
            }
        });
        updateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAdmin.this,Update_general_category.class));
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAdmin.this,Register_Request.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,AfterLogin.class));
                finish();
            }
        });
        sahb.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(MainAdmin.this, Sahb.class));
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
        addm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,Add_general_category_.class));
            }
        });
        deletem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,Delete_general_category.class));

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
