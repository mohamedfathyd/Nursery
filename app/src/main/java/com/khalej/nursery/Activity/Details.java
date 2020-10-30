package com.khalej.nursery.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.Model.contact_home;
import com.khalej.nursery.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    Intent intent;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> arrayList_id=new ArrayList<>();
    apiinterface_home apiinterface;
    AppCompatButton regesiter;
    private  static final int IMAGE = 100;
    ImageView imageView;
    Toolbar toolbar;
    int category_id;
    List<contact_home> contactListCategory=new ArrayList<>();
    Bitmap bitmap;
    ProgressDialog progressDialog;
    int countryId;
    DatePickerDialog picker;
    TextView timeSelect,dateSelect;
    String date="";
    Spinner spin;
    TextInputLayout textInputLayoutdetails,textInputLayoutname,textInputLayoutaddress,textInputLayoutphone;
    TextInputEditText textInputEditTextdetails,textInputEditTexterror,textInputEditTextname,textInputEditTextaddress,textInputEditTextphone;
  TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        initializer();
        this.setTitle("");
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
        intent=getIntent();
       details.setText(intent.getStringExtra("details"));
        Glide.with(this).load(intent.getStringExtra("image")).error(R.drawable.log).into(imageView);

        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getIntExtra("type",0)==1){
                    new AlertDialog.Builder(Details.this)
                            .setTitle("nursery")
                            .setMessage("Are you sure want to delete ?")
                            .setIcon(R.drawable.log)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    fetchInfo(intent.getIntExtra("id",0));
                                }})
                            .setNegativeButton(android.R.string.no, null).show();


               }
                else if(intent.getIntExtra("type",0)==2){
                    new AlertDialog.Builder(Details.this)
                            .setTitle("nursery")
                            .setMessage("Are you sure want to delete ?")
                            .setIcon(R.drawable.log)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    fetchInfoOffer(intent.getIntExtra("id",0));
                                }})
                            .setNegativeButton(android.R.string.no, null).show();

                }
                else{
                    new AlertDialog.Builder(Details.this)
                            .setTitle("nursery")
                            .setMessage("Are you sure want to delete ?")
                            .setIcon(R.drawable.log)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    fetchInfoNews(intent.getIntExtra("id",0));
                                }})
                            .setNegativeButton(android.R.string.no, null).show();

                }

            }
        });

    }
    public void initializer(){

        imageView=(ImageView)findViewById(R.id.image);

        details=findViewById(R.id.details);
        regesiter=(AppCompatButton)findViewById(R.id.appCompatButtonRegister);
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }
    private String convertToString()
    {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void fetchInfo(int id) {

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.delete_first(id);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(Details.this,"Deleted Done",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Details.this,MainAdmin.class));

                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }
    public void fetchInfoOffer(int id) {

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.delete_general(id);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(Details.this,"Deleted Done",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Details.this,MainAdmin.class));

                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }

    public void fetchInfoNews(int id) {

        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = null;
        call=apiinterface.delete_annonce(id);



        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(Details.this,"Deleted Done",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Details.this,MainAdmin.class));

                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }

}
