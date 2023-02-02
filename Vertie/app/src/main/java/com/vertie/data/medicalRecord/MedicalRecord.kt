package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MedicalRecord(
    @Expose @SerializedName("email") var email : String,
    @Expose @SerializedName("date") var date : String,
    @Expose @SerializedName("notes")var notes : String,
    @Expose @SerializedName("id")var id : String,
    @Expose @SerializedName("isActive")var isActive : Boolean,
    @Expose @SerializedName("EmailorVIN") var isDeleted : Boolean,
    @Expose @SerializedName("description") var description : String,
    @Expose @SerializedName("created") var createdAt : String,
    @Expose @SerializedName("modified") var modifiedAt : String,
    @Expose @SerializedName("tensionIndex") var tensionIndex: TensionIndex,
    @Expose @SerializedName("recoveryAbility") var recoveryAbility : RecoveryAbility,
    @Expose @SerializedName("heartRate") var heartRateResponse : HeartRateResponse,
    @Expose @SerializedName("mood") var mood : Mood
) : Serializable


