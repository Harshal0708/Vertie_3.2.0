package com.vertie.javacode.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.vertie.R;
import com.vertie.javacode.apiManager.APIManager;
import com.vertie.javacode.utility.Debugger;
import com.vertie.javacode.utility.Globals;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    EditText etEmail1;
    String email;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgot_password);

            findViewById(R.id.goBack).setOnClickListener(view->{
                finish();
            });

            etEmail1 =(EditText) findViewById(R.id.etEmail);

            findViewById(R.id.buttonSendMail).setOnClickListener(view->{
                email = etEmail1.getText().toString().trim();
                Log.d("EMAIL ::: ",email);
                if(email.isEmpty()){
                    Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
                }else {
                    forgotpasswordApiCall(email);
                }
            });
        }

        void forgotpasswordApiCall(String email){
            HashMap<String, String> mapFields = new HashMap<>();
            mapFields.put("email", email);
            Call<Object> call
                    = APIManager
                    .getUserManagerService(this,GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .forgotpassword(mapFields);
            Globals.showProgressDialog(this);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    LinkedTreeMap<String,String> body = (LinkedTreeMap<String, String>) response.body();
                    Debugger.debugD(TAG, response.toString());
                    Globals.hideProgressDialog();
                    if (!response.isSuccessful()) {
                        Debugger.debugE(TAG, "API status code : " + response.code());
                        return;
                    }
                        Toast.makeText(ForgotPassword.this, body.get("resultMessage"), Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Globals.hideProgressDialog();
                    Debugger.debugE(TAG, "API Failed: " + t.getLocalizedMessage());
                }
            });
        }


        void loginAPI(){

            HashMap<String, String> mapFields = new HashMap<>();
            mapFields.put("EmailorVIN", "sachin.prajapati0533@gmail.com");
            mapFields.put("Password", "P@ssw0rd");
            Call<Object> call
                    = APIManager
                    .getUserManagerService(this,GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .login(mapFields);
            Globals.showProgressDialog(this);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    LinkedTreeMap<String,String> body = (LinkedTreeMap<String, String>) response.body();
                    Debugger.debugD(TAG, response.toString());
                    Globals.hideProgressDialog();

//                SharedPreferences.Editor editor = getSharedPreferences(Constant.MM_SHARED_USER, 0).edit();
//                editor.putString(Constant.MM_SHARED_IS_PROFILE_SET, user.is_profile_set);
//                editor.commit();

//                    if (!response.isSuccessful()) {
//                        Debugger.debugE(TAG, "API status code : " + response.code());
//                        return;
//                    }
//                    Toast.makeText(ForgotPassword.this, body.get("resultMessage"), Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Globals.hideProgressDialog();
                    Debugger.debugE(TAG, "API Failed: " + t.getLocalizedMessage());
                }
            });
        }
}
