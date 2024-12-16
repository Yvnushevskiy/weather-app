package org.example.repositories;

import org.example.model.Session;
import org.example.model.User;

public interface SessionRepository {
    Session save(Session session);

    void sessionRefresh(Session session);
}
