package twitter.base.service;

import lombok.AllArgsConstructor;
import twitter.base.domain.BaseEntity;
import twitter.base.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;


@AllArgsConstructor
public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable, R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {

    protected final R repository;


    @Override
    public T saveOrUpdate(T entity) {
        return repository.saveOrUpdate(entity);
    }


    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public long getCount() {
        return repository.getCount();
    }

    @Override
    public Collection<T> saveAll(Collection<T> entityCollection) {
        return repository.saveAll(entityCollection);
    }

    @Override
    public void beginTransaction() {
        repository.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        repository.commitTransaction();
    }

    @Override
    public void rollBack() {
        repository.rollBack();
    }
}
