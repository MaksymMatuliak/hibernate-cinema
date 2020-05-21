package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setMovieSessionId(movieSessionId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movie session entity", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM MovieSession "
                    + "WHERE movie.movieId = :movieId AND time BETWEEN :time AND :time2");
            query.setParameter("movieId", movieId);
            query.setParameter("time", date.atStartOfDay());
            query.setParameter("time2", date.atStartOfDay().plusDays(1));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available movie sessions", e);
        }
    }
}
