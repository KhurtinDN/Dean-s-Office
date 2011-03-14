package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Order;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 14.03.11
 * Time: 13:31
 */
public interface ApproveOrderService {
    boolean addOrder(Order order);
    boolean approveOrder(Order order);
}
