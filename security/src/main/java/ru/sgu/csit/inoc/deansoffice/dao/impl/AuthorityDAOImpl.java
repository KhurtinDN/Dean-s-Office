package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.AuthorityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Authority;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Repository
public class AuthorityDAOImpl extends HibernateDaoSupport implements AuthorityDAO {

    @Autowired
    public void initSessionFactory(final SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Authority> loadAll() {
        return getHibernateTemplate().loadAll(Authority.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(final Authority authority) {
        getHibernateTemplate().save(authority);
    }
}
