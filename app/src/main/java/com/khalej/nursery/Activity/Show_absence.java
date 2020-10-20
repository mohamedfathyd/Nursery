package com.khalej.nursery.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalej.nursery.Adapter.RecyclerAdapter_second;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_absence;
import com.khalej.nursery.Model.contact_absence;
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

public class Show_absence extends AppCompatActivity {
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
    List<contact_absence> contactListCategory=new ArrayList<>();
    int mCartItemCount = 10;
int x=1;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_absence);
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
        id=intent.getIntExtra("id",0);
        realm= Realm.getDefaultInstance();
        AddToCard=findViewById(R.id.appCompatButtonLogin);
        Glide.with(this).load(R.drawable.log).error(R.drawable.log).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog settingsDialog = new Dialog(Show_absence.this);
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                settingsDialog.setContentView(R.layout.image_show);
                ImageView imgg = (ImageView) settingsDialog.findViewById(R.id.img);
                Glide.with(Show_absence.this).load(img).error(R.drawable.log).into(imgg);
                settingsDialog.show();
            }
        });


    }

    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_absence>> call = apiinterface.getcontacts_absence(sharedpref.getInt("id",0));
        call.enqueue(new Callback<List<contact_absence>>() {
            @Override
            public void onResponse(Call<List<contact_absence>> call, Response<List<contact_absence>> response) {

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
                        description.setText(contactListCategory.get(0).getNum_days()+"");

                    }
                }
                catch (Exception e){
                    //  Toast.makeText(ChatActivity.this,e+"",Toast.LENGTH_LONG).show();
                    contactListCategory=new ArrayList<>();
                }

            }

            @Override
            public void onFailure(Call<List<contact_absence>> call, Throwable t) {
                contactListCategory=new ArrayList<>();


            }
        });
    }

}
