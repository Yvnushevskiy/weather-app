package org.example.repositories;

import org.example.exception.Database.LocationNotFoundException;
import org.example.exception.Database.LocationPersistException;
import org.example.exception.Database.SessionPersistException;
import org.example.model.Location;
import org.example.model.Session;
import org.example.modelDTO.LocationDTO;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<List<Location>> findAllLocationsByUsername(String username) {
        try (org.hibernate.Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "FROM Location l WHERE l.user.login = :username";
            Query<Location> query = session.createQuery(hql, Location.class);
            query.setParameter("username", username);

            List<Location> locations = query.list();


            return Optional.ofNullable(locations.isEmpty() ? null : locations);
        } catch (Exception e) {

            throw new RuntimeException("Failed to fetch locations for username: " + username, e);
        }

    }
    public Optional<Integer> findLocationIDByUsernameAndLocation(String username, Location location) {
        try (org.hibernate.Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            // Создаем запрос HQL для поиска локации по имени пользователя и координатам
            String hql = "SELECT l.id FROM Location l WHERE l.user.login = :username " +
                    "AND l.lat = :lat AND l.lon = :lon";
            Query<Integer> query = session.createQuery(hql, Integer.class);

            // Устанавливаем параметры для запроса
            query.setParameter("username", username);
            query.setParameter("lat", location.getLat());
            query.setParameter("lon", location.getLon());

            // Получаем ID локации (или null, если не найдено)
            Integer locationId = query.uniqueResult();

            // Возвращаем результат в Optional
            return Optional.ofNullable(locationId);
        } catch (Exception e) {
            // Логирование или обработка исключений
            throw new RuntimeException("Failed to fetch location ID for username: " + username, e);
        }
    }


}
