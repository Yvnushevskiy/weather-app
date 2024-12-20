package org.example.repositories;

import org.example.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    void save(User user);
}
