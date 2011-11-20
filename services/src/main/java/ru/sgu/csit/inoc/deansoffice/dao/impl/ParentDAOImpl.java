package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.ParentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Parent;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class ParentDAOImpl extends BaseDAOImpl<Parent, Long> implements ParentDAO {
}
