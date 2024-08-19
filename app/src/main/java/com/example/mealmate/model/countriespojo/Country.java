package com.example.mealmate.model.countriespojo;

public class Country {
    public Country(String strArea,String strContryThumb){
        this.strArea=strArea;
        this.strContryThumb=strContryThumb;
    }
    public String getStrArea() {
        return strArea;
    }
    public String getstrContryThumb() {
        return strContryThumb;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String strArea;
    public String strContryThumb;
}
