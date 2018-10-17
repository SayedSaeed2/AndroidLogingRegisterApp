package com.example.android.androidlogingregisterapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Country POJO class for sending and receiving requests that coming from the Server
 */
public class Country {

    @SerializedName("name")
    private String name;
    @SerializedName("topLevelDomain")
    @Expose
    private List<String> topLevelDomain = null;

    @SerializedName("capital")
    private String capital;

    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }


    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return name;
    }

}
