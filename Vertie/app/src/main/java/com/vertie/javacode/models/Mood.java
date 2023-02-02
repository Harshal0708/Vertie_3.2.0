package com.vertie.javacode.models;

public class Mood {
    String type;
    Integer avtarImg;

    public Mood(String type, Integer avtarImg) {
        this.type = type;
        this.avtarImg = avtarImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAvtarImg() {
        return avtarImg;
    }

    public void setAvtarImg(Integer avtarImg) {
        this.avtarImg = avtarImg;
    }

}