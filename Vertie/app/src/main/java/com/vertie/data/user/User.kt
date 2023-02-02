package com.vertie.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vertie.data.base.BaseResponse
import java.io.Serializable

data class User(
    @Expose @SerializedName("EmailorVIN") var email : String? = null,
    @Expose @SerializedName("Password") var password : String? = null,
    @Expose @SerializedName("id") var id : String? = null
) : Serializable

class UserResponse : BaseResponse<User>()
