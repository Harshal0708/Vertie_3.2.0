package com.vertie.utils.sdkConverters

import com.vertie.data.medicalRecord.RecoveryAbility
import de.kenkou.sdk.headless.domain.model.measurement.Measurement
import de.kenkou.sdk.visual.activityresults.KenkouActivityResult

object RecoveryAbilityConverter {

    fun convertToRecoveryAbility(measurement: Measurement) : RecoveryAbility{

        var status : String = ""
        var description : String = ""

        val recoveryAbility = measurement.hrv.rmssdNorm
        val approximattedRecoveryAbility: Int

        if (recoveryAbility > 0) {
            approximattedRecoveryAbility = (recoveryAbility + 0.5).toInt()
        }
        else {
            approximattedRecoveryAbility = (recoveryAbility - 0.5).toInt()
        }

        if(0<=approximattedRecoveryAbility && approximattedRecoveryAbility<25){
            status = "Poor"
            description = "You are not able to recover very well"
        }

        if(25<=approximattedRecoveryAbility && approximattedRecoveryAbility<50){
            status = "Okay"
            description = "You are able to recover partially"
        }

        if(50<=approximattedRecoveryAbility && approximattedRecoveryAbility<75){
            status = "Good"
            description = "You are able to recover well"
        }

        if(75<=approximattedRecoveryAbility && approximattedRecoveryAbility<100){
            status = "Excellent"
            description = "You are able to recover very well"
        }
        return RecoveryAbility(
            status = status,
            description = description
        )
    }



}