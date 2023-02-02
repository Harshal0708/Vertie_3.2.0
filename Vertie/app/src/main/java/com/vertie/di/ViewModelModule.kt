package com.vertie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vertie.modules.login.LoginViewModel
import com.vertie.di.scopes.AppScope
import com.vertie.modules.analyze1.Analyze1ViewModel
import com.vertie.modules.analyze2.Analyze2ViewModel
import com.vertie.modules.analyze3.Analyze3ViewModel
import com.vertie.modules.analyze4.Analyze4ViewModel
import com.vertie.modules.dashboard.historyfragment.HistoryViewModel
import com.vertie.modules.dashboard.homefragment.HomeViewModel
import com.vertie.modules.dashboard.profilefragment.ProfileViewModel
import com.vertie.modules.splash.SplashViewModel
import com.vertie.modules.template.TemplateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by sarahussien on 2/18/19.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TemplateViewModel::class)
    abstract fun bindTemplateListViewModel(templateViewModel: TemplateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @AppScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Analyze1ViewModel::class)
    abstract fun bindAnalyze1ViewModel(analyze1ViewModel: Analyze1ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Analyze2ViewModel::class)
    abstract fun bindAnalyze2ViewModel(analyze2ViewModel: Analyze2ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Analyze3ViewModel::class)
    abstract fun bindAnalyze3ViewModel(analyze3ViewModel: Analyze3ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Analyze4ViewModel::class)
    abstract fun bindAnalyze4ViewModel(analyze4ViewModel: Analyze4ViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

}