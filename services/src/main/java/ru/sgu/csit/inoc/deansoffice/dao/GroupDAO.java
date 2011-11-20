package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
public interface GroupDAO extends BaseDAO<Group, Long> {
    List<Group> findBySpecialityId(Long specialityId);
    List<Group> findByCourseAndSpeciality(Integer course, Speciality speciality);
    List<Group> findByCourseAndSpecialityId(Integer course, Long specialityId);
    List<Group> findByName(String name);
}
