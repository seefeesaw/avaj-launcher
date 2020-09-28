package com.avaj.flyables;

import com.avaj.weather.WeatherTower;

public interface Flyable {
    void updateConditions() throws Exception;
    void registerTower(WeatherTower weatherTower);
}
