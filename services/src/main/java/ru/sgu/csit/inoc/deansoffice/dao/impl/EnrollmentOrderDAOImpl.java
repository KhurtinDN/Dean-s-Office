package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.EnrollmentOrderDAO;
import ru.sgu.csit.inoc.deansoffice.domain.EnrollmentOrder;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * @author Alexander Mesheryakov, Denis Khurtin
 */
@Repository
public class EnrollmentOrderDAOImpl extends BaseDAOImpl<EnrollmentOrder, Long> implements EnrollmentOrderDAO {
}
