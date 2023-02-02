package com.vertie.modules.analyze4

import com.vertie.di.scopes.FragmentScope
import com.vertie.modules.analyze3.Analyze3Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [Analyze4Module.Analyze4AbstractModule::class])
class Analyze4Module {
    @Module
    interface Analyze4AbstractModule{
        @FragmentScope
        @ContributesAndroidInjector
        fun analyze4Fragment(): Analyze4Fragment
    }
}