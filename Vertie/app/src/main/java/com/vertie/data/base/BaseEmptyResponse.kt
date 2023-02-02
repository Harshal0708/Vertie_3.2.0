package com.vertie.data.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseEmptyResponse(
    @SerializedName("Errors")
    @Expose
    var errors: List<ErrorResponseObject> = listOf(),

    @SerializedName("Messages")
    @Expose
    var messages: List<Any> = listOf()
)