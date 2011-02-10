package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 10:35:52 AM
 */
@Repository
public class StudentDAOImpl extends BaseDAOImpl<Student, Long> implements StudentDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByGroup(Group group) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("group", group));
        return (List<Student>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByGroupId(Long groupId) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(Restrictions.eq("group.id", groupId));
        return (List<Student>) getHibernateTemplate().findByCriteria(criteria);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public List<Student> findBySpecialityIdAndCourse(Long specialityId, Integer course) {
        DetachedCriteria criteria = createCriteriaForPersistentClass(
                Restrictions.eq("course", course), Restrictions.eq("speciality.id", specialityId));
        return (List<Student>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void initialize(Object proxy) {
        getHibernateTemplate().initialize(proxy);
    }
}
