package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ru.sgu.csit.inoc.deansoffice.dao.BaseDAO;

import java.io.Serializable;
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

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public List<T> findAll(Class<T> aClass) {
        return (List<T>)getHibernateTemplate().find("from " + aClass.getName());
    }
}
