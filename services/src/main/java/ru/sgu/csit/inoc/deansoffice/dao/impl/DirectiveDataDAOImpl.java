package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.DirectiveDataDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 03.03.11
 * Time: 13:31
 */
@Repository
public class DirectiveDataDAOImpl extends BaseDAOImpl<Directive.DirectiveData, Long>
        implements DirectiveDataDAO {
}
