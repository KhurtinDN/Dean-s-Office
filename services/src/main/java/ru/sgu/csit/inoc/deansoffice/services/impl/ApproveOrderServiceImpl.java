package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.LeaderDAO;
import ru.sgu.csit.inoc.deansoffice.dao.OrderDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Directive;
import ru.sgu.csit.inoc.deansoffice.domain.Order;
import ru.sgu.csit.inoc.deansoffice.services.ApproveOrderService;
import ru.sgu.csit.inoc.deansoffice.services.DirectiveService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 14.03.11
 * Time: 13:32
 */
@Service
public class ApproveOrderServiceImpl implements ApproveOrderService {
//    @Autowired
//    private LeaderDAO leaderDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public boolean addOrder(Order order) {
        if (order.getDirectives() != null && order.getData() != null && order.getData().getSupervisor() != null) {
            order.setState(Order.OrderState.IN_PROCESS);
            orderDAO.saveOrUpdate(order);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Выполняет директивы переданного приказа если его состояние Order.OrderState.IN_PROCESS.
     * У приказа обязательно должны быть заплнены поля number и signedDate.
     * @param order
     * @return
     */
    @Override
    public boolean approveOrder(Order order) {
        if (order.getState().equals(Order.OrderState.IN_PROCESS)
                && order.getNumber() != null && order.getSignedDate() != null) {

            DirectiveService directiveService = new DirectiveServiceImpl();
            for (int i = 0; i < order.getDirectives().size(); ++i) {
                Directive directive = order.getDirectives().get(i);
                directiveService.executeDirective(directive);
            }

            order.setState(Order.OrderState.COMPLETED);
            orderDAO.saveOrUpdate(order);
            return true;
        }
        return false;
    }
}
