package com.vertie.modules.dashboard.historyfragment

import androidx.lifecycle.MutableLiveData
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.vertie.Constants
import com.vertie.data.medicalRecord.*
import com.vertie.data.medicalRecord.source.MedicalRecordDataSource
import com.vertie.data.user.Session
import com.vertie.di.qualifiers.Repo
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.modules.base.BaseViewModel
import com.vertie.utils.sdkConverters.HeartRateConverter
import com.vertie.utils.sdkConverters.MoodConverter
import com.vertie.utils.sdkConverters.RecoveryAbilityConverter
import com.vertie.utils.sdkConverters.TensionIndexConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject


class HistoryViewModel @Inject constructor(
    @Repo private val medicalRecordRepository: MedicalRecordDataSource,
    private val session: Session
) : BaseViewModel() {

    val Week : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val Day : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val Month : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val TAG = this.javaClass.simpleName
    val addToManual : MutableLiveData<Boolean> = MutableLiveData(false)


    val date : MutableLiveData<String> = MutableLiveData()
    val tensionIndex : MutableLiveData<String> = MutableLiveData()
    val recoveryAbility : MutableLiveData<String> = MutableLiveData()
    val pulse : MutableLiveData<String> = MutableLiveData()
    val mood : MutableLiveData<String> = MutableLiveData()
    val medicalRecordData : MutableLiveData<List<MedicalRecord>> = MutableLiveData()
    val weeklyGraphDataModel : MutableLiveData<List<WeeklyGraphDataModel>> = MutableLiveData()

    fun week(){
        Week.value = true
        getMedicalRecordsByDateType(1)
    }

    fun day(){
        Day.value = true
        getMedicalRecordsByDateType(0)
    }

    fun month(){
        Month.value = true
        getMedicalRecordsByDateType(2)
    }

    fun refresh(){
        downloadAllRecords()
    }

    fun setAddToManual(value : Boolean){
        addToManual.value = value
    }

    fun downloadAllRecords(){

        loading.value = true
        disposable.add(
            medicalRecordRepository.getAllRecords(
                session.currentUser?.email.let { MedicalRecordRequest(it) }
                    .let { convertToJSON(it) }
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           if(it != null){
                               medicalRecordData.value = it
                               loading.value = false
                           }
                },{
                    handleDataError(TAG,it)
                    loading.value = false
                })
        )
    }

    fun convertToJSON(medicalRecordRequest: MedicalRecordRequest) : String{
       return Klaxon().toJsonString(medicalRecordRequest)
    }

    fun getMedicalRecordsByDateType(type:Int){
        loading.value = true
        MedicalRecordGet(SingletonClass.getInstance().userId,SingletonClass.getInstance().email, LocalDateTime.now().toString(),type).let {
            medicalRecordRepository.getMedicalDateType(
                it
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it != null){
                        medicalRecordData.value = it.medicalRecords
                        weeklyGraphDataModel.value = it.weeklyGraphDataModel
                        loading.value = false
                    }
                },{
                    handleDataError("tag",it)
                    loading.value = false
                })
        }.let {
            disposable.add(
                it
            )
            loading.value = false
        }
    }
}