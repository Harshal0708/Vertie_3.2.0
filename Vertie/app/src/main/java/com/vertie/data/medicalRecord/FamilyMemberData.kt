package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FamilyMemberData(
    @Expose @SerializedName("id") var id:String,
    @Expose @SerializedName("relationId") var relationId:String,
    @Expose @SerializedName("name") var name:String,
    @Expose @SerializedName("dob") var dob:String,
    @Expose @SerializedName("dod") var dod:String,
//    @Expose @SerializedName("translations") var translations:String,
    @Expose @SerializedName("occupation") var occupation:String,
    @Expose @SerializedName("relationShipStatus") var relationShipStatus:String,
    @Expose @SerializedName("zipCode") var zipCode:String,
    @Expose @SerializedName("traits") var traits:String,
    @Expose @SerializedName("userId") var userId:String,
    @Expose @SerializedName("memberStatus") var memberStatus:String,
    @Expose @SerializedName("familyMemberOrder") var familyMemberOrder:String,
    @Expose @SerializedName("parents") var parents:String,
    @Expose @SerializedName("spouses") var spouses:String,
    @Expose @SerializedName("gender") var gender:String,
)