package twitter.base.repository;

import lombok.AllArgsConstructor;
import twitter.base.domain.BaseEntity;
import twitter.exception.NotFoundException;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public abstract class BaseRepositoryImpl
        <T extends BaseEntity<ID>, ID extends Serializable> implements BaseRepository<T, ID> {

    protected  EntityManager em;

    @Override
    public T saveOrUpdate(T entity) {
        beginTransaction();
        entity = saveWithoutTransaction(entity);
        commitTransaction();
        em.clear();
        return entity;
    }

    public T saveWithoutTransaction(T entity) {
        if (entity.getId() == null)
            em.persist(entity);
         else
            entity = em.merge(entity);

        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable( em.find(getEntityClass(),id));
    }

    @Override
    public void deleteById(ID id) {
        beginTransaction();
        em.remove(id);
        commitTransaction();
    }

    @Override
    public Collection<T> findAll() {
        return em.createQuery("from "+ getEntityClass().getSimpleName(),getEntityClass()).getResultList();
    }

    @Override
    public long getCount() {
        return
                em.createQuery("select count( e ) from "+getEntityClass().getSimpleName()+" e ",Long.class)
                        .getSingleResult();
    }

    @Override
    public Collection<T> saveAll(Collection<T> entityCollection) {
        beginTransaction();
        List<T> savedEntity=new ArrayList<>();
        entityCollection.forEach(e->savedEntity.add(saveWithoutTransaction(e)));
        commitTransaction();
        return savedEntity;
    }

    @Override
    public void beginTransaction() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();

    }

    @Override
    public void commitTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }



    }

    @Override
    public void rollBack() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();

    }

    public abstract Class<T> getEntityClass();
}
