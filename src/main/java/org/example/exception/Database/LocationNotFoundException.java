package org.example.exception.Database;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String location, Throwable cause) {
        super("Location"+location + " not found"+ cause.getMessage()+cause);
    }
}
