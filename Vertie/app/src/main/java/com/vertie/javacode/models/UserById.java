
package com.vertie.javacode.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserById {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("vertieNumber")
    @Expose
    private String vertieNumber;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private Object middleName;
    @SerializedName("nickName")
    @Expose
    private String nickName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("primaryPhone")
    @Expose
    private String primaryPhone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("languageSpoken")
    @Expose
    private String languageSpoken;
    @SerializedName("languageCode")
    @Expose
    private String languageCode;
    @SerializedName("streetAddress1")
    @Expose
    private String streetAddress1;
    @SerializedName("streetAddress2")
    @Expose
    private String streetAddress2;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("userRole")
    @Expose
    private String userRole;
    @SerializedName("nameofOrganization")
    @Expose
    private Object nameofOrganization;
    @SerializedName("unitAreaName")
    @Expose
    private Object unitAreaName;
    @SerializedName("employerLocation")
    @Expose
    private Object employerLocation;
    @SerializedName("position")
    @Expose
    private Object position;
    @SerializedName("managerName")
    @Expose
    private Object managerName;
    @SerializedName("doj")
    @Expose
    private String doj;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("lastLogin")
    @Expose
    private String lastLogin;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("pictureProfile")
    @Expose
    private String pictureProfile;
    @SerializedName("pictureProfile_base64")
    @Expose
    private Object pictureProfileBase64;
    @SerializedName("learningToken")
    @Expose
    private String learningToken;
    @SerializedName("learningSession")
    @Expose
    private String learningSession;
    @SerializedName("pictureProfilePath")
    @Expose
    private Object pictureProfilePath;
    @SerializedName("phoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("tokenExpiration")
    @Expose
    private String tokenExpiration;
    @SerializedName("googleFitEmail")
    @Expose
    private String googleFitEmail;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("alertMessage")
    @Expose
    private String alertMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVertieNumber() {
        return vertieNumber;
    }

    public void setVertieNumber(String vertieNumber) {
        this.vertieNumber = vertieNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getMiddleName() {
        return middleName;
    }

    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguageSpoken() {
        return languageSpoken;
    }

    public void setLanguageSpoken(String languageSpoken) {
        this.languageSpoken = languageSpoken;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Object getNameofOrganization() {
        return nameofOrganization;
    }

    public void setNameofOrganization(Object nameofOrganization) {
        this.nameofOrganization = nameofOrganization;
    }

    public Object getUnitAreaName() {
        return unitAreaName;
    }

    public void setUnitAreaName(Object unitAreaName) {
        this.unitAreaName = unitAreaName;
    }

    public Object getEmployerLocation() {
        return employerLocation;
    }

    public void setEmployerLocation(Object employerLocation) {
        this.employerLocation = employerLocation;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public Object getManagerName() {
        return managerName;
    }

    public void setManagerName(Object managerName) {
        this.managerName = managerName;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPictureProfile() {
        return pictureProfile;
    }

    public void setPictureProfile(String pictureProfile) {
        this.pictureProfile = pictureProfile;
    }

    public Object getPictureProfileBase64() {
        return pictureProfileBase64;
    }

    public void setPictureProfileBase64(Object pictureProfileBase64) {
        this.pictureProfileBase64 = pictureProfileBase64;
    }

    public String getLearningToken() {
        return learningToken;
    }

    public void setLearningToken(String learningToken) {
        this.learningToken = learningToken;
    }

    public String getLearningSession() {
        return learningSession;
    }

    public void setLearningSession(String learningSession) {
        this.learningSession = learningSession;
    }

    public Object getPictureProfilePath() {
        return pictureProfilePath;
    }

    public void setPictureProfilePath(Object pictureProfilePath) {
        this.pictureProfilePath = pictureProfilePath;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(String tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getGoogleFitEmail() {
        return googleFitEmail;
    }

    public void setGoogleFitEmail(String googleFitEmail) {
        this.googleFitEmail = googleFitEmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserById.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("vertieNumber");
        sb.append('=');
        sb.append(((this.vertieNumber == null)?"<null>":this.vertieNumber));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("middleName");
        sb.append('=');
        sb.append(((this.middleName == null)?"<null>":this.middleName));
        sb.append(',');
        sb.append("nickName");
        sb.append('=');
        sb.append(((this.nickName == null)?"<null>":this.nickName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("gender");
        sb.append('=');
        sb.append(((this.gender == null)?"<null>":this.gender));
        sb.append(',');
        sb.append("dob");
        sb.append('=');
        sb.append(((this.dob == null)?"<null>":this.dob));
        sb.append(',');
        sb.append("primaryPhone");
        sb.append('=');
        sb.append(((this.primaryPhone == null)?"<null>":this.primaryPhone));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("languageSpoken");
        sb.append('=');
        sb.append(((this.languageSpoken == null)?"<null>":this.languageSpoken));
        sb.append(',');
        sb.append("languageCode");
        sb.append('=');
        sb.append(((this.languageCode == null)?"<null>":this.languageCode));
        sb.append(',');
        sb.append("streetAddress1");
        sb.append('=');
        sb.append(((this.streetAddress1 == null)?"<null>":this.streetAddress1));
        sb.append(',');
        sb.append("streetAddress2");
        sb.append('=');
        sb.append(((this.streetAddress2 == null)?"<null>":this.streetAddress2));
        sb.append(',');
        sb.append("postalCode");
        sb.append('=');
        sb.append(((this.postalCode == null)?"<null>":this.postalCode));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("userType");
        sb.append('=');
        sb.append(((this.userType == null)?"<null>":this.userType));
        sb.append(',');
        sb.append("userRole");
        sb.append('=');
        sb.append(((this.userRole == null)?"<null>":this.userRole));
        sb.append(',');
        sb.append("nameofOrganization");
        sb.append('=');
        sb.append(((this.nameofOrganization == null)?"<null>":this.nameofOrganization));
        sb.append(',');
        sb.append("unitAreaName");
        sb.append('=');
        sb.append(((this.unitAreaName == null)?"<null>":this.unitAreaName));
        sb.append(',');
        sb.append("employerLocation");
        sb.append('=');
        sb.append(((this.employerLocation == null)?"<null>":this.employerLocation));
        sb.append(',');
        sb.append("position");
        sb.append('=');
        sb.append(((this.position == null)?"<null>":this.position));
        sb.append(',');
        sb.append("managerName");
        sb.append('=');
        sb.append(((this.managerName == null)?"<null>":this.managerName));
        sb.append(',');
        sb.append("doj");
        sb.append('=');
        sb.append(((this.doj == null)?"<null>":this.doj));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("lastLogin");
        sb.append('=');
        sb.append(((this.lastLogin == null)?"<null>":this.lastLogin));
        sb.append(',');
        sb.append("updatedBy");
        sb.append('=');
        sb.append(((this.updatedBy == null)?"<null>":this.updatedBy));
        sb.append(',');
        sb.append("updatedDate");
        sb.append('=');
        sb.append(((this.updatedDate == null)?"<null>":this.updatedDate));
        sb.append(',');
        sb.append("createdBy");
        sb.append('=');
        sb.append(((this.createdBy == null)?"<null>":this.createdBy));
        sb.append(',');
        sb.append("createdDate");
        sb.append('=');
        sb.append(((this.createdDate == null)?"<null>":this.createdDate));
        sb.append(',');
        sb.append("pictureProfile");
        sb.append('=');
        sb.append(((this.pictureProfile == null)?"<null>":this.pictureProfile));
        sb.append(',');
        sb.append("pictureProfileBase64");
        sb.append('=');
        sb.append(((this.pictureProfileBase64 == null)?"<null>":this.pictureProfileBase64));
        sb.append(',');
        sb.append("learningToken");
        sb.append('=');
        sb.append(((this.learningToken == null)?"<null>":this.learningToken));
        sb.append(',');
        sb.append("learningSession");
        sb.append('=');
        sb.append(((this.learningSession == null)?"<null>":this.learningSession));
        sb.append(',');
        sb.append("pictureProfilePath");
        sb.append('=');
        sb.append(((this.pictureProfilePath == null)?"<null>":this.pictureProfilePath));
        sb.append(',');
        sb.append("phoneCode");
        sb.append('=');
        sb.append(((this.phoneCode == null)?"<null>":this.phoneCode));
        sb.append(',');
        sb.append("accessToken");
        sb.append('=');
        sb.append(((this.accessToken == null)?"<null>":this.accessToken));
        sb.append(',');
        sb.append("refreshToken");
        sb.append('=');
        sb.append(((this.refreshToken == null)?"<null>":this.refreshToken));
        sb.append(',');
        sb.append("tokenExpiration");
        sb.append('=');
        sb.append(((this.tokenExpiration == null)?"<null>":this.tokenExpiration));
        sb.append(',');
        sb.append("googleFitEmail");
        sb.append('=');
        sb.append(((this.googleFitEmail == null)?"<null>":this.googleFitEmail));
        sb.append(',');
        sb.append("otp");
        sb.append('=');
        sb.append(((this.otp == null)?"<null>":this.otp));
        sb.append(',');
        sb.append("alertMessage");
        sb.append('=');
        sb.append(((this.alertMessage == null)?"<null>":this.alertMessage));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
