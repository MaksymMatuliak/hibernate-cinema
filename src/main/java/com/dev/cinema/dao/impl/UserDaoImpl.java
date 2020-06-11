package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;
    private final HashUtil hashUtil;

    public UserDaoImpl(SessionFactory sessionFactory, HashUtil hashUtil) {
        this.sessionFactory = sessionFactory;
        this.hashUtil = hashUtil;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            byte[] salt = hashUtil.getSalt();
            user.setSalt(salt);
            user.setPassword(hashUtil.hashPassword(user.getPassword(), salt));
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM User WHERE email = :email");
            query.setParameter("email", email);
            return Optional.ofNullable((User) query.uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user by email", e);
        }
    }

    @Override
    public Optional<User> getById(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM User WHERE userId = :userId");
            query.setParameter("userId", userId);
            return Optional.ofNullable((User) query.uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user by id", e);
        }
    }
}
