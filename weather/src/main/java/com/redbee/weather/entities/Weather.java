package com.redbee.weather.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */

@Document(collection = "weather")
public class Weather {
    @Id
    private String id;
    private Location location;
    private Wind wind;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private List<Forecast> forecasts;
    private String date;

    public Weather(Location location, Wind wind, Astronomy astronomy, Atmosphere atmosphere, List<Forecast> forecasts, String date) {
        this.location = location;
        this.wind = wind;
        this.astronomy = astronomy;
        this.atmosphere = atmosphere;
        this.forecasts = forecasts;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
