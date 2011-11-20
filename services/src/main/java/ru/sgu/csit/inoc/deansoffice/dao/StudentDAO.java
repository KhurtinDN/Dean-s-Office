package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
public interface StudentDAO extends BaseDAO<Student, Long> {
    List<Student> findByGroup(Group group);
    List<Student> findByGroupId(Long groupId);
    List<Student> findBySpecialityIdAndCourse(Long specialityId, Integer course);
}
