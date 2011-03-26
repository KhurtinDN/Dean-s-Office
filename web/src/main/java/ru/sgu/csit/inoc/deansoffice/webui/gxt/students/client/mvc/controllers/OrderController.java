package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.OrderView;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:04 PM
 */
public class OrderController extends Controller {
    private OrderView orderView;

    public OrderController() {
        registerEventTypes(AppEvents.OrderQueueCall, AppEvents.AddNewOrderCall, AppEvents.EditOrderCall);
    }

    @Override
    protected void initialize() {
        super.initialize();

        orderView = new OrderView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(orderView, event);
    }
}
