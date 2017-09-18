package com.redbee.weather.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Gisela Lopez Giles on 9/14/17.
 */
@Document(collection = "wind")
public class Wind{
    @Id
    private String id;
    private int chill;
    private int direction;
    private int speed;

    public Wind(int chill, int direction, int speed) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getChill() {
        return chill;
    }

    public void setChill(int chill) {
        this.chill = chill;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
