package com.khalej.nursery.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import com.khalej.nursery.Model.Apiclient_home;
import com.khalej.nursery.Model.apiinterface_home;
import com.khalej.nursery.R;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    TextInputLayout textInputLayoutname,textInputLayoutaddress,textInputLayoutphone,
            textInputLayoutpassword,textInputLayoutconfirmpassword;
    TextInputEditText textInputEditTextname,textInputEditTextaddress,textInputEditTextphone,textInputEditTextdate,
            textInputEditTextpassword,textInputEditTextconfirmpassword;
    AppCompatButton regesiter;
    Spinner spinner;
    AppCompatTextView openlogin;
    Call<ResponseBody> call = null;
    String code,mVerificationId;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog1;
    Spinner spin;
    login_ login_;
    String codee =null;
    Intent i;
    private apiinterface_home apiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

       i=getIntent();

        inisialize();

        openlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });
        regesiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regesiter.setClickable(false);
               if (textInputEditTextname.getText().toString().equals("") || textInputEditTextname.getText().toString() == null) {

                    textInputLayoutname.setError("أدخل اسم المستخدم");

                } else if (textInputEditTextphone.getText().toString().equals("") || textInputEditTextphone.getText().toString() == null) {

                    textInputLayoutphone.setError("أدخل رقم الموبيل");

                } else if (textInputEditTextpassword.getText().toString().equals("") || textInputEditTextpassword.getText().toString() == null) {

                    textInputLayoutpassword.setError("أدخل كلمة المرور");

                } else if (textInputEditTextconfirmpassword.getText().toString().equals("") || textInputEditTextconfirmpassword.getText().toString() == null) {

                    textInputLayoutconfirmpassword.setError("أدخل  تأكيد كلمة المرور");

                } else if (!textInputEditTextconfirmpassword.getText().toString().equals(textInputEditTextpassword.getText().toString())) {
                    textInputLayoutconfirmpassword.setError("كلمة تأكيد مختلفة");

                    textInputEditTextconfirmpassword.setText("");
                } else {


                     fetchInfo();
                }
            }
        });


    }



    public void fetchInfo() {
        progressDialog = ProgressDialog.show(Registration.this, "جاري انشاء الحساب", "Please wait...", false, false);
        progressDialog.show();
        String phone=textInputEditTextphone.getText().toString();

        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_newaccount(textInputEditTextname.getText().toString(),
                textInputEditTextpassword.getText().toString(),"22"
                ,phone ,1,i.getIntExtra("type",1));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Registration.this);
                dlgAlert.setMessage("تم أنشاء حساب جديد بنجاح ... سيتم مراجعة طلب النضمام والموافقة من قبل الأدمن");
                dlgAlert.setTitle("nursery");
                dlgAlert.setIcon(R.drawable.log);
                dlgAlert.setPositiveButton("حسنا", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
//                login_=new login_();
//                String phone=textInputEditTextphone.getText().toString();
//                login_.fetchInfo(Registration.this,phone,textInputEditTextpassword.getText().toString());
//                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Registration.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inisialize() {
        textInputLayoutname = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutaddress = (TextInputLayout) findViewById(R.id.textInputLayoutaddress);
        textInputLayoutphone = (TextInputLayout) findViewById(R.id.textInputLayoutphone);
        textInputLayoutpassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputLayoutconfirmpassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextname = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextphone = (TextInputEditText) findViewById(R.id.textInputEditTextphone);
        textInputEditTextaddress = (TextInputEditText) findViewById(R.id.textInputEditTextaddress);
        textInputEditTextpassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);


        textInputEditTextconfirmpassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        regesiter = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        openlogin = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }
}