package com.vertie.javacode.models;

import com.google.gson.annotations.SerializedName;

public class ProxyUserModel {

    private String course_name;
    private int imgid;
    @SerializedName("name")
    private String name;
    @SerializedName("userId")
    private String userId;
    @SerializedName("memberStatus")
    private String memberStatus;
    @SerializedName("id")
    private String id;

    public ProxyUserModel(String id, String course_name, int imgid, String userId, String memberStatus) {
        this.id = id;
        this.course_name = course_name;
        this.imgid = imgid;
        this.userId = userId;
        this.memberStatus = memberStatus;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
