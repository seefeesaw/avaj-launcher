package com.avaj.flyables;

import com.avaj.AvajLauncherException;

public abstract class AircraftFactory {
    public static Flyable newAircraft(AircraftType type, String name, int longitude, int latitude, int height) throws Exception {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

            switch (type) {
                case Helicopter:
                    return new Helicopter(name, coordinates);
                case JetPlane:
                    return new JetPlane(name, coordinates);
                case Balloon:
                    return new Balloon(name, coordinates);
            }

        throw new AvajLauncherException("Unknown aircraft type: " + type);
    }
}
