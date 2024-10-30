package org.example.repositories;
import org.example.model.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
