package com.redbee.weather.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gisela Lopez Giles on 9/15/17.
 */
@Document(collection = "astronomy")
public class Astronomy{
    @Id
    private String id;
    private String sunrise;
    private String sunset;

    public Astronomy(String sunrise, String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
