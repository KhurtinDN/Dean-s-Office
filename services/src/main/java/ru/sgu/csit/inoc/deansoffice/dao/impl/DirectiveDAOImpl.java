package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.DirectiveDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class DirectiveDAOImpl extends BaseDAOImpl<Directive, Long> implements DirectiveDAO {
}
