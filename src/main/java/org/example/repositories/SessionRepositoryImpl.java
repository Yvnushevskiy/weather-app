package org.example.repositories;

import org.example.exception.Database.SessionNotFoundException;
import org.example.exception.Database.SessionPersistException;
import org.example.exception.Database.UserPersistException;
import org.example.model.Session;
import org.example.model.User;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Transaction;

public class SessionRepositoryImpl {
    public void save(Session session) {
        Transaction tx = null;
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = Hsession.beginTransaction();
            Hsession.save(session);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new SessionPersistException(e);
        }
    }

    public void SessionRefresh(Session session){
        Transaction tx = null;
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = Hsession.beginTransaction();
            Hsession.update(session);
            tx.commit();
        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
            throw new SessionNotFoundException(e);
        }
    }
}
