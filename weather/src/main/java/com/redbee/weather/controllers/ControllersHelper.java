package com.redbee.weather.controllers;

import com.redbee.weather.entities.Location;
import com.redbee.weather.entities.Weather;
import com.redbee.weather.providers.ProviderServiceInterface;
import com.redbee.weather.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Gisela Lopez Giles on 9/17/17.
 */
@Service
public class ControllersHelper {

    @Autowired
    private WeatherRepository weatherRepo;

    @Autowired
    private ProviderServiceInterface providerServiceInterface;

    @Autowired
    private AstronomyRepository astronomyRepo;

    @Autowired
    private AtmosphereRepository atmosphereRepo;

    @Autowired
    private ForecastRepository forecastRepo;

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private WindRepository windRepo;

    public Weather findWeather(String locationStr) {
        String[] locationArr = splitLocation(locationStr);
        Location location = locationRepo.findByRegionAndCityIgnoreCase(locationArr[1].toUpperCase(), locationArr[0].toUpperCase());
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Weather weather = weatherRepo.findByLocationAndDate(location, localDate.format(formatter));
        if (weather == null){
            weather = getWeatherByProvider(locationStr);
        }
        return weather;
    }

    private String[] splitLocation(String locationStr){
        String[] location = locationStr.split(",");
        return location;
    }

    private Weather getWeatherByProvider(String locationStr){
        Weather weather = providerServiceInterface.obtainWeather(locationStr);
        astronomyRepo.save(weather.getAstronomy());
        atmosphereRepo.save(weather.getAtmosphere());
        windRepo.save(weather.getWind());
        Location location = locationRepo.findByRegionAndCityIgnoreCase(weather.getLocation().getRegion(), weather.getLocation().getCity());
        if(location == null){
            locationRepo.save(weather.getLocation());
        }
        forecastRepo.save(weather.getForecasts());
        weatherRepo.save(weather);
        return weather;
    }
}
