package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeeklyGraphDataModel(
    @Expose @SerializedName("weekDay") var weekDay : String,
    @Expose @SerializedName("avgTensionIndexValue") var avgTensionIndexValue : Int,
    @Expose @SerializedName("avgHeartRateValue") var avgHeartRateValue : Int,
)
