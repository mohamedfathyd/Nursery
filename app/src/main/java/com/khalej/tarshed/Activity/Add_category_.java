package com.khalej.tarshed.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.khalej.tarshed.Model.Apiclient_home;
import com.khalej.tarshed.Model.apiinterface_home;
import com.khalej.tarshed.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_category_ extends AppCompatActivity {
    Intent intent;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> arrayList_id=new ArrayList<>();
    apiinterface_home apiinterface;
    AppCompatButton regesiter;
    private  static final int IMAGE = 100;
    ImageView imageView;
    Toolbar toolbar;
    int category_id;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    Spinner spinner;
    TextInputLayout textInputLayoutdetails,textInputLayoutname,textInputLayoutaddress,textInputLayoutphone;
    TextInputEditText textInputEditTextdetails,textInputEditTextname,textInputEditTextaddress,textInputEditTextphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_);
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
                regesiter.setClickable(false);
            }
        });
    }
    public void initializer(){

        imageView=(ImageView)findViewById(R.id.image);

        textInputLayoutname=(TextInputLayout)findViewById(R.id.textInputLayoutName);

        textInputEditTextname=(TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextaddress=findViewById(R.id.textInputEditTextdetails);
        textInputEditTextphone=findViewById(R.id.textInputEditTextprice);

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
    public void fetchInfo(){

        String image="";
        try{
         image = convertToString();}
        catch (Exception e){
            Toast.makeText(Add_category_.this,"من فضلك اختر صورة" , Toast.LENGTH_LONG).show();
            return;

        }
        progressDialog = ProgressDialog.show(Add_category_.this,"جارى تسجيل الجهاز الجديد","Please wait...",false,false);
        progressDialog.show();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_add_first_category(textInputEditTextname.getText().toString()
                ,image,textInputEditTextphone.getText().toString(),textInputEditTextaddress.getText().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Add_category_.this);
                dlgAlert.setMessage("تم تسجيل الجهاز الجديد بنجاح ");
                dlgAlert.setTitle("ترشيد");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                regesiter.setClickable(true);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Add_category_.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
