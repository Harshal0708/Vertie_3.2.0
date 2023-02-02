package com.vertie.di

import com.vertie.modules.analyze1.Analyze1Activity
import com.vertie.modules.analyze1.Analyze1Module
import com.vertie.modules.analyze2.Analyze2Activity
import com.vertie.modules.analyze2.Analyze2Module
import com.vertie.modules.login.LoginActivity
import com.vertie.modules.login.LoginModule
import com.vertie.di.scopes.ActivityScope
import com.vertie.modules.analyze3.Analyze3Activity
import com.vertie.modules.analyze3.Analyze3Module
import com.vertie.modules.analyze4.Analyze4Activity
import com.vertie.modules.analyze4.Analyze4Module
import com.vertie.modules.dashboard.DashboardActivity
import com.vertie.modules.dashboard.DashboardModule
import com.vertie.modules.splash.SplashActivity
import com.vertie.modules.splash.SplashModule
import com.vertie.modules.template.TemplateActivity
import com.vertie.modules.template.TemplateModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by sarahussien on 2/12/19.
 */
@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [TemplateModule::class])
    abstract fun contributeTemplateActivity(): TemplateActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun contributeDashboardActivity(): DashboardActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [Analyze1Module::class])
    abstract fun contributeAnalyze1Activity(): Analyze1Activity

    @ActivityScope
    @ContributesAndroidInjector(modules = [Analyze2Module::class])
    abstract fun contributeAnalyze2Activity(): Analyze2Activity

    @ActivityScope
    @ContributesAndroidInjector(modules = [Analyze3Module::class])
    abstract fun contributeAnalyze3Activity(): Analyze3Activity

    @ActivityScope
    @ContributesAndroidInjector(modules = [Analyze4Module::class])
    abstract fun contributeAnalyze4Activity(): Analyze4Activity


}