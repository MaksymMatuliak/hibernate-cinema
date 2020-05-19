package com.dev.cinema.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
  private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

  public Movie add(Movie movie) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Long itemId = (Long) session.save(movie);
      transaction.commit();
      movie.setMovie_id(itemId);
      return movie;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new RuntimeException("Can't insert movie entity", e);
    }
  }

  public List<Movie> getAll() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder().createQuery(Movie.class);
      criteriaQuery.from(Movie.class);
      return session.createQuery(criteriaQuery).getResultList();
    } catch (Exception e) {
      throw new DataProcessingException("Can't get movies");
    }
  }
}
