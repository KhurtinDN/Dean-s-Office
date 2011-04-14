package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminAppEvents;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 8:26 PM
 */
public class AdminAppView extends View {
    private Viewport viewport;

    public AdminAppView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        viewport = new Viewport();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AdminAppEvents.Init)) {
            onInit(event);
        } else if (eventType.equals(AdminAppEvents.NavigationPanelReady)) {
            onNavigationPanelReady(event);
        }
    }

    private void onInit(AppEvent event) {
        viewport.setLayout(new BorderLayout());
    }

    private void onNavigationPanelReady(AppEvent event) {
        BorderLayoutData westLayoutData = new BorderLayoutData(Style.LayoutRegion.WEST, 300, 200, 500);

        ContentPanel contentPanel = event.getData();
        viewport.add(contentPanel, westLayoutData);
    }
}
