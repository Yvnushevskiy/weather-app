package org.example.exception.Database;

public class SessionPersistException extends RuntimeException {
    public SessionPersistException(Throwable cause) {
        super("Cannot save session" + cause.getMessage() + cause);
    }
}

