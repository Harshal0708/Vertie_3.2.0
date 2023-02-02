package com.vertie.data.medicalRecord

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData(
    @Expose @SerializedName("id") var id:String,
    @Expose @SerializedName("vertieNumber") var vertieNumber:String,
    @Expose @SerializedName("firstName") var firstName:String,
    @Expose @SerializedName("middleName") var middleName:String,
    @Expose @SerializedName("nickName") var nickName:String,
    @Expose @SerializedName("lastName") var lastName:String,
    @Expose @SerializedName("gender") var gender:String,
    @Expose @SerializedName("dob") var dob:String,
    @Expose @SerializedName("primaryPhone") var primaryPhone:String,
    @Expose @SerializedName("email") var email:String,
    @Expose @SerializedName("languageSpoken") var languageSpoken:String,
    @Expose @SerializedName("languageCode") var languageCode:String,
    @Expose @SerializedName("streetAddress1") var streetAddress1:String,
    @Expose @SerializedName("streetAddress2") var streetAddress2:String,
    @Expose @SerializedName("postalCode") var postalCode:String,
    @Expose @SerializedName("city") var city:String,
    @Expose @SerializedName("state") var state:String,
    @Expose @SerializedName("country") var country:String,
    @Expose @SerializedName("userType") var userType:String,
    @Expose @SerializedName("userRole") var userRole:String,
    @Expose @SerializedName("nameofOrganization") var nameofOrganization:String,
    @Expose @SerializedName("unitAreaName") var unitAreaName:String,
    @Expose @SerializedName("employerLocation") var employerLocation:String,
    @Expose @SerializedName("position") var position:String,
    @Expose @SerializedName("managerName") var managerName:String,
    @Expose @SerializedName("doj") var doj:String,
    @Expose @SerializedName("password") var password:String,
    @Expose @SerializedName("lastLogin") var lastLogin:String,
    @Expose @SerializedName("updatedBy") var updatedBy:String,
    @Expose @SerializedName("updatedDate") var updatedDate:String,
    @Expose @SerializedName("createdBy") var createdBy:String,
    @Expose @SerializedName("createdDate") var createdDate:String,
    @Expose @SerializedName("pictureProfile") var pictureProfile:String,
    @Expose @SerializedName("pictureProfile_base64") var pictureProfile_base64:String,
    @Expose @SerializedName("learningToken") var learningToken:String,
    @Expose @SerializedName("learningSession") var learningSession:String,
    @Expose @SerializedName("pictureProfilePath") var pictureProfilePath:String,
    @Expose @SerializedName("phoneCode") var phoneCode:String,
    @Expose @SerializedName("accessToken") var accessToken:String,
    @Expose @SerializedName("refreshToken") var refreshToken:String,
    @Expose @SerializedName("tokenExpiration") var tokenExpiration:String,
    @Expose @SerializedName("googleFitEmail") var googleFitEmail:String,
    @Expose @SerializedName("otp") var otp:String,
    @Expose @SerializedName("alertMessager") var alertMessager:String
) : Serializable


