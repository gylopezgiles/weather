package com.redbee.weather.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */
@Document(collection = "location")
public class Location {
    @Id
    private String id;
    private String country;
    private String region;
    private String city;

    public Location(String country, String region, String city) {
        this.country = country;
        this.region = region;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
