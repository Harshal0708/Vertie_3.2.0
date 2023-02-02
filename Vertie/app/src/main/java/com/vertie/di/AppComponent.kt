package com.vertie.di

import android.app.Application
import androidx.databinding.DataBindingComponent
import com.vertie.data.user.source.UserRepositoryModule
import com.vertie.di.RoomModule
import com.vertie.CustomApplication
import com.vertie.data.medicalRecord.source.MedicalRecordRepositoryModule
import com.vertie.data.mood.source.MoodRepositoryModule
import com.vertie.di.scopes.AppScope
import com.vertie.utils.bindingUtils.BindingAdapters
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


/**
 * Created by sarahussien on 2/12/19.
 */
@AppScope
@Component(modules = [
    DataBindingModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class, ActivitiesModule::class,
    ViewModelModule::class, UtilsModule::class,
    UserRepositoryModule::class, NetworkModule::class,
    RoomModule::class, MoodRepositoryModule::class, MedicalRecordRepositoryModule::class])
interface AppComponent : AndroidInjector<CustomApplication>, DataBindingComponent {

    // we can now do DaggerAppComponent.builder().application(this).build().inject(this),
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph

    override fun getBindingAdapters(): BindingAdapters

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}