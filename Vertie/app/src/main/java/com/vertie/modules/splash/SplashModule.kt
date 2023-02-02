package com.vertie.modules.splash

import com.vertie.di.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [SplashModule.SplashAbstractModule::class])
class SplashModule {
    @Module
    interface SplashAbstractModule {
        @FragmentScope
        @ContributesAndroidInjector
        fun splashFragment(): SplashFragment
    }
}