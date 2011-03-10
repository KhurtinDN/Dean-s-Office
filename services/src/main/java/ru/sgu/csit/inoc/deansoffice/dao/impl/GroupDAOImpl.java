package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 10:50:53 AM
 */
@Repository
public class GroupDAOImpl extends BaseDAOImpl<Group, Long> implements GroupDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findByCourseAndSpeciality(Integer course, Speciality speciality) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(
                Restrictions.eq("course", course), Restrictions.eq("speciality", speciality));
        return (List<Group>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findByCourseAndSpecialityId(Integer course, Long specialityId) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(
                Restrictions.eq("course", course), Restrictions.eq("speciality.id", specialityId));
        return (List<Group>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findByName(String name) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("name", name));
        return (List<Group>) getHibernateTemplate().findByCriteria(criteria);
    }
}
