package com.khalej.nursery.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalej.nursery.R;


import io.realm.Realm;
import me.anwarshahriar.calligrapher.Calligrapher;

public class Show extends AppCompatActivity {
Intent intent;
ImageView imageView,car;
String img;
TextView name,price,add,remove,num,description ,error;
    AppCompatButton AddToCard;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    Toolbar toolbar;
    int id;
    TextView textCartItemCount;
    int mCartItemCount = 10;
int x=1;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);


        name=findViewById(R.id.name);
        imageView=findViewById(R.id.img);
        error=findViewById(R.id.error);
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
        price=findViewById(R.id.price);
        num=findViewById(R.id.num);
        add=findViewById(R.id.add);

        remove=findViewById(R.id.remove);
        description=findViewById(R.id.description);
        intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        img=intent.getStringExtra("image");
        price.setText(intent.getStringExtra("price"));
        error.setText(intent.getStringExtra("error"));
        id=intent.getIntExtra("id",0);
        description.setText(intent.getStringExtra("description"));
        realm= Realm.getDefaultInstance();
        AddToCard=findViewById(R.id.appCompatButtonLogin);
        Glide.with(this).load(img).error(R.drawable.log).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(Show.this);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(R.layout.image_show);
                ImageView imgg = (ImageView) settingsDialog.findViewById(R.id.img);
                Glide.with(Show.this).load(img).error(R.drawable.log).into(imgg);
                settingsDialog.show();
            }
        });


    }

}
