package org.example.repositories;

import org.example.exception.Database.UserPersistException;
import org.example.exception.Database.UserNotFoundException;
import org.example.model.User;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {


    public Optional<User> findByUsername(String username) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession())
        {
            // Создаем запрос HQL
            String hql = "FROM User WHERE login = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult(); // Вернет единственного пользователя или null
            return Optional.ofNullable(user);
        }
    }


    public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new UserPersistException(user.getLogin(), e);
        }

    }

}
