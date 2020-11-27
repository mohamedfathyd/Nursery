package com.khalej.nursery.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_report;
import com.khalej.nursery.Model.contact_report;
import com.khalej.nursery.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import io.realm.Realm;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Show_report extends AppCompatActivity {
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
    apiinterface_home apiinterface;
    List<contact_report> contactListCategory=new ArrayList<>();
    int mCartItemCount = 10;
int x=1;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

        sharedpref = getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
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
        id=intent.getIntExtra("id",0);
        realm= Realm.getDefaultInstance();
        AddToCard=findViewById(R.id.appCompatButtonLogin);
        Glide.with(this).load(R.drawable.log).error(R.drawable.log).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(Show_report.this);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(R.layout.image_show);
                ImageView imgg = (ImageView) settingsDialog.findViewById(R.id.img);
                Glide.with(Show_report.this).load(img).error(R.drawable.log).into(imgg);
                settingsDialog.show();
            }
        });
fetchInfo();

    }

    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_report>> call = apiinterface.getcontacts_report(sharedpref.getInt("id",0));
        call.enqueue(new Callback<List<contact_report>>() {
            @Override
            public void onResponse(Call<List<contact_report>> call, Response<List<contact_report>> response) {

                try {


                    contactListCategory = response.body();
                    if (response.code() == 404) {
                        contactListCategory=new ArrayList<>();
                        return;
                    }
                    if(contactListCategory.isEmpty()){
                        contactListCategory=new ArrayList<>();
                    }
                    else {
                        //  Toast.makeText(ChatActivity.this, "22", Toast.LENGTH_LONG).show();

                        name.setText(sharedpref.getString("name",""));
                        error.setText(contactListCategory.get(0).getDetails());
                        price.setText(contactListCategory.get(0).getMonth()+"");

                    }
                }
                catch (Exception e){
                    //  Toast.makeText(ChatActivity.this,e+"",Toast.LENGTH_LONG).show();
                    contactListCategory=new ArrayList<>();
                }

            }

            @Override
            public void onFailure(Call<List<contact_report>> call, Throwable t) {
                contactListCategory=new ArrayList<>();


            }
        });
    }

}
