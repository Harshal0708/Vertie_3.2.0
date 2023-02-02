package com.vertie

import android.content.Context
import android.content.res.Configuration
import androidx.databinding.DataBindingUtil
import com.vertie.Constants
import com.vertie.di.DaggerAppComponent
import com.vertie.utils.LocaleHelper
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by sarahussien on 2/12/19.
 */

/**
 * We create a custom Application class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @AppScoped Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
class CustomApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val builder = DaggerAppComponent.builder().application(this).build()
        DataBindingUtil.setDefaultComponent(builder)
        return builder
    }

}