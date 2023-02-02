package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MedicalRecordResponce(
                                 @Expose @SerializedName("weeklyGraphViewModel") var weeklyGraphDataModel : List<WeeklyGraphDataModel>,
                                 @Expose @SerializedName("medicalRecords") var medicalRecords : List<MedicalRecord>,
) : Serializable
