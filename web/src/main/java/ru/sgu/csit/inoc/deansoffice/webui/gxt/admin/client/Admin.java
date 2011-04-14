package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminAppController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminNavigationController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminAppEvents;

/**
 * User: hd ( KhurtinDN (a) gmail.com )
 * Date: 4/9/11
 * Time: 4:36 PM
 */
public class Admin implements EntryPoint {
    public void onModuleLoad() {
        Dispatcher dispatcher = Dispatcher.get();
        dispatcher.addController(new AdminAppController());
        dispatcher.addController(new AdminNavigationController());

        dispatcher.dispatch(AdminAppEvents.Init);
        dispatcher.dispatch(AdminAppEvents.UIReady);
    }
}
