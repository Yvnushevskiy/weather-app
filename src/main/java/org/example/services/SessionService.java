package org.example.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.model.Session;
import org.example.model.User;
import org.example.repositories.SessionRepository;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRepositoryImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;


@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public Session save(Session session) {
        return sessionRepository.save(session);
    }


//    public void addUUIDtoUser(String username) {
//        User user = userService.getByUsername(username);
//        Session session = new Session();
//        session.setUser(user);
//        session.setExpiresAt(Date.valueOf(LocalDate.now()));
//        sessionRepository.save(session);
//    }

    public UUID getUUIDbyUsername(String username) {

        return null;
    }

}

