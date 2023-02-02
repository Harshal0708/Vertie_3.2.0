package com.vertie.javacode.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.vertie.Constants;
import com.vertie.R;
import com.vertie.data.base.BaseEmptyResponse;
import com.vertie.data.user.LoginRequest;
import com.vertie.data.user.LoginResponse;
import com.vertie.data.user.Userr;
import com.vertie.data.user.source.UserDataSource;
import com.vertie.javacode.adapters.ProxyUserAdapter;
import com.vertie.javacode.apiManager.APIManager;
import com.vertie.javacode.models.ProxyUserModel;
import com.vertie.javacode.models.UserData;
import com.vertie.javacode.singleton.SingletonClass;
import com.vertie.javacode.utility.Globals;
import com.vertie.modules.dashboard.DashboardActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import java.lang.reflect.Type;

public class ChildSelectionActivity extends AppCompatActivity implements ProxyUserAdapter.OnClickListner,UserDataSource{

    GridView coursesGV;
    TextView tvNoProxyUser;
    UserDataSource userRepository;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_selection);

        coursesGV = findViewById(R.id.idGVcourses);
        tvNoProxyUser = findViewById(R.id.tvNoProxyUser);

        String userList = getApplicationContext().getSharedPreferences(Constants.userList, 0).getString(Constants.proxyUserList,null);
        List<Userr> arrU = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Userr>>(){}.getType();
        if(userList.equals("null")){
            tvNoProxyUser.setVisibility(View.VISIBLE);
        }else {
            tvNoProxyUser.setVisibility(View.GONE);
        List<Userr> contactList = gson.fromJson(userList, type);
        for (Userr contact : contactList){
            arrU.add(contact);
        }

        ArrayList<ProxyUserModel> proxyUserModelArrayList = new ArrayList<>();
        for (Userr item : arrU)
        {
            proxyUserModelArrayList.add(new ProxyUserModel(item.getId(),item.getFirstName()+item.getLastName(), R.drawable.user_icon,item.getId(),item.getStreetAddress1()));
        }
        ProxyUserAdapter adapter = new ProxyUserAdapter(this, proxyUserModelArrayList);
        coursesGV.setAdapter(adapter);
        adapter.setOnClickListner(this);
        }
    }


    @Override
    public void onClickEvent(View view, int position, ProxyUserModel item) {
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        if(position == 0){
            editor.putBoolean("isFamilyMembar", false);
            editor.putString("user_id", item.getUserId());
            SingletonClass.getInstance().userId = item.getUserId();
        }else {
            editor.putString("user_email", "");
            editor.putString("user_id", item.getId());
            SingletonClass.getInstance().userId = item.getId();
            SingletonClass.getInstance().email="";
            editor.putBoolean("isFamilyMembar", true);
        }
        editor.commit();
        startActivity(new Intent(this,DashboardActivity.class));
    finish();
    }

    @NonNull
    @Override
    public Single<LoginResponse> userLogin(@NonNull LoginRequest loginRequest) {
        return null;
    }

    @NonNull
    @Override
    public Single<BaseEmptyResponse> setUserPrefs(@NonNull Userr user, @NonNull String token, @NonNull String id) {
        return userRepository.setUserPrefs(user,token,id);
    }

    @NonNull
    @Override
    public Completable initSession() {
        return null;
    }

    @NonNull
    @Override
    public Completable clearPrefs() {
        return null;
    }
}