package twitter.base.repository;

import com.sun.xml.bind.v2.model.core.ID;
import twitter.base.domain.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseEntity<ID>,ID extends Serializable> {

    T saveOrUpdate(T entity);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    Collection<T> findAll();

    long getCount();

    Collection<T> saveAll(Collection<T> entityCollection);

    void beginTransaction();

    void commitTransaction();

    void rollBack();



}
