package org.example.repositories;
import org.example.model.User;

public interface SessionRepository {
    void addSession(User user, String session);
    void refreshSession(User user , String session);
}
