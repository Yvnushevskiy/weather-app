package org.example.exception.Database;

public class LocationPersistException extends RuntimeException {
    public LocationPersistException(String location, Exception cause) {
        super("Cannot save location: " + location + cause.getMessage() , cause);
    }
}
