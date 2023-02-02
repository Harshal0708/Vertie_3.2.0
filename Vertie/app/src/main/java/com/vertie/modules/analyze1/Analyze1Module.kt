package com.vertie.modules.analyze1

import com.vertie.di.scopes.FragmentScope
import com.vertie.modules.template.TemplateFragment
import com.vertie.modules.template.TemplateModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [Analyze1Module.Analyze1AbstractModule::class])
class Analyze1Module {
    @Module
    interface Analyze1AbstractModule {
        @FragmentScope
        @ContributesAndroidInjector
        fun analyze1Fragment(): Analyze1Fragment
    }
}