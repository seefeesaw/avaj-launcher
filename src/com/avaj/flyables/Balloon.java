package com.avaj.flyables;

import com.avaj.weather.WeatherTower;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, AircraftType.Balloon, coordinates);
    }

    @Override
    public void updateConditions() throws Exception {
        switch (weatherTower.getWeather(this.coordinates)) {
            case "SUN":
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "):  Let's enjoy the good weather and take some pics.");
                coordinates.changeLongitude(2);
                coordinates.changeHeight(4);
                break;
            case "RAIN":
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "): Damn you rain! You messed up my baloon.");
                coordinates.changeHeight(-5);
                break;
            case "FOG":
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "): Oh, no! I'm going to get lost in this fluffy fog!");
                coordinates.changeHeight(-3);
                break;
            default:
                System.out.println(this.getType() + "#" + this.name + "(" + this.id + "):  It's snowing. We're gonna crash.");
                coordinates.changeHeight(-15);
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
