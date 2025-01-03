package org.example.repositories;

import org.example.exception.Database.SessionNotFoundException;
import org.example.exception.Database.SessionPersistException;
import org.example.exception.Database.UserPersistException;
import org.example.model.Session;
import org.example.model.User;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.UUID;

public class SessionRepositoryImpl implements SessionRepository {

    public Session save(Session session) {
        Transaction tx = null;
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = Hsession.beginTransaction();
            Hsession.save(session);
            tx.commit();
            return session;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new SessionPersistException(e);
        }

    }

    public void sessionRefresh(Session session) {
        Transaction tx = null;
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = Hsession.beginTransaction();
            Hsession.update(session);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new SessionNotFoundException(e);
        }
    }

    public Session findSessionById(UUID id) {
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            String hql = "from Session where id = :id";
            Query<Session> query = Hsession.createQuery(hql, Session.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e){
            throw new SessionNotFoundException(e);
        }
    }
}
