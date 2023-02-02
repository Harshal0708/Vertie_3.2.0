package com.vertie.di

import android.app.Application
import android.content.Context
import com.vertie.di.scopes.AppScope
import dagger.Binds
import dagger.Module

/**
 * Created by sarahussien on 2/12/19.
 */
@Module(includes = [NetworkModule::class])
abstract class AppModule {
    @AppScope
    @Binds
    abstract fun bindContext(application: Application): Context
}