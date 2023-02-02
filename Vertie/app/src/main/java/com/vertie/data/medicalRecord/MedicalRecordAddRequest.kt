package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MedicalRecordAddRequest(
    @Expose @SerializedName("Email") var email : String,
    @Expose @SerializedName("Date") var date : String,
    @Expose @SerializedName("TensionIndex") var tensionIndex: TensionIndex,
    @Expose @SerializedName("RecoveryAbility") var recoveryAbility: RecoveryAbility,
    @Expose @SerializedName("HeartRate") var heartRateRequest : HeartRateRequest,
    @Expose @SerializedName("Mood") var mood: Mood,
    @Expose @SerializedName("Notes") var notes: String
)