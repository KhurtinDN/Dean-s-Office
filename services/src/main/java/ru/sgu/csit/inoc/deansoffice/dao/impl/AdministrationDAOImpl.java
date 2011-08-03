package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.AdministrationDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Administration;

/**
 * @author Denis Khurtin
 */
@Repository
public class AdministrationDAOImpl extends BaseDAOImpl<Administration, Long> implements AdministrationDAO {
}
