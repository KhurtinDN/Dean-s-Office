package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 10:51:57 AM
 */
@Repository
public class SpecialityDAOImpl extends BaseDAOImpl<Speciality, Long> implements SpecialityDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByShortName(String shortName) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("shortName", shortName));
        return (List<Speciality>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByFaculty(Faculty faculty) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("faculty", faculty));
        return (List<Speciality>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByFacultyId(Long facultyId) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("faculty.id", facultyId));
        return (List<Speciality>) getHibernateTemplate().findByCriteria(criteria);
    }
}
