package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 10:53:02 AM
 */
@Repository
public class FacultyDAOImpl extends BaseDAOImpl<Faculty, Long> implements FacultyDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Faculty> findByShortName(String shortName) {
//        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("shortName", shortName));
//        return (List<Faculty>) getHibernateTemplate().findByCriteria(criteria);
        return (List<Faculty>) getHibernateTemplate().find("from Faculty f where f.shortName=?", shortName);
    }
}
