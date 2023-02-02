package com.vertie.data.medicalRecord.source

import com.vertie.data.medicalRecord.*
import com.vertie.data.user.source.UserDataSource
import com.vertie.di.qualifiers.Local
import com.vertie.di.qualifiers.Remote
import com.vertie.di.scopes.AppScope
import com.vertie.utils.providers.BaseResourceProvider
import io.reactivex.Single
import javax.inject.Inject

@AppScope
class MedicalRecordRepository @Inject constructor(@Remote val remote: MedicalRecordDataSource, private val resourceProvider: BaseResourceProvider) : MedicalRecordDataSource {
    override fun getAllRecords(email: String?): Single<List<MedicalRecord>> {
        return remote.getAllRecords(email)
    }

    override fun addRecord(body: MedicalRecordAddRequest): Single<MedicalRecord> {
        return remote.addRecord(body)
    }

    override fun getMedicalDateType(body: MedicalRecordGet): Single<MedicalRecordResponce> {
        return remote.getMedicalDateType(body)
    }

    override fun getUserDateById(body: String): Single<UserData> {
        return remote.getUserDateById(body)
    }

    override fun getFamilyMemberById(body: String): Single<FamilyMemberData> {
        return remote.getFamilyMemberById(body)
    }

}