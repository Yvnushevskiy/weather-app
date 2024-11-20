package org.example.exception.Database;

public class UserPersistException extends RuntimeException {
    public UserPersistException(String username, Exception cause) {
        super("Cannot save user:" + username + cause.getMessage() , cause);
    }
}
