package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.OrderQueueWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.OrderController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:07 PM
 */
public class OrderView extends View {
    private OrderQueueWindow orderQueueWindow;

    public OrderView(OrderController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        orderQueueWindow = new OrderQueueWindow();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.OrderQueueCall)) {
            orderQueueWindow.show();
        }
    }
}
