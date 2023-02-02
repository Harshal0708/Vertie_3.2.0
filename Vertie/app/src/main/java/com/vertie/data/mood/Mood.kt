package com.vertie.data.mood

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Mood(
    @Expose @SerializedName("Status") var mood : String = "",
) : Serializable
