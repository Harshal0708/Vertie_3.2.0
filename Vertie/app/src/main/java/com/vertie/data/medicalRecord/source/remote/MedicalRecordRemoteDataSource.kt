package com.vertie.data.medicalRecord.source.remote

import com.vertie.data.medicalRecord.*
import com.vertie.data.medicalRecord.source.MedicalRecordDataSource
import com.vertie.javacode.singleton.SingletonClass
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Retrofit
import javax.inject.Inject

class MedicalRecordRemoteDataSource @Inject constructor(private val api: MedicalRecordApiService) : MedicalRecordDataSource {

    override fun getAllRecords(email: String?): Single<List<MedicalRecord>> {
        return api.get(RequestBody.create(MediaType.parse("application/json"), "{Email:"+SingletonClass.getInstance().email+"}"))
    }

    override fun addRecord(body: MedicalRecordAddRequest): Single<MedicalRecord> {
        return api.addRecord(body)
    }

    override fun getMedicalDateType(body: MedicalRecordGet): Single<MedicalRecordResponce> {
        return api.genMedicalRecords(body)
    }

    override fun getUserDateById(body: String): Single<UserData> {
        return api.getUserById(body)
    }

    override fun getFamilyMemberById(body: String): Single<FamilyMemberData> {
        return api.getFamilyMemberById(body)
    }

}