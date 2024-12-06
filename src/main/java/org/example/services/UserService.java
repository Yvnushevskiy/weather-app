package org.example.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.repositories.UserRepository;

import java.util.Objects;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean DoesUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isValidUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent()&&password.equals(user.get().getPassword());
    }

    public boolean isSessionValid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null;
        //TODO
    }

    public void Logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public void addUser(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
    }

}

