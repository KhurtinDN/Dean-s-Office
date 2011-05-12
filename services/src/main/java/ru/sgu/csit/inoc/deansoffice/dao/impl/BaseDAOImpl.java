package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ru.sgu.csit.inoc.deansoffice.dao.BaseDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 3, 2010
 * Time: 1:06:29 PM
 */
public class BaseDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements BaseDAO<T, ID> {
    @Autowired
    public void initSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public Class getPersistentClass() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected DetachedCriteria createCriteriaForPersistentClass(SimpleExpression... expressions) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());

        for (SimpleExpression expression : expressions) {
            criteria.add(expression);
        }

        return criteria;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ID save(T entity) {
        return (ID) getHibernateTemplate().save(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void update(T transientObject) {
        getHibernateTemplate().update(transientObject);
    }

    @Override
    public void delete(T persistentObject) {
        getHibernateTemplate().delete(persistentObject);
    }

    @Override
    public boolean deleteById(ID id) {
        return getHibernateTemplate().bulkUpdate("delete from " + getPersistentClass().getName() + " s where s.id=?", id) > 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(ID id) {
//        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("id", id));
//        return (T) getHibernateTemplate().findByCriteria(criteria, 0, 1).get(0);
        return (T) getHibernateTemplate().find("from " + getPersistentClass().getName() + " s where s.id=?", id).get(0);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) getHibernateTemplate().find("from " + getPersistentClass().getName());
    }
}
