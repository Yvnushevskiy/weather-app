package org.example.exception.Database;

public class UserPersistException extends RuntimeException {
    public UserPersistException(String username, Throwable cause) {
        super("Cannot save user:" + username + cause.getMessage() , cause);
    }
}
