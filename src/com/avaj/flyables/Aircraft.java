package com.avaj.flyables;

public abstract class Aircraft {
    private AircraftType type;

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    private static long idCounter = 0;

    protected Aircraft(String name, AircraftType type, Coordinates coordinates) {
       this.name = name;
       this.type = type;
       this.coordinates = coordinates;
       this.id = this.nextId();
    }

    private long nextId() {
        return ++(idCounter);
    }

    protected AircraftType getType() {
        return type;
    }

}
