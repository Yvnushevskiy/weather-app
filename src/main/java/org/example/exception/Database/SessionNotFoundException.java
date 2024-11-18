package org.example.exception.Database;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(Throwable cause) {
        super("Session not found" + cause.getMessage() ,cause);
    }
}
