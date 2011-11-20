package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.ReferenceDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class ReferenceDAOImpl extends BaseDAOImpl<Reference, Long> implements ReferenceDAO {
}
