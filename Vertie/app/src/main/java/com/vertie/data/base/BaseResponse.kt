package com.vertie.data.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
abstract class BaseResponse<T> {
    @SerializedName("Results")
    @Expose
    var results: T? = null

    @SerializedName("Errors")
    @Expose
    var errors: List<ErrorResponseObject> = listOf()

    @SerializedName("Messages")
    @Expose
    var messages: List<Any> = listOf()
}