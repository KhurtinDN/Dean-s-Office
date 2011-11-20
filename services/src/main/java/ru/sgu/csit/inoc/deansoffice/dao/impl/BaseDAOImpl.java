package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.BaseDAO;
import ru.sgu.csit.inoc.deansoffice.domain.PersistentItem;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * The implementation of generic base DAO superclass for other DAO classes.
 *
 * @author Alexander Mesheryakov, Denis Khurtin
 */
public class BaseDAOImpl<T extends PersistentItem, ID extends Serializable> extends HibernateDaoSupport implements BaseDAO<T, ID> {
    @Autowired
    public void initSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public Class getPersistentClass() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public T findById(ID id) {
        return (T) getHibernateTemplate().get(getPersistentClass(), id);

    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getHibernateTemplate().loadAll(getPersistentClass());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    @SuppressWarnings("unchecked")
    public ID save(T entity) {
        return (ID) getHibernateTemplate().save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(T transientObject) {
        getHibernateTemplate().update(transientObject);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(T persistentObject) {
        getHibernateTemplate().delete(persistentObject);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteById(ID id) {
        return getHibernateTemplate().bulkUpdate("delete from " + getPersistentClass().getName() + " s where s.id=?", id) > 0;
    }
}
