package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.OrderDataDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 03.03.11
 * Time: 13:20
 */
@Repository
public class OrderDataDAOImpl extends BaseDAOImpl<Order.OrderData, Long> implements OrderDataDAO {
}
