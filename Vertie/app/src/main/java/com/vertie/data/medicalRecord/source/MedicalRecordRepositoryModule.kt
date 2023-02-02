package com.vertie.data.medicalRecord.source

import com.vertie.data.medicalRecord.source.remote.MedicalRecordRemoteDataSource
import com.vertie.di.qualifiers.Remote
import com.vertie.di.qualifiers.Repo
import com.vertie.di.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class MedicalRecordRepositoryModule {

    @Binds
    @Repo
    @AppScope
    internal abstract fun providesMedicalRecordRepoDataSource(dataSource: MedicalRecordRepository): MedicalRecordDataSource

    @Binds
    @Remote
    @AppScope
    internal abstract fun providesMedicalRecordRemoteDataSource(dataSource: MedicalRecordRemoteDataSource): MedicalRecordDataSource


}