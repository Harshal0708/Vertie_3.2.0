package com.vertie.utils.sdkConverters

import com.vertie.data.medicalRecord.Mood
import de.kenkou.sdk.headless.domain.model.measurement.Measurement
import de.kenkou.sdk.visual.activityresults.KenkouActivityResult

object MoodConverter {


    fun convertToMood(measurment : Measurement) : Mood{
        var status : String = ""
        when(measurment.pmqa?.mood){
            10 -> status = "Bad"
            20 -> status = "Okay"
            30 -> status = "Good"
            40 -> status = "Excellent"
        }
        return Mood(
            status
        )
    }

}