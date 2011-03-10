package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.DirectiveDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 03.03.11
 * Time: 12:51
 */
@Repository
public class DirectiveDAOImpl extends BaseDAOImpl<Directive, Long> implements DirectiveDAO {
}
