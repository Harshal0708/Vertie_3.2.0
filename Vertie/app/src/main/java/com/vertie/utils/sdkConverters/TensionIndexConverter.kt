package com.vertie.utils.sdkConverters

import com.vertie.data.medicalRecord.TensionIndex
import de.kenkou.sdk.headless.domain.model.measurement.Measurement
import de.kenkou.sdk.visual.activityresults.KenkouActivityResult

object TensionIndexConverter {

    fun convertToTensionIndex(measurement: Measurement) : TensionIndex{
        val tensionIndexValue = measurement.hrv.stressIndexNorm
        val approximattedTensionIndexValue: Int

        if (tensionIndexValue > 0) {
            approximattedTensionIndexValue = (tensionIndexValue + 0.5).toInt()
        }
        else {
            approximattedTensionIndexValue = (tensionIndexValue - 0.5).toInt()
        }

        var value = approximattedTensionIndexValue
        var valueString = approximattedTensionIndexValue.toString()
        var status = ""
        var description = ""

        if(0<=approximattedTensionIndexValue && approximattedTensionIndexValue<25){
            status = "Low"
            description = "You are not tense"
        }

        if(25<=approximattedTensionIndexValue && approximattedTensionIndexValue<50){
            status = "Medium"
            description = "You are slightly tense"
        }

        if(50<=approximattedTensionIndexValue && approximattedTensionIndexValue<75){
            status = "High"
            description = "You are tense"
        }

        if(75<=approximattedTensionIndexValue && approximattedTensionIndexValue<100){
            status = "Very High"
            description = "You are very tense"
        }

        return TensionIndex(
            value,
            valueString,
            status,
            description
        )
    }

}