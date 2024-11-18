
package org.example.exception.Database;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username, Throwable cause) {
        super("User" + username + " not found" + cause.getMessage(), cause);
    }
}
