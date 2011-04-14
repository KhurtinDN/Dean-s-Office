package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.AdminNavigationPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminNavigationController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminAppEvents;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 9:23 PM
 */
public class AdminNavigationView extends View {
    private AdminNavigationPanel adminNavigationPanel;

    public AdminNavigationView(AdminNavigationController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        adminNavigationPanel = new AdminNavigationPanel();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AdminAppEvents.Init)) {
            onInit(event);
        }
    }

    private void onInit(AppEvent event) {
        Dispatcher.forwardEvent(AdminAppEvents.NavigationPanelReady, adminNavigationPanel);
    }
}
