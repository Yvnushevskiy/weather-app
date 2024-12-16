package org.example.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.exception.Database.UserNotFoundException;
import org.example.model.Session;
import org.example.model.User;
import org.example.repositories.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SessionService sessionService;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public boolean DoesUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isValidUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent()&&password.equals(user.get().getPassword());
    }
    //TODO method login get username+password and respond with UUID its bullshit
    public UUID login(String username, String password) {
        isValidUser(username, password);
        User user = getByUsername(username);
        Session session = new Session();
        session.setUser(user);
        session.setExpiresAt(Date.valueOf(LocalDate.now()));
        sessionService.save(session);
        return session.getId();
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
