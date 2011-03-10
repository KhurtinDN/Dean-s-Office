package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.OrderDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 03.03.11
 * Time: 12:48
 */
@Repository
public class OrderDAOImpl extends BaseDAOImpl<Order, Long> implements OrderDAO {
}
