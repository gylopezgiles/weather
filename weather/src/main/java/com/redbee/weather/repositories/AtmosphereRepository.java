package com.redbee.weather.repositories;

import com.redbee.weather.entities.Atmosphere;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */
public interface AtmosphereRepository extends MongoRepository<Atmosphere, String> {
}
