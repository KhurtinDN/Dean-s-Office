package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.PassportDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Passport;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class PassportDAOImpl extends BaseDAOImpl<Passport, Long> implements PassportDAO {
}
