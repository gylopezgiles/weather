package com.redbee.weather.providers;

import com.redbee.weather.entities.Weather;
import org.springframework.stereotype.Service;

/**
 * Created by Gisela Lopez Giles on 9/15/17.
 */
@Service
public interface ProviderServiceInterface {
    Weather obtainWeather(String location);
}
