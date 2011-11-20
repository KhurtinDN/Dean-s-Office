package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.DirectiveDataDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class DirectiveDataDAOImpl extends BaseDAOImpl<Directive.DirectiveData, Long> implements DirectiveDataDAO {
}
