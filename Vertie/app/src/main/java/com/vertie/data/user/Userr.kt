package com.vertie.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vertie.data.base.BaseResponse
import java.io.Serializable

class Userr (
   @Expose @SerializedName("id"                    ) var id                   : String? = null,
   @Expose @SerializedName("vertieNumber"          ) var vertieNumber         : String? = null,
   @Expose @SerializedName("firstName"             ) var firstName            : String? = null,
   @Expose @SerializedName("middleName"            ) var middleName           : String? = null,
   @Expose @SerializedName("nickName"              ) var nickName             : String? = null,
   @Expose @SerializedName("lastName"              ) var lastName             : String? = null,
   @Expose @SerializedName("gender"                ) var gender               : String? = null,
   @Expose @SerializedName("dob"                   ) var dob                  : String? = null,
   @Expose @SerializedName("primaryPhone"          ) var primaryPhone         : String? = null,
   @Expose @SerializedName("email"                 ) var email                : String? = null,
   @Expose @SerializedName("languageSpoken"        ) var languageSpoken       : String? = null,
   @Expose @SerializedName("languageCode"          ) var languageCode         : String? = null,
   @Expose @SerializedName("streetAddress1"        ) var streetAddress1       : String? = null,
   @Expose @SerializedName("streetAddress2"        ) var streetAddress2       : String? = null,
   @Expose @SerializedName("postalCode"            ) var postalCode           : String? = null,
   @Expose @SerializedName("city"                  ) var city                 : String? = null,
   @Expose @SerializedName("state"                 ) var state                : String? = null,
   @Expose @SerializedName("country"               ) var country              : String? = null,
   @Expose @SerializedName("userType"              ) var userType             : String? = null,
   @Expose @SerializedName("userRole"              ) var userRole             : String? = null,
   @Expose @SerializedName("nameofOrganization"    ) var nameofOrganization   : String? = null,
   @Expose @SerializedName("unitAreaName"          ) var unitAreaName         : String? = null,
   @Expose @SerializedName("employerLocation"      ) var employerLocation     : String? = null,
   @Expose @SerializedName("position"              ) var position             : String? = null,
   @Expose @SerializedName("managerName"           ) var managerName          : String? = null,
   @Expose @SerializedName("doj"                   ) var doj                  : String? = null,
   @Expose @SerializedName("password"              ) var password             : String? = null,
   @Expose @SerializedName("lastLogin"             ) var lastLogin            : String? = null,
   @Expose @SerializedName("updatedBy"             ) var updatedBy            : String? = null,
   @Expose @SerializedName("updatedDate"           ) var updatedDate          : String? = null,
   @Expose @SerializedName("createdBy"             ) var createdBy            : String? = null,
   @Expose @SerializedName("createdDate"           ) var createdDate          : String? = null,
   @Expose @SerializedName("pictureProfile"        ) var pictureProfile       : String? = null,
   @Expose @SerializedName("pictureProfile_base64" ) var pictureProfileBase64 : String? = null,
   @Expose @SerializedName("learningToken"         ) var learningToken        : String? = null,
   @Expose @SerializedName("learningSession"       ) var learningSession      : String? = null,
   @Expose @SerializedName("pictureProfilePath"    ) var pictureProfilePath   : String? = null,
   @Expose @SerializedName("phoneCode"             ) var phoneCode            : String? = null,
   @Expose @SerializedName("accessToken"           ) var accessToken          : String? = null,
   @Expose @SerializedName("refreshToken"          ) var refreshToken         : String? = null,
   @Expose @SerializedName("tokenExpiration"       ) var tokenExpiration      : String? = null,
   @Expose @SerializedName("googleFitEmail"        ) var googleFitEmail       : String? = null,
   @Expose @SerializedName("otp"                   ) var otp                  : String? = null,
   @Expose @SerializedName("alertMessage"          ) var alertMessage         : String? = null
): Serializable {
   constructor(Userr: Userr) : this() {
   }
}

class UserrResponse : BaseResponse<Userr>()