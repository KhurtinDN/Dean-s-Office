package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.ParentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Dean;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 24.12.10
 * Time: 9:26
 */
@Repository
public class ParentDAOImpl extends BaseDAOImpl<Dean, Long> implements ParentDAO {
}
