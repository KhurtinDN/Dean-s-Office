package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
@Repository
public class StudentDAOImpl extends BaseDAOImpl<Student, Long> implements StudentDAO {

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByGroup(Group group) { // http://forum.springsource.org/showthread.php?t=67323
        return getHibernateTemplate().find("from Student s where s.group=?", group);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByGroupId(Long groupId) {
        return getHibernateTemplate().find("from Student s where s.group.id=?", groupId);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings({"unchecked"})
    @Override
    public List<Student> findBySpecialityIdAndCourse(Long specialityId, Integer course) {
        return getHibernateTemplate().find("from Student s where s.speciality.id=? and s.group.course=?",
                specialityId, course);
    }
}
