package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TensionIndex(
    @Expose @SerializedName("value") var value : Int,
    var valueString : String,
    @Expose @SerializedName("status") var status : String,
    @Expose @SerializedName("description") var description : String
) : Serializable
