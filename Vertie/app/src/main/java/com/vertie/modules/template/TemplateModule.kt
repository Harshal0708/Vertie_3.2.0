package com.vertie.modules.template

import com.vertie.di.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [TemplateModule.TemplateAbstractModule::class])
class TemplateModule {
    @Module
    interface TemplateAbstractModule {
        @FragmentScope
        @ContributesAndroidInjector
        fun templateFragment(): TemplateFragment
    }
}