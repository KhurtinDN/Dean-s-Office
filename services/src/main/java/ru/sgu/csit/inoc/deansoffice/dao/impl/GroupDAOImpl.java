package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
@Repository
public class GroupDAOImpl extends BaseDAOImpl<Group, Long> implements GroupDAO {

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findBySpecialityId(Long specialityId) {
        return (List<Group>) getHibernateTemplate().find("from Group g where g.speciality.id=?", specialityId);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findByCourseAndSpeciality(Integer course, Speciality speciality) {
        return (List<Group>) getHibernateTemplate().find("from Group g where g.course=? and g.speciality=?",
                course, speciality);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findByCourseAndSpecialityId(Integer course, Long specialityId) {
        return (List<Group>) getHibernateTemplate().find("from Group g where g.course=? and g.speciality.id=?",
                course, specialityId);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Group> findByName(String name) {
        return (List<Group>) getHibernateTemplate().find("from Group g where g.name=?", name);
    }
}
