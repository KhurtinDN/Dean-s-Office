package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.OrderModel;

import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:37 PM
 */
@RemoteServiceRelativePath("GWTServices/OrderService")
public interface OrderService extends RemoteService {

    public List<OrderModel> loadOrders();

    public static class App {
        private static final OrderServiceAsync ourInstance = GWT.create(OrderService.class);

        public static OrderServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
