package com.vertie.modules.analyze2

import com.vertie.modules.analyze1.Analyze1Fragment
import com.vertie.di.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [Analyze2Module.Analyze2AbstractModule::class])
class Analyze2Module {
    @Module
    interface Analyze2AbstractModule{
        @FragmentScope
        @ContributesAndroidInjector
        fun analyze2Fragment(): Analyze2Fragment
    }
}