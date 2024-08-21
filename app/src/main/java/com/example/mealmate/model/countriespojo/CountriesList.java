package com.example.mealmate.model.countriespojo;

import java.util.ArrayList;

public class CountriesList {
    public ArrayList<Country> countryList;
    public static CountriesList instance = null;
    public CountriesList() {
        countryList = new ArrayList<>();
        countryList.add(new Country("American", "https://www.worldometers.info/img/flags/us-flag.gif"));
        countryList.add(new Country("British", "https://www.worldometers.info/img/flags/uk-flag.gif"));
        countryList.add(new Country("Canadian", "https://www.worldometers.info/img/flags/ca-flag.gif"));
        countryList.add(new Country("Chinese", "https://www.worldometers.info/img/flags/ch-flag.gif"));
        countryList.add(new Country("Croatian", "https://www.worldometers.info/img/flags/hr-flag.gif"));
        countryList.add(new Country("Dutch", "https://www.worldometers.info/img/flags/gm-flag.gif"));
        countryList.add(new Country("Egyptian", "https://www.worldometers.info/img/flags/eg-flag.gif"));
        countryList.add(new Country("Filipino", "https://www.worldometers.info/img/flags/rp-flag.gif"));
        countryList.add(new Country("French", "https://www.worldometers.info/img/flags/fr-flag.gif"));
        countryList.add(new Country("Greek", "https://www.worldometers.info/img/flags/gr-flag.gif"));
        countryList.add(new Country("Indian", "https://www.worldometers.info/img/flags/in-flag.gif"));
        countryList.add(new Country("Irish", "https://www.worldometers.info/img/flags/iz-flag.gif"));
        countryList.add(new Country("Italian", "https://www.worldometers.info/img/flags/it-flag.gif"));
        countryList.add(new Country("Jamaican", "https://www.worldometers.info/img/flags/jm-flag.gif"));
        countryList.add(new Country("Japanese", "https://www.worldometers.info/img/flags/ja-flag.gif"));
        countryList.add(new Country("Kenyan", "https://www.worldometers.info/img/flags/ke-flag.gif"));
        countryList.add(new Country("Malaysian", "https://www.worldometers.info/img/flags/my-flag.gif"));
        countryList.add(new Country("Mexican", "https://www.worldometers.info/img/flags/mx-flag.gif"));
        countryList.add(new Country("Moroccan", "https://www.worldometers.info/img/flags/mo-flag.gif"));
        countryList.add(new Country("Polish", "https://www.worldometers.info/img/flags/pl-flag.gif"));
        countryList.add(new Country("Portuguese", "https://www.worldometers.info/img/flags/po-flag.gif"));
        countryList.add(new Country("Russian", "https://www.worldometers.info/img/flags/rs-flag.gif"));
        countryList.add(new Country("Spanish", "https://www.worldometers.info/img/flags/sp-flag.gif"));
        countryList.add(new Country("Thai", "https://www.worldometers.info/img/flags/th-flag.gif"));
        countryList.add(new Country("Tunisian", "https://www.worldometers.info/img/flags/ts-flag.gif"));
        countryList.add(new Country("Turkish", "https://www.worldometers.info/img/flags/tu-flag.gif"));
        countryList.add(new Country("Ukrainian", "https://www.worldometers.info/img/flags/up-flag.gif"));
        countryList.add(new Country("Vietnamese", "https://www.worldometers.info/img/flags/vm-flag.gif"));
    }


    public static CountriesList getInstance(){
        if (instance == null)
            instance = new CountriesList();
        return instance;
    }

    public ArrayList<Country> getcountries() {
        return countryList;
    }

    public void setcountries(ArrayList<Country> meals) {
        this.countryList = countryList;
    }
}
