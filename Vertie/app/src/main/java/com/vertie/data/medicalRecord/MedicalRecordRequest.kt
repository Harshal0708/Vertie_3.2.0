package com.vertie.data.medicalRecord

import com.beust.klaxon.Json
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vertie.Constants

data class MedicalRecordRequest(
    @Json(index = 1) @Expose @SerializedName("Email") var Email: String? = ""
)