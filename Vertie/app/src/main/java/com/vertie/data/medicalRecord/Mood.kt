package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Mood(
    @Expose @SerializedName("status") var status : String,
) : Serializable
