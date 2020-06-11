package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie session", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM MovieSession "
                    + "WHERE movie.movieId = :movieId AND time BETWEEN :startDay AND :endDay");
            query.setParameter("movieId", movieId);
            query.setParameter("startDay", date.atStartOfDay());
            query.setParameter("endDay", date.atStartOfDay().plusDays(1));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available movie sessions", e);
        }
    }

    @Override
    public Optional<MovieSession> getById(Long movieSessionId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "FROM MovieSession WHERE movieSessionId= :movieSessionId");
            query.setParameter("movieSessionId", movieSessionId);
            return Optional.ofNullable((MovieSession) query.uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie session", e);
        }
    }
}
