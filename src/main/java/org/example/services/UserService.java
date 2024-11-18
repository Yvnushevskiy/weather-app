package org.example.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValidUser(String username, String password) {
        return false; //TODO
    }

    public boolean isSessionValid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null;
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

