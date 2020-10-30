package com.khalej.nursery.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


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
spin=findViewById(R.id.spinCountry);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category_id=contactListCategory.get(i).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

            }
        });
        dateSelect=findViewById(R.id.dateSelect);
        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Add_category_.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String month="";
                                if((monthOfYear+1)<10){
                                    month="0"+(monthOfYear+1);
                                }
                                else{
                                    month= String.valueOf((monthOfYear+1));
                                }
                                String day="";
                                if((dayOfMonth+1)<10){
                                    day="0"+(dayOfMonth);
                                }
                                else{
                                    day= String.valueOf((dayOfMonth));
                                }
                                dateSelect.setText(year + "-" + month+ "-" + day);
                                date=year + "-" + month+ "-" + day;
                            }
                        }, day, month, year);
                picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                picker.show();

            }
        });
        timeSelect=findViewById(R.id.timeSelect);
        timeSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Add_category_.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String hour="";
                        if(selectedHour<10){
                            hour="0"+(selectedHour);
                        }
                        else{
                            hour= String.valueOf((selectedHour));
                        }
                        String minute="";
                        if(selectedMinute<10){
                            minute="0"+(selectedMinute);
                        }
                        else{
                            minute= String.valueOf((selectedMinute));
                        }
                        timeSelect.setText( hour + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        fetchgetCategory();
    }
    public void initializer(){

        imageView=(ImageView)findViewById(R.id.image);

        textInputLayoutname=(TextInputLayout)findViewById(R.id.textInputLayoutName);

        textInputEditTextname=(TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextaddress=findViewById(R.id.textInputEditTextdetails);
        textInputEditTextphone=findViewById(R.id.textInputEditTextprice);
        textInputEditTexterror=findViewById(R.id.textInputEditTexterror);
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
            Toast.makeText(Add_category_.this,"Please Select Image" , Toast.LENGTH_LONG).show();
            return;

        }
        progressDialog = ProgressDialog.show(Add_category_.this,"Loading Add new offer","Please wait...",false,false);
        progressDialog.show();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_add_first_category(textInputEditTextname.getText().toString()
                ,image,textInputEditTextphone.getText().toString()+"",textInputEditTextaddress.getText().toString()
                ,textInputEditTexterror.getText().toString(), category_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();

                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Add_category_.this);
                dlgAlert.setMessage("Offer Added Succesfully ");
                dlgAlert.setIcon(R.drawable.log);
                dlgAlert.setTitle("nursery");
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

    public void fetchgetCategory(){
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_home>> call = apiinterface.getcontacts_first();
        call.enqueue(new Callback<List<contact_home>>() {
            @Override
            public void onResponse(Call<List<contact_home>> call, Response<List<contact_home>> response) {
                contactListCategory=response.body();
                try {
                    if(contactListCategory.size()!=0){
                        ArrayList<String> arrayList = new ArrayList<>();
                        for (int i = 0; i < contactListCategory.size(); i++) {

                            arrayList.add(contactListCategory.get(i).getname());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                Add_category_.this,
                                android.R.layout.simple_spinner_item,
                                arrayList
                        );
                        spin.setAdapter(adapter);


                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<contact_home>> call, Throwable t) {
                //  Toast.makeText(CallUs.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }



}
