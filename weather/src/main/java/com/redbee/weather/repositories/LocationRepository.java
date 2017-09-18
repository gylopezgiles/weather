package com.redbee.weather.repositories;

import com.redbee.weather.entities.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */
public interface LocationRepository extends MongoRepository<Location, String> {
    Location findByRegionAndCityIgnoreCase(String region, String city);
}
