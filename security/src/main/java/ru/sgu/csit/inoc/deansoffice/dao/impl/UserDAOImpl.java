package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.UserDAO;
import ru.sgu.csit.inoc.deansoffice.domain.User;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Repository
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    @Autowired
    public void initSessionFactory(final SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUsername(final String username) {
        final List result = getHibernateTemplate().find("from User u where u.username=?", username);

        return (User) (result.size() == 1 ? result.get(0) : null);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveOrUpdate(final User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(final User user) {
        getHibernateTemplate().delete(user);
    }
}
