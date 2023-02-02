package com.vertie.modules.dashboard

import com.vertie.modules.dashboard.historyfragment.HistoryFragment
import com.vertie.modules.dashboard.homefragment.HomeFragment
import com.vertie.modules.dashboard.profilefragment.ProfileFragment
import com.vertie.di.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DashboardModule.DashboardAbstractModule::class])
class DashboardModule {
    @Module
    interface DashboardAbstractModule {
        @FragmentScope
        @ContributesAndroidInjector
        fun homeFragment(): HomeFragment

        @FragmentScope
        @ContributesAndroidInjector
        fun historyFragment(): HistoryFragment

        @FragmentScope
        @ContributesAndroidInjector
        fun profileFragment(): ProfileFragment
    }
}