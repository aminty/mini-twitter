package twitter.repository.impl;

import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.User;
import twitter.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }


    @Override
    public boolean isUserExistsByUsername(String username) {
        TypedQuery<Long> query = em.createQuery("select count( u ) from " + getEntityClass().getSimpleName() + " u where u.username= :username", Long.class);
        query.setParameter("username", username);
        Long count = query.getSingleResult();
        return count > 0;

    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        TypedQuery<Long> query = em.createQuery("select count( u ) from " + getEntityClass().getSimpleName() + " u where u.email= :email", Long.class);
        query.setParameter("email", email);
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        TypedQuery<User> query =
                em.createQuery("from " + getEntityClass().getSimpleName() + " u where u.username= :username "
                        , getEntityClass());

        query.setParameter("username", username);
        try {
           User foundUser = query.getSingleResult();
            return Optional.ofNullable(foundUser);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        TypedQuery<User> query =
                em.createQuery("from " + getEntityClass().getSimpleName() + " u where u.email= :email "
                        , getEntityClass());

        query.setParameter("email", email);
        try {
            User foundUser = query.getSingleResult();
            return Optional.ofNullable(foundUser);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
