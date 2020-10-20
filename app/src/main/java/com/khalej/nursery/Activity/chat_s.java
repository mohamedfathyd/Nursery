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
import android.widget.Toast;

import com.khalej.nursery.Adapter.RecyclerAdapter_chat;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_chat;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chat_s extends AppCompatActivity {
    private apiinterface_home apiinterface;
    RecyclerView recyclerView;
    EditText message;
    ImageView send;
    TextView toolbar_title;
    EditText textchat;
    ImageView sendd;
Intent i;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_chat recyclerAdapter;
    private List<contact_chat> contactList = new ArrayList<>();
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressBar progressBar;
 Intent intent;
 int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        i=getIntent();
        setSupportActionBar(toolbar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        this.setTitle("");
        progressBar=(ProgressBar)findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        sendd=findViewById(R.id.sendd);
        message=findViewById(R.id.textchat);

        sendd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchInfo_send();
            }
        });
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
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(this, 1);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(
                        1, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);

        fetchInfo();
    }
    public void fetchInfo(){
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setAdapter(null);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(
                        1, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_chat>> call = apiinterface.getcontacts_chat(id);
        call.enqueue(new Callback<List<contact_chat>>() {
            @Override
            public void onResponse(Call<List<contact_chat>> call, Response<List<contact_chat>> response) {
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
                        recyclerAdapter = new RecyclerAdapter_chat(chat_s.this, contactList);
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
            public void onFailure(Call<List<contact_chat>> call, Throwable t) {
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

    public void fetchInfo_send(){

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_addchat(i.getIntExtra("id",0),2,message.getText().toString()+"");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(chat_s.this,"تم الأرسال",Toast.LENGTH_LONG).show();fetchInfo();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}

