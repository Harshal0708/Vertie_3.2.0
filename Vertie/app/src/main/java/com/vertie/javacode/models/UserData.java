package com.vertie.javacode.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    public UserData(String id, String vertieNumber, String firstName, Object middleName, String nickName, String lastName, String gender, String dob, String primaryPhone, String email, String languageSpoken, String languageCode, String streetAddress1, String streetAddress2, String postalCode, String city, String state, String country, String userType, String userRole, Object nameofOrganization, Object unitAreaName, Object employerLocation, Object position, Object managerName, String doj, String password, String lastLogin, Object updatedBy, String updatedDate, String createdBy, String createdDate, String pictureProfile, Object pictureProfileBase64, String learningToken, String learningSession, Object pictureProfilePath, String phoneCode, Object accessToken, Object refreshToken, Object tokenExpiration, String googleFitEmail, String otp, String alertMessage) {
        this.id = id;
        this.vertieNumber = vertieNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.nickName = nickName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.primaryPhone = primaryPhone;
        this.email = email;
        this.languageSpoken = languageSpoken;
        this.languageCode = languageCode;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.userType = userType;
        this.userRole = userRole;
        this.nameofOrganization = nameofOrganization;
        this.unitAreaName = unitAreaName;
        this.employerLocation = employerLocation;
        this.position = position;
        this.managerName = managerName;
        this.doj = doj;
        this.password = password;
        this.lastLogin = lastLogin;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.pictureProfile = pictureProfile;
        this.pictureProfileBase64 = pictureProfileBase64;
        this.learningToken = learningToken;
        this.learningSession = learningSession;
        this.pictureProfilePath = pictureProfilePath;
        this.phoneCode = phoneCode;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenExpiration = tokenExpiration;
        this.googleFitEmail = googleFitEmail;
        this.otp = otp;
        this.alertMessage = alertMessage;
    }

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
    private Object accessToken;
    @SerializedName("refreshToken")
    @Expose
    private Object refreshToken;
    @SerializedName("tokenExpiration")
    @Expose
    private Object tokenExpiration;
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

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", vertieNumber='" + vertieNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName=" + middleName +
                ", nickName='" + nickName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", primaryPhone='" + primaryPhone + '\'' +
                ", email='" + email + '\'' +
                ", languageSpoken='" + languageSpoken + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", streetAddress1='" + streetAddress1 + '\'' +
                ", streetAddress2='" + streetAddress2 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", userType='" + userType + '\'' +
                ", userRole='" + userRole + '\'' +
                ", nameofOrganization=" + nameofOrganization +
                ", unitAreaName=" + unitAreaName +
                ", employerLocation=" + employerLocation +
                ", position=" + position +
                ", managerName=" + managerName +
                ", doj='" + doj + '\'' +
                ", password='" + password + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", updatedBy=" + updatedBy +
                ", updatedDate='" + updatedDate + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", pictureProfile='" + pictureProfile + '\'' +
                ", pictureProfileBase64=" + pictureProfileBase64 +
                ", learningToken='" + learningToken + '\'' +
                ", learningSession='" + learningSession + '\'' +
                ", pictureProfilePath=" + pictureProfilePath +
                ", phoneCode='" + phoneCode + '\'' +
                ", accessToken=" + accessToken +
                ", refreshToken=" + refreshToken +
                ", tokenExpiration=" + tokenExpiration +
                ", googleFitEmail='" + googleFitEmail + '\'' +
                ", otp='" + otp + '\'' +
                ", alertMessage='" + alertMessage + '\'' +
                '}';
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

    public Object getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(Object accessToken) {
        this.accessToken = accessToken;
    }

    public Object getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(Object refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Object getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Object tokenExpiration) {
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

}