package org.example.repositories;
import org.example.exception.Database.UserPersistException;
import org.example.exception.Database.UserNotFoundException;
import org.example.model.User;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserRepositoryImpl implements UserRepository {



    public User findByUsername(String username) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(User.class, username);
        } catch (Exception e) {
            throw new UserNotFoundException(username,e);
                    
        }
    }

    public void save(User user) {
        Transaction tx = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx  = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            throw new UserPersistException(user.getLogin(),e);
        }

    }

}
