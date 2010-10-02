package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 9:58:32 AM
 */
public interface GroupDAO extends BaseDAO<Group, Long> {
    List<Group> findByCourseAndSpeciality(Integer course, Speciality speciality);
    List<Group> findByCourseAndSpecialityId(Integer course, Long specialityId);
}
