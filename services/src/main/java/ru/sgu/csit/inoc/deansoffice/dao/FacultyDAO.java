package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 9:59:38 AM
 */
public interface FacultyDAO extends BaseDAO<Faculty, Long> {
    List<Faculty> findByShortName(String shortName);
}
