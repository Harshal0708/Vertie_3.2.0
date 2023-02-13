package com.vertie.javacode.singleton;

import com.vertie.javacode.models.QuestionsObj;

import java.util.ArrayList;

public class SingletonClass {

    private static SingletonClass mInstance;

    public String token;
    public String selectedStep4="";
    public String selectedStep5="";
    public String selectedStep7="";

    public String userId="";
    public String email="";
    public ArrayList<QuestionsObj> questionsArrListData =null;
    private SingletonClass() {}

    public static SingletonClass getInstance(){
        if(mInstance == null)
            mInstance = new SingletonClass();
        return mInstance;
    }

}