package com.vertie.javacode.apiManager;


import android.content.Context;

import com.vertie.Constants;
import com.vertie.javacode.utility.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class APIManager {


    public static JsonPlaceHolderApi getUserManagerService(Context c,Converter.Factory converterFactory)
    {
        String token = c.getSharedPreferences("user", 0).getString("token",null);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        if(token!=""){
            retrofitBuilder.client(client);
        }
        retrofitBuilder.baseUrl(Constants.API_BASEURL);
        if(converterFactory != null ) {
            retrofitBuilder.addConverterFactory(converterFactory);
        }
        Retrofit retrofit = retrofitBuilder.build();
        JsonPlaceHolderApi userManagerService = retrofit.create(JsonPlaceHolderApi.class);
        return userManagerService;
    }

}
