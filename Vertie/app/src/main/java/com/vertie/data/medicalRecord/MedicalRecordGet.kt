package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MedicalRecordGet(

    @Expose @SerializedName("ProfileId") var ProfileId : String,
    @Expose @SerializedName("email") var email : String,
    @Expose @SerializedName("date") var date : String,
    @Expose @SerializedName("dateType") var dateType: Int
)