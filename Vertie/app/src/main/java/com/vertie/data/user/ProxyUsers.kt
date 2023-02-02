package com.vertie.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProxyUsers(
    @SerializedName("id") @Expose val id : String? = null,
    @SerializedName("userId") @Expose val userId : String? = null,
    @SerializedName("proxyUserIds") @Expose val proxyUserIds : String? = null,
    @SerializedName("cmDate") @Expose val cmDate : String? = null,
    @SerializedName("registration") @Expose val registration : Userr? = null
)