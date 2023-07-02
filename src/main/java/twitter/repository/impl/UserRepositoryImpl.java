package twitter.repository.impl;

import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.User;
import twitter.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public List<User> findUser(String title) {
        CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        Predicate firstNamePredicate = criteriaBuilder.like(from.get("firstname"), "%" + title + "%"); // Create a predicate for matching firstName
        Predicate lastNamePredicate = criteriaBuilder.like(from.get("lastname"), "%" + title + "%"); // Create a predicate for matching lastName
        Predicate usernamePredicate = criteriaBuilder.like(from.get("username"), "%" + title + "%"); // Create a predicate for matching u
        Predicate finalPredicate    = criteriaBuilder.or(firstNamePredicate,lastNamePredicate,usernamePredicate);
        criteriaQuery.select(from).where(finalPredicate);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
