package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.springframework.stereotype.Repository;
import ru.sgu.csit.inoc.deansoffice.dao.OrderDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 7, 2010
 * Time: 10:56:51 AM
 */
@Repository
public class OrderDAOImpl extends BaseDAO<Order, Long> implements OrderDAO {
}
