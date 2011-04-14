package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.OrderModel;

import java.util.List;

public interface OrderServiceAsync {
    void loadOrders(AsyncCallback<List<OrderModel>> async);
}
