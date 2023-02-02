package com.vertie.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("EmailorVIN") @Expose val email: String? = null,
    @SerializedName("Password") @Expose val password: String? = null,
    )
