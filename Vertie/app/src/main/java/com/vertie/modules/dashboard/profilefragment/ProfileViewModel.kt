package com.vertie.modules.dashboard.profilefragment

import android.annotation.SuppressLint
import android.se.omapi.Session
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vertie.data.medicalRecord.FamilyMemberData
import com.vertie.data.medicalRecord.MedicalRecord
import com.vertie.data.medicalRecord.MedicalRecordGet
import com.vertie.data.medicalRecord.UserData
import com.vertie.data.medicalRecord.source.MedicalRecordDataSource
import com.vertie.di.qualifiers.Repo
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.modules.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDateTime
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    @Repo private val medicalRecordRepository: MedicalRecordDataSource,
//    private val session: Session
) :  BaseViewModel() {


    val medicalRecordData : MutableLiveData<UserData> = MutableLiveData()

    val medicalRecordData2 : MutableLiveData<FamilyMemberData> = MutableLiveData()

//    val userDataAPIcall : MutableLiveData<Boolean> = MutableLiveData()
//    @SuppressLint("SuspiciousIndentation")
//    fun getUserData(user_id:String){
//        loading.value = true
////        isFamilyMembar
//        if(true){
//            medicalRecordRepository.getFamilyMemberById(
//                user_id
//            ).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Log.e("tag","Exception ${it.toString()}")
//                    if(it != null){
//                        medicalRecordData2.value = it
//                        loading.value = false
//                    }
//                },{
//                    handleDataError("tag",it)
//                    loading.value = false
//                })
//        }else{
//            medicalRecordRepository.getUserDateById(
//                user_id
//            ).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    if(it != null){
//                        medicalRecordData.value = it
//                        loading.value = false
//                    }
//                },{
//                    handleDataError("tag",it)
//                    loading.value = false
//                })
//        }
//    }


}