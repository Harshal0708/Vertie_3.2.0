package com.vertie.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("resultCode") @Expose val resultCode : String? = null,
    @SerializedName("resultMessage") @Expose val resultMessage : String? = null,
    @SerializedName("jwtToken") @Expose val jwtToken : String? = null,
    @SerializedName("moduleAccess") @Expose val moduleAccess : String? = null,
    @SerializedName("user") @Expose val user : Userr? = null,
    @SerializedName("familyMembers") @Expose val familyMembers : List<FamilyMember>? = null,
    @SerializedName("proxyUsers") @Expose val proxyUsers : List<ProxyUsers>? = null
)
