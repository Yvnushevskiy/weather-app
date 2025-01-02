package org.example.repositories;

import org.example.model.Session;
import org.example.model.User;

import java.util.UUID;

public interface SessionRepository {
    Session save(Session session);

    void sessionRefresh(Session session);

    Session findSessionById(UUID id);
}
