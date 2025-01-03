package org.example.repositories;

import org.example.exception.Database.LocationNotFoundException;
import org.example.exception.Database.LocationPersistException;
import org.example.exception.Database.SessionPersistException;
import org.example.model.Location;
import org.example.model.User;
import org.example.modelDTO.LocationDTO;
import org.example.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
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
    public Optional<Integer> findLocationIDByUsernameAndLocationName(String username, String name) {
        try (org.hibernate.Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            // Создаем запрос HQL для поиска локации по имени пользователя и имени локации
            String hql = "SELECT l.id FROM Location l WHERE l.user.login = :username AND l.name = :name";
            Query<Integer> query = session.createQuery(hql, Integer.class);

            // Устанавливаем параметры для запроса
            query.setParameter("username", username);
            query.setParameter("name", name);

            // Получаем ID локации (или null, если не найдено)
            Integer locationId = query.uniqueResult();

            // Возвращаем результат в Optional
            return Optional.ofNullable(locationId);
        } catch (Exception e) {
            // Логирование или обработка исключений
            throw new RuntimeException("Failed to fetch location ID for username: " + username + " and name: " + name, e);
        }
    }


    public Optional<Location> findLocationByLocationID(int id) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            // Создаем запрос HQL для поиска локации по ID
            String hql = "FROM Location WHERE id = :id";
            Query<Location> query = session.createQuery(hql, Location.class);
            query.setParameter("id", id);

            // Получаем уникальную локацию
            Location location = query.uniqueResult(); // Вернет единственную локацию или null

            return Optional.ofNullable(location);
        } catch (Exception e) {
            // Логируем или обрабатываем исключение, если необходимо
            throw new RuntimeException("Failed to fetch location with ID: " + id, e);
        }
    }


}
