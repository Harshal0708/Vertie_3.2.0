package com.vertie.di

import android.content.Context
import androidx.annotation.NonNull
import com.vertie.utils.networkUtils.RetrofitInterceptor
import com.vertie.data.user.source.remote.UserApiService
import com.vertie.BuildConfig
import com.vertie.Constants
import com.vertie.di.scopes.AppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vertie.data.mood.source.remote.MoodApiService
import com.vertie.data.user.Session
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by sarahussien on 2/12/19.
 */

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideOkHttpInterceptors(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @AppScope
    @Provides
    fun provideRetrofitInterceptor(context: Context,session:Session): RetrofitInterceptor {
        return RetrofitInterceptor(context,session)
    }


    @AppScope
    @Provides
    fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        retrofitInterceptor: RetrofitInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(retrofitInterceptor)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @AppScope
    @Provides
    fun provideGsonFactory(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
    }

    @AppScope
    @Provides
    fun provideRetrofitClient(@NonNull okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Serialize Objects
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }


    @AppScope
    @Provides
    fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApiService::class.java)

    @AppScope
    @Provides
    fun provideMoodApi(retrofit: Retrofit) = retrofit.create(MoodApiService::class.java)

    @AppScope
    @Provides
    fun provideMedicalRecordApi(retrofit: Retrofit) = retrofit.create(com.vertie.data.medicalRecord.source.remote.MedicalRecordApiService::class.java)

}