package com.khalej.nursery.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.khalej.nursery.Adapter.RecyclerAdapter_first;
import com.khalej.nursery.Adapter.RecyclerAdapter_first_annonce;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_annonce;
import com.khalej.nursery.Model.contact_annonce_realm;
import com.khalej.nursery.Model.contact_home;
import com.khalej.nursery.Model.contact_home_realm;
import com.khalej.nursery.R;

import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.realm.Realm;
import io.realm.RealmResults;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerView2;
    private RecyclerView.LayoutManager layoutManager;
    ImageView absence;
    CountDownTimer countDownTimer;
    private RecyclerAdapter_first recyclerAdapter;
    private RecyclerAdapter_first_annonce recyclerAdapter_annonce;
    private List<contact_home> contactList;
    private List<contact_annonce> contactList_annonce;
    private apiinterface_home apiinterface;
    int x=0;
    int y=0;
    Switch swtch;
    ProgressBar progressBar;
    Realm realm;
    ImageView offer;
    TextView rased,astbian;
    private SharedPreferences sharedpref;
    Typeface myTypeface;
    private SharedPreferences.Editor edt;
    TextView points ,whous,callus,aramix,roaia ,myorder ;
    EditText search_bar;
    ImageView logout;
    ImageView search;
    TextView textCartItemCount;
    int mCartItemCount = 10;
    String lang;

    TextView recharge;
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
        setContentView(R.layout.activity_main);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Nasser.otf", true);

        sharedpref = getSharedPreferences("tarched", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("");
        swtch=findViewById(R.id.swtch);
        roaia=findViewById(R.id.roaia);
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
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });

        absence=findViewById(R.id.absence);
        absence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedpref.getInt("type",0)==2){
                    startActivity(new Intent(MainActivity.this,Sahb.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"جاري تنفيذ جزء الغياب الخاص بالطالب" ,Toast.LENGTH_LONG).show();
                }
            }
        });
        roaia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,Roaia.class));
            }
        });
        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sharedpref.getString("language","").trim().equals("ar")){
                    edt.putString("language","en");
                    edt.apply();
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();
                }
                else
                {
                    edt.putString("language","ar");
                    edt.apply();
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();
                }
            }

        });

        callus=findViewById(R.id.callus);
        whous=findViewById(R.id.whous);
        myorder=findViewById(R.id.myorder);
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Message.class);

                startActivity(intent);
            }
        });

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String url = "https://api.whatsapp.com/send?phone="+"+96899220360";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);}
                catch( Exception e){
                    Toast.makeText(MainActivity.this, "غير متاحه الأن عاود المحاولة لاحقا " ,Toast.LENGTH_LONG).show();
                }
//                String dial = "tel:" + "+966 50 736 9231";
//                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });
        whous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Whous.class));
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new GridLayoutManager(this, 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerview2);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView2.setHasFixedSize(true);


        try {


            final int counter=100*5000;

            countDownTimer=   new CountDownTimer(counter, 5000) {

                public void onTick(long millisUntilFinished) {
                    // Toast.makeText(MainActivity.this , ""+(millisUntilFinished / 1000),Toast.LENGTH_LONG).show();
                    recyclerView2.smoothScrollToPosition(y);
                    y++;
                    if(y>x){
                        y=0;
                    }
                    //here you can have your logic to set text to edittext

                }

                public void onFinish() {

                }

            }.start();}
        catch (Exception e){}

        fetchInfo();
        fetchInfo_annonce();
    }


    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_home>> call = apiinterface.getcontacts_first();
        call.enqueue(new Callback<List<contact_home>>() {
            @Override
            public void onResponse(Call<List<contact_home>> call, Response<List<contact_home>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                WriteTodatabase(contactList);

            }

            @Override
            public void onFailure(Call<List<contact_home>> call, Throwable t) {

            }
        });
    }
    public void WriteTodatabase(List<contact_home> contactList){
//             realm.delete(subject_content_realm.class);
        // Create a new object
        deletedata();
        if(!(contactList==null)){
            for(int i=0;i<contactList.size();i++){
                realm.beginTransaction();
                contact_home_realm images = realm.createObject(contact_home_realm.class);
                images.setId(contactList.get(i).getId());
                images.setname(contactList.get(i).getname());
                images.setImg(contactList.get(i).getImg());
                images.setDescription(contactList.get(i).getDescription());
                images.setPrice(contactList.get(i).getPrice());
                images.setError(contactList.get(i).getError());
                realm.commitTransaction();
            }
            showdata();}

    }
    public void showdata(){

        RealmResults<contact_home_realm> content_realms = realm.where(contact_home_realm.class).findAll();
        if (content_realms.isEmpty() || content_realms.equals(null)) {

        } else {    // realm.beginTransaction();
            List<contact_home_realm> result = content_realms;


            recyclerAdapter=new RecyclerAdapter_first(MainActivity.this,result);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    public void deletedata(){
        realm.beginTransaction();
        RealmResults<contact_home_realm> content_realms=realm.where(contact_home_realm.class).findAll();
        content_realms.deleteAllFromRealm();
        realm.commitTransaction();
    }
    public boolean isNetworkAvailable(Context ctx) {
        if (ctx == null)
            return false;

        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    public void fetchInfo_annonce(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_annonce>> call = apiinterface.getcontacts_annonce();
        call.enqueue(new Callback<List<contact_annonce>>() {
            @Override
            public void onResponse(Call<List<contact_annonce>> call, Response<List<contact_annonce>> response) {
                contactList_annonce = response.body();
                progressBar.setVisibility(View.GONE);
                WriteTodatabase_annonce(contactList_annonce);

            }

            @Override
            public void onFailure(Call<List<contact_annonce>> call, Throwable t) {

            }
        });
    }
    public void WriteTodatabase_annonce(List<contact_annonce> contactList_){
//             realm.delete(subject_content_realm.class);
        // Create a new object
        deletedata_annonce();
        if(!(contactList_==null)){
            for(int i=0;i<contactList_.size();i++){
                realm.beginTransaction();
                contact_annonce_realm images = realm.createObject(contact_annonce_realm.class);

                images.setImage(contactList_.get(i).getImage());
                images.setId(contactList_.get(i).getId());

                realm.commitTransaction();
            }
            showdata_annonce();}
    }
    public void showdata_annonce(){

        RealmResults<contact_annonce_realm> content_realms = realm.where(contact_annonce_realm.class).findAll();
        if (content_realms.isEmpty() || content_realms.equals(null)) {

        } else {    // realm.beginTransaction();
            List<contact_annonce_realm> result2 = content_realms;

            x=result2.size();
            recyclerAdapter_annonce=new RecyclerAdapter_first_annonce(MainActivity.this,result2,recyclerView2);
            recyclerView2.setAdapter(recyclerAdapter_annonce);
        }
    }

    public void deletedata_annonce(){
        realm.beginTransaction();
        RealmResults<contact_annonce_realm> content_realms=realm.where(contact_annonce_realm.class).findAll();
        content_realms.deleteAllFromRealm();
        realm.commitTransaction();
    }






}
