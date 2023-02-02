package com.vertie.data.medicalRecord.source

import com.vertie.data.medicalRecord.*
import io.reactivex.Single

interface MedicalRecordDataSource {
    fun getAllRecords(email : String?) : Single<List<MedicalRecord>>
    fun addRecord(body : MedicalRecordAddRequest) : Single<MedicalRecord>
    fun getMedicalDateType(body : MedicalRecordGet) : Single<MedicalRecordResponce>
    fun getUserDateById(body : String) : Single<UserData>
    fun getFamilyMemberById(body : String) : Single<FamilyMemberData>
}