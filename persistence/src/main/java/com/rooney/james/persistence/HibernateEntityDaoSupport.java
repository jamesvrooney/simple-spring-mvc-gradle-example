package com.rooney.james.persistence;

import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Repository
@SuppressWarnings("unchecked")
public abstract class HibernateEntityDaoSupport<T extends Serializable, PK extends Serializable> implements EntityDao<T, PK> {

    protected Class<T> entityType;

    private SessionFactory sessionFactory;

    protected HibernateEntityDaoSupport(SessionFactory sessionFactory, Class<T> entityType) {
        this.sessionFactory = sessionFactory;
        this.entityType = entityType;
    }

    @Override
    public Class<T> getEntityType() {
        return entityType;
    }

    @Override
    public T getById(PK id) {
        return (T) currentSession().get(entityType, id);
    }

    @Override
    public T lockById(PK id) {
        return (T) currentSession().get(entityType, id, LockOptions.UPGRADE);
    }

    @Override
    public List<T> getAll() {
        return defaultCriteria().list();
    }

    @Override
    public List<PK> getAllIds() {
        return defaultCriteria().setProjection(Projections.id()).list();
    }

    @Override
    public List<T> getBulk(Collection<PK> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.<T>emptyList();
        }
        return defaultCriteria().add(Restrictions.in(Projections.id().toString(), ids)).list();
    }

    @Override
    public List<T> getBulk(Collection<PK> ids, String[] loadedProperties) {
        if (ids == null || ids.isEmpty()) {
            return Collections.<T>emptyList();
        }

        Criteria criteria = defaultCriteria()
                .add(Restrictions.in(Projections.id().toString(), ids));

        for (String property : loadedProperties){
            criteria.setFetchMode(property, FetchMode.JOIN);
        }

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }
    @Override
    public List<T> getOrderedBulk(Collection<PK> ids, boolean asc) {
        if (ids == null || ids.isEmpty()) {
            return Collections.<T>emptyList();
        }
        String idName = Projections.id().toString();
        return defaultCriteria().add(Restrictions.in(idName, ids)).addOrder(asc ? Order.asc(idName) : Order.desc(idName)).list();
    }

    @Override
    public long count() {
        Criteria criteria = defaultCriteria().setProjection(Projections.count("id"));
        return (Long)criteria.list().get(0);
    }

    @Override
    public T load(PK id) {
        return (T) currentSession().load(entityType, id);
    }

    @Override
    public void save(T entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        currentSession().update(entity);

    }

    @Override
    public void saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    @Override
    public void clear() {
        currentSession().clear();
    }

    @Override
    public void flush() {
        currentSession().flush();
    }

    @Override
    public void refresh(T entity) {
        currentSession().refresh(entity);
    }

    @Override
    public void merge(T entity) {
        currentSession().merge(entity);
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria defaultCriteria() {
        return currentSession().createCriteria(entityType);
    }

    protected Criteria defaultCriteria(String alias) {
        return currentSession().createCriteria(entityType, alias);
    }


    @Override
    public void saveBulk(Collection<T> entities) {
        Session session = currentSession();
        for(T entity : entities) {
            session.save(entity);
        }
    }


    @Override
    public void updateBulk(Collection<T> entities) {
        Session session = currentSession();
        for(T entity : entities) {
            session.update(entity);
        }
    }


    @Override
    public void saveOrUpdateBulk(Collection<T> entities) {
        Session session = currentSession();
        for(T entity : entities) {
            session.saveOrUpdate(entity);
        }
    }


    @Override
    public void deleteBulk(Collection<T> entities) {
        Session session = currentSession();
        for(T entity : entities) {
            session.delete(entity);
        }
    }

    @Override
    public void evict(T entity) {
        currentSession().evict(entity);
    }

    @Override
    public List<T> findByExample(T entity) {
        return defaultCriteria().add(Example.create(entity)).list();
    }
}
