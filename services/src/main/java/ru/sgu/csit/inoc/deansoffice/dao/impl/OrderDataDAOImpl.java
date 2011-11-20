package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.OrderDataDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * @author Alexander Mesheryakov
 */
@Repository
public class OrderDataDAOImpl extends BaseDAOImpl<Order.OrderData, Long> implements OrderDataDAO {
}
