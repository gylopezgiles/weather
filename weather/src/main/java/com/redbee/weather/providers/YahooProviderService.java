package com.redbee.weather.providers;

import com.redbee.weather.consumers.RestConsumer;
import com.redbee.weather.entities.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.JSONObject;

/**
 * Created by Gisela Lopez Giles on 9/14/17.
 */
@Service
public class YahooProviderService implements ProviderServiceInterface{

    @Value("${weather.yahooprovider.url}")
    private String url;

    @Value("${weather.yahooprovider.queryparam}")
    private String queryparam;

    @Value("${weather.yahooprovider.query}")
    private String query;

    @Value("${weather.yahooprovider.formatparam}")
    private String formatparam;

    @Value("${weather.yahooprovider.format}")
    private String format;

    @Value("${weather.yahooprovider.envparam}")
    private String envparam;

    @Value("${weather.yahooprovider.env}")
    private String env;

    @Autowired
    private JsonParserService jsonParserService;

    public String generateQuery(String location){
        return query.replace("[location]", location);
    }

    @Override
    public Weather obtainWeather(String location){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(queryparam, generateQuery(location));
        params.add(formatparam, format);
        params.add(envparam, env);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
        String weatherStr = RestConsumer.obtain(builder, HttpMethod.GET, String.class);
        JSONObject weatherJson = new JSONObject(weatherStr);
        return jsonParserService.jsonToWeather(weatherJson);
    }

}
