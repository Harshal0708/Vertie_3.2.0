package com.vertie.data.medicalRecord

import com.beust.klaxon.Status
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeartRateRequest(
    @Expose @SerializedName("Value") var value : Int,
    @Expose @SerializedName("Status") var status : String
)
