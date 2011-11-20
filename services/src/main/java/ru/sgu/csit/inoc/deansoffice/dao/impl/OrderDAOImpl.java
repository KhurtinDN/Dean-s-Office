package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.OrderDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class OrderDAOImpl extends BaseDAOImpl<Order, Long> implements OrderDAO {
}
