package com.vertie.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.vertie.Constants
import com.vertie.di.scopes.AppScope
import com.vertie.utils.connectivityUtils.DefaultOnlineChecker
import com.vertie.utils.connectivityUtils.OnlineChecker
import com.vertie.utils.providers.BaseResourceProvider
import com.vertie.utils.providers.ResourceProvider
import dagger.Module
import dagger.Provides

/**
 * Created by sarahussien on 2/21/19.
 */
@Module
class UtilsModule {
    @AppScope
    @Provides
    fun provideConnectivityManager(context: Application): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @AppScope
    @Provides
    fun onlineChecker(cm: ConnectivityManager): OnlineChecker {
        return DefaultOnlineChecker(cm)
    }

    @AppScope
    @Provides
    fun provideResourceProvider(context: Application): BaseResourceProvider {
        return ResourceProvider(context)
    }

    @AppScope
    @Provides
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}