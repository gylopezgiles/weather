package com.redbee.weather.providers;

import com.redbee.weather.entities.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gisela Lopez Giles on 9/17/17.
 */
@Service
public class JsonParserService {

    public Weather jsonToWeather(JSONObject response){
        JSONObject jsonBlock = response.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("location");
        Location location = jsonToLocation(jsonBlock);
        jsonBlock = response.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("wind");
        Wind wind = jsonToWind(jsonBlock);
        jsonBlock = response.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("atmosphere");
        Atmosphere atmosphere = jsonToAtmosphere(jsonBlock);
        jsonBlock = response.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy");
        Astronomy astronomy = jsonToAstronomy(jsonBlock);
        JSONArray jsonArray = response.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
        List<Forecast> forecasts= jsonToForecastList(jsonArray);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Weather(location, wind, astronomy, atmosphere, forecasts, localDate.format(formatter));
    }

    private Location jsonToLocation (JSONObject jsonBlock){
        String city = jsonBlock.getString("city").toUpperCase();
        String country = jsonBlock.getString("country").toUpperCase();
        String region = jsonBlock.getString("region").replace(" ", "").toUpperCase();
        return new Location(country, region, city);
    }

    private Wind jsonToWind(JSONObject jsonBlock) {
        int chill = jsonBlock.getInt("chill");
        int direction = jsonBlock.getInt("direction");
        int speed = jsonBlock.getInt("speed");
        return new Wind(chill, direction, speed);
    }

    private Atmosphere jsonToAtmosphere(JSONObject jsonBlock) {
        int humidity = jsonBlock.getInt("humidity");
        double pressure = jsonBlock.getDouble("pressure");
        int rising = jsonBlock.getInt("rising");
        double visibility = jsonBlock.getDouble("visibility");
        return new Atmosphere(humidity, pressure, rising, visibility);
    }

    private Astronomy jsonToAstronomy(JSONObject jsonBlock) {
        String sunrise = jsonBlock.getString("sunrise");
        String sunset = jsonBlock.getString("sunset");
        return new Astronomy(sunrise, sunset);
    }

    private List<Forecast> jsonToForecastList(JSONArray jsonBlock){
        List<Forecast> forecasts = new ArrayList<>();
        for(int i = 0 ; i < jsonBlock.length() ; i++){
            forecasts.add(jsonToForecast(jsonBlock.getJSONObject(i)));
        }
        return forecasts;
    }

    private Forecast jsonToForecast(JSONObject jsonObject){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        int code = jsonObject.getInt("code");
        try {
            date = formatter.parse(jsonObject.getString("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = jsonObject.getString("day");
        int high = jsonObject.getInt("high");
        int low = jsonObject.getInt("low");
        String text = jsonObject.getString("text");
        return new Forecast(code, date, day, high, low, text);
    }
}
