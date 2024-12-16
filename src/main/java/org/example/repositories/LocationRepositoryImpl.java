package org.example.repositories;

import org.example.exception.Database.LocationNotFoundException;
import org.example.exception.Database.LocationPersistException;
import org.example.exception.Database.SessionPersistException;
import org.example.model.Location;
import org.example.model.Session;
import org.example.modelDTO.LocationDTO;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Transaction;

public class LocationRepositoryImpl implements LocationRepository {
    public void save(Location location) {
        Transaction tx = null;
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = Hsession.beginTransaction();
            Hsession.save(location);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new LocationNotFoundException(location.getName(), e);
        }
    }

    public void remove(Location location) {
        Transaction tx = null;
        try (org.hibernate.Session Hsession = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx = Hsession.beginTransaction();
            Hsession.remove(location);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new LocationPersistException(location.getName(), e);
        }
    }
}
