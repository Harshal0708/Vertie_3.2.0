package com.vertie.modules.analyze3

import com.vertie.di.scopes.FragmentScope
import com.vertie.modules.analyze1.Analyze1Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [Analyze3Module.Analyze3AbstractModule::class])
class Analyze3Module {
    @Module
    interface Analyze3AbstractModule{
        @FragmentScope
        @ContributesAndroidInjector
        fun analyze3Fragment(): Analyze3Fragment
    }
}