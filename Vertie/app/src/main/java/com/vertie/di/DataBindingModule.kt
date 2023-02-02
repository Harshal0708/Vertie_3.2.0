package com.vertie.di

import android.content.Context
import com.vertie.di.scopes.AppScope
import com.vertie.utils.bindingUtils.BindingAdapters
import dagger.Module
import dagger.Provides

@Module
class DataBindingModule {

    @Provides
    @AppScope
    fun provideBindingAdapters(applicationContext: Context): BindingAdapters {
        return BindingAdapters(applicationContext)
    }
}