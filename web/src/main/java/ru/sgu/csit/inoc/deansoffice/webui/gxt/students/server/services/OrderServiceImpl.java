package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.OrderService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:41 PM
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderModel> loadOrders() {
        List<OrderModel> orderModelList = new ArrayList<OrderModel>();

        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        orderModel.setType("Приказ");
        orderModel.setName("Остаточные итоги");
        orderModel.setStatus("Утверждён");
        orderModelList.add(orderModel);

        orderModel = new OrderModel();
        orderModel.setId(1L);
        orderModel.setType("Приказ");
        orderModel.setName("Назначение на стипендию");
        orderModel.setStatus("Создан");
        orderModelList.add(orderModel);

        return orderModelList;
    }
}
