package com.avaj.weather;

import com.avaj.flyables.Coordinates;
import com.avaj.flyables.Flyable;

public class WeatherTower extends Tower {
    private WeatherProvider weatherProvider;

    public WeatherTower(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public String getWeather(Coordinates coordinates) {
        return weatherProvider.getCurrentWeather(coordinates);
    }

    public void changeWeather() throws Exception {
        super.conditionsChanged();
    }

    @Override
    protected void registerSpecificTower(Flyable flyable) {
        flyable.registerTower(this);
    }
}
