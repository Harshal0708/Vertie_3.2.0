package com.vertie.javacode.models;

import java.util.HashMap;

public class MenualEntryObj {

    HashMap<String, String> setp1;
    HashMap<String, String> setp2;
    HashMap<String, String> setp3;
    HashMap<String, String> setp4;
    HashMap<String, String> setp5;
    HashMap<String, String> setp6;
    HashMap<String, String> setp7;

    public MenualEntryObj(HashMap<String, String> setp1, HashMap<String, String> setp2, HashMap<String, String> setp3, HashMap<String, String> setp4, HashMap<String, String> setp5, HashMap<String, String> setp6, HashMap<String, String> setp7) {
        this.setp1 = setp1;
        this.setp2 = setp2;
        this.setp3 = setp3;
        this.setp4 = setp4;
        this.setp5 = setp5;
        this.setp6 = setp6;
        this.setp7 = setp7;
    }

    public HashMap<String, String> getSetp1() {
        return setp1;
    }

    public void setSetp1(HashMap<String, String> setp1) {
        this.setp1 = setp1;
    }

    public HashMap<String, String> getSetp2() {
        return setp2;
    }

    public void setSetp2(HashMap<String, String> setp2) {
        this.setp2 = setp2;
    }

    public HashMap<String, String> getSetp3() {
        return setp3;
    }

    public void setSetp3(HashMap<String, String> setp3) {
        this.setp3 = setp3;
    }

    public HashMap<String, String> getSetp4() {
        return setp4;
    }

    public void setSetp4(HashMap<String, String> setp4) {
        this.setp4 = setp4;
    }

    public HashMap<String, String> getSetp5() {
        return setp5;
    }

    public void setSetp5(HashMap<String, String> setp5) {
        this.setp5 = setp5;
    }

    public HashMap<String, String> getSetp6() {
        return setp6;
    }

    public void setSetp6(HashMap<String, String> setp6) {
        this.setp6 = setp6;
    }

    public HashMap<String, String> getSetp7() {
        return setp7;
    }

    public void setSetp7(HashMap<String, String> setp7) {
        this.setp7 = setp7;
    }

    @Override
    public String toString() {
        return "{" +
                "setp1=" + setp1 +
                ", setp2=" + setp2 +
                ", setp3=" + setp3 +
                ", setp4=" + setp4 +
                ", setp5=" + setp5 +
                ", setp6=" + setp6 +
                ", setp7=" + setp7 +
                '}';
    }
}