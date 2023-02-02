package com.vertie.data.user.source


import com.vertie.data.user.source.local.UserLocalDataSource
import com.vertie.data.user.source.remote.UserRemoteDataSource
import com.vertie.di.qualifiers.Local
import com.vertie.di.qualifiers.Remote
import com.vertie.di.qualifiers.Repo
import com.vertie.di.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class UserRepositoryModule {

    @Binds
    @Local
    @AppScope
    internal abstract fun providesLocalDataSource(dataSource: UserLocalDataSource) : UserDataSource

    @Binds
    @Repo
    @AppScope
    internal abstract fun providesUserRepoDataSource(dataSource: UserRepository): UserDataSource

    @Binds
    @Remote
    @AppScope
    internal abstract fun providesUserRemoteDataSource(dataSource: UserRemoteDataSource): UserDataSource

}