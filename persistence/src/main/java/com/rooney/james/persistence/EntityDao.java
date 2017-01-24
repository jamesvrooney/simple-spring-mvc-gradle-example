package com.rooney.james.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface EntityDao<T extends Serializable, PK extends Serializable> {

    Class<T> getEntityType();

    /**
     * Gets an entity by its primary key.
     * @param <T> entity type
     * @param id primary key
     * @return
     */
    T getById(PK id);

    T lockById(PK id);

    List<T> getBulk(Collection<PK> ids);

    List<T> getBulk(Collection<PK> ids, String[] loadedProperties);

    List<T> getOrderedBulk(Collection<PK> ids, boolean asc);

    List<T> getAll();

    List<PK> getAllIds();


    long count();

    T load(PK id);

    void save(T entity);

    void saveBulk(Collection<T> entities);

    void update(T entity);

    void updateBulk(Collection<T> entities);

    void saveOrUpdate(T entity);

    void saveOrUpdateBulk(Collection<T> entities);

    void delete(T entity);

    void deleteBulk(Collection<T> entities);

    void flush();

    void clear();

    void refresh(T entity);

    void merge(T entity);

    void evict(T entity);

    List<T> findByExample(T entity);

}
