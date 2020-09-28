package com.avaj.flyables;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;
    
    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }
    
    public int getLongitude() {
        return this.longitude;
    }
    
    public int getLatitude() {
        return this.latitude;
    }
    
    public int getHeight() {
        return this.height;
    }

    private boolean checkTooLow(int height) {
        return height <= 0;
    }

    private boolean checkTooHigh(int height) {
        return height >= 100;
    }

    public void changeLongitude(int longitude) {
        this.longitude += longitude;
    }

    public void changeLatitude(int latitude) {
        this.latitude += latitude;
    }

    public void changeHeight(int height) {
        if (checkTooLow(this.height + height)) {
            this.height = 0;
        } else if (checkTooHigh(this.height + height)) {
            this.height = 100;
        }
        else
            this.height += height;
    }

}
