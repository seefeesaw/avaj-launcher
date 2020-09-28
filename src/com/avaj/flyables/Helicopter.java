package com.avaj.flyables;

import com.avaj.weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, AircraftType.Helicopter, coordinates);
    }

    @Override
    public void updateConditions() throws Exception {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "): This is hot.");
                coordinates.changeLongitude(10);
                coordinates.changeHeight(2);
                break;
            case "RAIN":
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "): It's rain. I'm so scare!");
                coordinates.changeLongitude(5);
                break;
            case "FOG":
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "): Fooooggggiiii!");
                coordinates.changeLongitude(1);
                break;
            default:
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "): My rotor is going to freeze!");
                coordinates.changeHeight(-12);
                break;
        }

        if (coordinates.getHeight() == 0) {
            weatherTower.unregister(this);
            System.out.println(this.getType() + "#" + this.name + "(" + this.id + ") landing");
            System.out.println("Tower says: " + this.getType() + "#" + this.name + "(" + this.id + ") unregistered from weather tower.");
        }

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        System.out.println("Tower says: " + this.getType() + "#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
