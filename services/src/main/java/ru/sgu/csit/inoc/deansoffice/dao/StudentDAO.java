package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 3, 2010
 * Time: 10:57:21 AM
 */
public interface StudentDAO extends BaseDAO<Student, Long> {
    List<Student> findByGroup(Group group);
    List<Student> findByGroupId(Long groupId);
    List<Student> findBySpecialityIdAndCourse(Long specialityId, Integer course);
    void initialize(Object proxy);
}
