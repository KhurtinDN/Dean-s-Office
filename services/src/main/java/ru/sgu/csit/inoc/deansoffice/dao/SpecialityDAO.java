package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 9:58:57 AM
 */
public interface SpecialityDAO extends BaseDAO<Speciality, Long> {
    List<Speciality> findByName(String name);
    List<Speciality> findByShortName(String shortName);
    List<Speciality> findByFaculty(Faculty faculty);
    List<Speciality> findByFacultyId(Long facultyId);
}
