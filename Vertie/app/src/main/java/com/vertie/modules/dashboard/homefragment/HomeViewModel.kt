package com.vertie.modules.dashboard.homefragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.vertie.data.medicalRecord.MedicalRecordAddRequest
import com.vertie.data.medicalRecord.source.MedicalRecordDataSource
import com.vertie.data.medicalRecord.source.MedicalRecordRepository
import com.vertie.data.user.Session
import com.vertie.data.user.User
import com.vertie.data.user.source.UserDataSource
import com.vertie.di.qualifiers.Repo
import com.vertie.modules.base.BaseViewModel
import com.vertie.utils.sdkConverters.HeartRateConverter
import com.vertie.utils.sdkConverters.MoodConverter
import com.vertie.utils.sdkConverters.RecoveryAbilityConverter
import com.vertie.utils.sdkConverters.TensionIndexConverter
import de.kenkou.sdk.headless.domain.model.measurement.Measurement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDateTime
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val session: Session,
    @Repo private val mediaclRecordRepository: MedicalRecordDataSource,
) : BaseViewModel() {

    val goToMeasurement : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val goToTutorial : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    fun measure(){
        goToMeasurement.value = true
    }

    fun learn(){
        goToTutorial.value = true
    }

    fun addRecordFromResult(measurement: Measurement){
        Log.d("dataaaaa ::: ",measurement.toString())
        var gson = Gson()
//        var mMineUserEntity = gson?.fromJson(measurement, Measurement::class.java)
//        JSON.stringify(testData)
        var addMedicalRecordAddRequest : MedicalRecordAddRequest? = session.currentUser?.email?.let {
            MedicalRecordAddRequest(
                date = prepareDate(),
                email = it,
                heartRateRequest = HeartRateConverter.convertToHeartRateRequest(measurement) ,
                mood = MoodConverter.convertToMood(measurement),
                recoveryAbility = RecoveryAbilityConverter.convertToRecoveryAbility(measurement),
                tensionIndex = TensionIndexConverter.convertToTensionIndex(measurement),
                notes = ""
                )
        }

        addMedicalRecordAddRequest?.let {
            mediaclRecordRepository.addRecord(
                it
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },{
                    handleDataError("tag",it)
                })
        }?.let {
            disposable.add(
                it
            )
        }

    }

    private fun prepareDate() : String{
        val currentDateTime = LocalDateTime.now()
        val dayName = currentDateTime.dayOfWeek.name
        val dayMonth = currentDateTime.dayOfMonth.toString()
        val monthName = currentDateTime.month.name
        val year = currentDateTime.year.toString()

        return dayName + "," + dayMonth + " " + monthName + " " + year
    }


}