package ru.sgu.csit.inoc.deansoffice.dao;

import ru.sgu.csit.inoc.deansoffice.domain.Administration;

/**
 * @author Denis Khurtin
 */
public interface AdministrationDAO extends BaseDAO<Administration, Long> {
    Administration load();
}
