package com.khalej.nursery.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.khalej.nursery.Adapter.RecyclerAdapter_second;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_home;
import com.khalej.nursery.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Second_category extends AppCompatActivity {
    private apiinterface_home apiinterface;
    RecyclerView recyclerView;
    EditText message;
    ImageView send;
    TextView toolbar_title;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_second recyclerAdapter;
    private List<contact_home> contactList = new ArrayList<>();
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressBar progressBar;
 Intent intent;
 int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        this.setTitle("");
        progressBar=(ProgressBar)findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);

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
        toolbar_title=findViewById(R.id.toolbar_title);
intent=getIntent();
id=intent.getIntExtra("id",0);
        toolbar_title.setText(intent.getStringExtra("name"));
        sharedpref = getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(this, 1);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetchInfo();
    }
    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_home>> call = apiinterface.getcontacts_second(id);
        call.enqueue(new Callback<List<contact_home>>() {
            @Override
            public void onResponse(Call<List<contact_home>> call, Response<List<contact_home>> response) {
                progressBar.setVisibility(View.GONE);

                try {


                    contactList = response.body();
                    if (response.code() == 404) {
                        contactList=new ArrayList<>();
                        return;
                    }
                    if(contactList.isEmpty()){
                        contactList=new ArrayList<>();
                    }
                    else {
                        //  Toast.makeText(ChatActivity.this, "22", Toast.LENGTH_LONG).show();
                        recyclerAdapter = new RecyclerAdapter_second(Second_category.this, contactList);
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerView.scrollToPosition(contactList.size() - 1);
                    }
                }
                catch (Exception e){
                    //  Toast.makeText(ChatActivity.this,e+"",Toast.LENGTH_LONG).show();
                    contactList=new ArrayList<>();
                }

            }

            @Override
            public void onFailure(Call<List<contact_home>> call, Throwable t) {
                contactList=new ArrayList<>();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


            finish();

    }
}

