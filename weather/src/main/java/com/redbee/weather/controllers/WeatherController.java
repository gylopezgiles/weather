package com.redbee.weather.controllers;

import com.redbee.weather.entities.Astronomy;
import com.redbee.weather.entities.Atmosphere;
import com.redbee.weather.entities.Weather;
import com.redbee.weather.providers.ProviderServiceInterface;
import com.redbee.weather.repositories.AstronomyRepository;
import com.redbee.weather.repositories.AtmosphereRepository;
import com.redbee.weather.repositories.ForecastRepository;
import com.redbee.weather.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */
@Controller
public class WeatherController {

    @Autowired
    private ControllersHelper controllersHelper;

    @Autowired
    private ProviderServiceInterface providerServiceInterface;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/weather", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Weather> getWeatherByLocation(@RequestParam String location) {
        System.out.println("Location: " + location);
        Weather weather = controllersHelper.findWeather(location);
        return new ResponseEntity<Weather>(weather, HttpStatus.ACCEPTED);
    }

}
