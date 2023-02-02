package com.vertie.javacode.models;

public class MenualRecordAddObject {
    String email;
    String date;
    ItemModel tensionIndex;
    ItemModel recoveryAbility;
    ItemModel heartRate;
    ItemModel mood;
    String notes;

    public MenualRecordAddObject(String email, String date, ItemModel tensionIndex, ItemModel recoveryAbility, ItemModel heartRate, ItemModel mood, String notes) {
        this.email = email;
        this.date = date;
        this.tensionIndex = tensionIndex;
        this.recoveryAbility = recoveryAbility;
        this.heartRate = heartRate;
        this.mood = mood;
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ItemModel getTensionIndex() {
        return tensionIndex;
    }

    public void setTensionIndex(ItemModel tensionIndex) {
        this.tensionIndex = tensionIndex;
    }

    public ItemModel getRecoveryAbility() {
        return recoveryAbility;
    }

    public void setRecoveryAbility(ItemModel recoveryAbility) {
        this.recoveryAbility = recoveryAbility;
    }

    public ItemModel getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(ItemModel heartRate) {
        this.heartRate = heartRate;
    }

    public ItemModel getMood() {
        return mood;
    }

    public void setMood(ItemModel mood) {
        this.mood = mood;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "{" +
                "email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", tensionIndex=" + tensionIndex +
                ", recoveryAbility=" + recoveryAbility +
                ", heartRate=" + heartRate +
                ", mood=" + mood +
                ", notes='" + notes + '\'' +
                '}';
    }
}
