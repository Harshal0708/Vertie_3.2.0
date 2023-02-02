package com.vertie.data.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vertie.data.base.BaseResponse
import java.io.Serializable

class FamilyMember (
   @Expose @SerializedName("id"          ) var id         : String? = null,
   @Expose @SerializedName("relationId"          ) var relationId         : String? = null,
   @Expose @SerializedName("name"          ) public  var name         : String? = null,
   @Expose @SerializedName("dob"          ) var dob         : String? = null,
//   @Expose @SerializedName("dod"          ) var dod         : String? = null,
//   @Expose @SerializedName("translations"          ) var translations         : String? = null,
   @Expose @SerializedName("occupation"          ) var occupation         : String? = null,
   @Expose @SerializedName("relationShipStatus"          ) var relationShipStatus         : String? = null,
   @Expose @SerializedName("zipCode"          ) var zipCode         : String? = null,
   @Expose @SerializedName("traits"          ) var traits         : String? = null,
   @Expose @SerializedName("userId"          ) var userId         : String? = null,
   @Expose @SerializedName("memberStatus"          ) var memberStatus         : String? = null,
   @Expose @SerializedName("familyMemberOrder"          ) var familyMemberOrder         : String? = null,
   @Expose @SerializedName("parents"          ) var parents         : String? = null,
   @Expose @SerializedName("spouses"          ) var spouses         : String? = null,
   @Expose @SerializedName("gender"          ) var gender         : String? = null,
): Serializable

class FamilyMembers : BaseResponse<List<FamilyMember>>()