package com.vertie.utils.sdkConverters

import com.vertie.data.medicalRecord.HeartRateRequest
import com.vertie.data.medicalRecord.TensionIndex
import de.kenkou.sdk.headless.domain.model.measurement.Measurement
import de.kenkou.sdk.visual.activityresults.KenkouActivityResult

object HeartRateConverter {

    fun convertToHeartRateRequest(measurement: Measurement) : HeartRateRequest {
        val heartBeatValue = measurement.hrv.hrAvgBpm
        val approximattedHeartBeatValue: Int

        if (heartBeatValue > 0) {
            approximattedHeartBeatValue = (heartBeatValue + 0.5).toInt()
        }
        else {
            approximattedHeartBeatValue = (heartBeatValue - 0.5).toInt()
        }
        var status : String = ""


        if(0<approximattedHeartBeatValue && approximattedHeartBeatValue<=65){
            status = "Normal"
        }

        if(65<approximattedHeartBeatValue && approximattedHeartBeatValue<=85){
            status = "Medium"
        }

        if(85<approximattedHeartBeatValue && approximattedHeartBeatValue<=100){
            status = "High"
        }

        if(100<approximattedHeartBeatValue && approximattedHeartBeatValue<=170){
            status = "Very High"
        }

        return HeartRateRequest(
            approximattedHeartBeatValue,
            status
        )
    }


}