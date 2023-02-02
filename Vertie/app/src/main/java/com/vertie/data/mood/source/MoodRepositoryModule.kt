package com.vertie.data.mood.source

import com.vertie.data.mood.source.local.MoodLocalDataSource
import com.vertie.data.mood.source.remote.MoodRemoteDataSource
import com.vertie.data.user.source.UserDataSource
import com.vertie.data.user.source.UserRepository
import com.vertie.data.user.source.local.UserLocalDataSource
import com.vertie.data.user.source.remote.UserRemoteDataSource
import com.vertie.di.qualifiers.Local
import com.vertie.di.qualifiers.Remote
import com.vertie.di.qualifiers.Repo
import com.vertie.di.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class MoodRepositoryModule {

    @Binds
    @Local
    @AppScope
    internal abstract fun providesMoodLocalDataSource(dataSource: MoodLocalDataSource) : MoodDataSource

    @Binds
    @Repo
    @AppScope
    internal abstract fun providesMoodRepoDataSource(dataSource: MoodRepository): MoodDataSource

    @Binds
    @Remote
    @AppScope
    internal abstract fun providesMoodRemoteDataSource(dataSource: MoodRemoteDataSource): MoodDataSource

}