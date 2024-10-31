package org.example.exception.Database;

public class LocationPersistException extends RuntimeException {
    public LocationPersistException(String location,Throwable cause) {
        super("Cannot save location: " + location +cause.getMessage()+cause);
    }
}
