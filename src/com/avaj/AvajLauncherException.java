package com.avaj;

public class AvajLauncherException extends Exception {
    private String errorMessage;

    public AvajLauncherException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
