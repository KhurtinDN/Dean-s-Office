package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders.OrderDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders.OrderQueueWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.OrderController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.OrderModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:07 PM
 */
public class OrderView extends View {

    private OrderQueueWindow orderQueueWindow;
    private OrderDialog orderDialog;

    public OrderView(OrderController controller) {
        super(controller);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.OrderQueueCall)) {
            onOrderQueueCall(event);
        } else if (eventType.equals(AppEvents.AddNewOrderCall)) {
            onAddNewOrderCall(event);
        } else if (eventType.equals(AppEvents.EditOrderCall)) {
            onEditOrderCall(event);
        }
    }

    private void onOrderQueueCall(AppEvent event) {
        if (orderQueueWindow == null) {
            orderQueueWindow = new OrderQueueWindow();
        }
        orderQueueWindow.show();
    }

    private void onAddNewOrderCall(AppEvent event) {
        if (orderDialog == null) {
            orderDialog = new OrderDialog();
        }
        orderDialog.showNewOrderDialog();
    }

    private void onEditOrderCall(AppEvent event) {
        if (orderDialog == null) {
            orderDialog = new OrderDialog();
        }
        orderDialog.showEditOrderDialog( (OrderModel) event.getData() );
    }
}
