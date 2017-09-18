package com.redbee.weather.repositories;

import com.redbee.weather.entities.Location;
import com.redbee.weather.entities.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */
public interface WeatherRepository extends MongoRepository<Weather, String> {
    Weather findByLocationAndDate(Location location, String date);
}
