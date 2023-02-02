package com.vertie.data.medicalRecord.source.remote

import com.vertie.Constants
import com.vertie.data.medicalRecord.*
import com.vertie.javacode.utility.Constant
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MedicalRecordApiService {

    @HTTP(method = "get", path = Constants.GET_ALL_RECORDS, hasBody = true)
    fun get(@Body body: RequestBody): Single<List<MedicalRecord>>

    @POST(Constants.ADD_RECORD)
    fun addRecord(@Body body : MedicalRecordAddRequest) : Single<MedicalRecord>

    @POST(Constants.GET_Medical_Records_By_Date_Type)
    fun genMedicalRecords(@Body body : MedicalRecordGet) : Single<MedicalRecordResponce>

    @GET(Constants.API_GetUserById + "{id}")
    fun getUserById(@Path("id") id: String?): Single<UserData>

    @GET(Constants.API_GetFamilyMemberById + "{id}")
    fun getFamilyMemberById(@Path("id") id: String?): Single<FamilyMemberData>


}