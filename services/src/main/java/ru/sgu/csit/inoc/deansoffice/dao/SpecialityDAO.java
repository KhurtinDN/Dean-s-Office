package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
public interface SpecialityDAO extends BaseDAO<Speciality, Long> {
    List<Speciality> findByName(String name);
    List<Speciality> findByShortName(String shortName);
    List<Speciality> findByFaculty(Faculty faculty);
    List<Speciality> findByFacultyId(Long facultyId);
}
