package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
@Repository
public class SpecialityDAOImpl extends BaseDAOImpl<Speciality, Long> implements SpecialityDAO {

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByName(String name) {
        return getHibernateTemplate().find("from Speciality s where s.name=?", name);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByShortName(String shortName) {
        return getHibernateTemplate().find("from Speciality s where s.shortName=?", shortName);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByFaculty(Faculty faculty) {
        return getHibernateTemplate().find("from Speciality s where s.faculty=?", faculty);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Speciality> findByFacultyId(Long facultyId) {
        return getHibernateTemplate().find("from Speciality s where s.faculty.id=?", facultyId);
    }
}
