package com.vertie.javacode.models;

public class ItemModel {
    Integer value;
    String statu;
    String description;

    public ItemModel(Integer value, String statu, String description) {
        this.value = value;
        this.statu = statu;
        this.description = description;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "value:" + value +
                ", statu:'" + statu + '\'' +
                ", description:'" + description + '\'' +
                '}';
    }
}
