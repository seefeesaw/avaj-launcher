package com.avaj;

import com.avaj.flyables.AircraftFactory;
import com.avaj.flyables.AircraftType;
import com.avaj.flyables.Flyable;
import com.avaj.weather.WeatherProvider;
import com.avaj.weather.WeatherTower;

import java.math.BigInteger;
import java.security.*;
import java.util.*;

public class Simulator {
    private List<String> scenario;
    private Map<String, AircraftType> availableAircraftHashMap = new HashMap<>();

    Simulator(List<String> scenario) throws NoSuchAlgorithmException {
        for (AircraftType type : AircraftType.values()) {
            availableAircraftHashMap.put(getMD5(type.toString()), type);
        }

        this.scenario = scenario;
    }

    public void Simulation() throws Exception {
        int numberOfSimulation;
        int height, longitude, latitude;

        Flyable flyable;
        WeatherTower weatherTower = new WeatherTower(WeatherProvider.getProvider());

        try {
            numberOfSimulation = Integer.parseInt(scenario.get(0));
            scenario.remove(0);
        } catch (NumberFormatException e) {
            throw new AvajLauncherException("Wrong format of Number of Simulation: " + e.getMessage());
        }

        for (String item : scenario) {
            AircraftType aircraftType;
            String[] result = item.split(" ");
            String rawAircraftType = result[0];

            if (ifMD5(rawAircraftType)) {
                // MD5 case
                aircraftType = availableAircraftHashMap.get(rawAircraftType);
                if (aircraftType == null) {
                    throw new AvajLauncherException("Unknown aircraft type: " + rawAircraftType);
                }
            } else {
                // Plain text case
                try {
                    aircraftType = AircraftType.valueOf(rawAircraftType);
                } catch (IllegalArgumentException e) {
                    throw new AvajLauncherException("Unknown aircraft type: " + rawAircraftType);
                }
            }

            try {
                longitude = Integer.parseInt(result[2]);
                latitude = Integer.parseInt(result[3]);
                height = Integer.parseInt(result[4]);

                if (longitude <= 0 || latitude <= 0 || height <= 0)
                    throw new AvajLauncherException("Coordinates should be are positive numbers");
                flyable = AircraftFactory.newAircraft(
                        aircraftType,
                        result[1],
                        longitude,
                        latitude,
                        height);

                weatherTower.register(flyable);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new AvajLauncherException("Missing value in input file: " + e.getMessage());
            }
            catch (NumberFormatException e) {
                throw new AvajLauncherException("Wrong coordinate type: " + e.getMessage());
            }
        }

        while (numberOfSimulation-- != 0) {
            weatherTower.changeWeather();
        }
    }

    private boolean ifMD5(String input) {
        if (input.length() != 32)
            return false;

        for (char c: input.toCharArray()) {
            if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'f'))
                return false;
        }

        return true;
    }

    private static String getMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        messageDigest.update(input.getBytes());

        byte[] digest = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, digest);
        String hash = bigInteger.toString(16);

        while (hash.length() < 32) {
            hash = "0" + hash;
        }

        return (hash);
    }
}
