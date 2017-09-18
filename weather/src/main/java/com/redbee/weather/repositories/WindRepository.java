package com.redbee.weather.repositories;

import com.redbee.weather.entities.Wind;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Gisela Lopez Giles on 9/13/17.
 */
public interface WindRepository extends MongoRepository<Wind, String> {

}
