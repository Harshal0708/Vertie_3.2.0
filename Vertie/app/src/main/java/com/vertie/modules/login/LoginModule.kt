package com.vertie.modules.login

import com.vertie.di.scopes.FragmentScope
import com.vertie.modules.template.TemplateModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [LoginModule.LoginAbstractModule::class])
class LoginModule {
    @Module
    interface LoginAbstractModule{
        @FragmentScope
        @ContributesAndroidInjector
        fun loginFragment() : LoginFragment
    }
}