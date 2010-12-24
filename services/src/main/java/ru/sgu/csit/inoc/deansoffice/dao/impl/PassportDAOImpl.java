package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.PassportDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Passport;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 24.12.10
 * Time: 9:27
 */
@Repository
public class PassportDAOImpl extends BaseDAOImpl<Passport, Long> implements PassportDAO {
}
