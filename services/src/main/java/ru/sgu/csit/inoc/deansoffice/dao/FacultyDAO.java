package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;

import java.util.List;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
public interface FacultyDAO extends BaseDAO<Faculty, Long> {
    List<Faculty> findByShortName(String shortName);
}
