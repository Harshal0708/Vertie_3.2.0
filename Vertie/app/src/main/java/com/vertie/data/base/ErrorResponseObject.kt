package com.vertie.data.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponseObject(
    @SerializedName("ErrorMessage") @Expose var errorMessage: String?
)