package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.RootPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.controllers.AppController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 2:35 PM
 */
public class AppView extends View {
    private Viewport viewport;
    private ContentPanel mainPanel;

    public AppView(AppController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        viewport = new Viewport();
        mainPanel = new ContentPanel();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.Init)) {
            onInit(event);
        } else if (eventType.equals(AppEvents.NavigationPanelReady)) {
            onNavigationPanelReady(event);
        } else if (eventType.equals(AppEvents.UIReady)) {
            onUIReady(event);
        } else if (eventType.equals(AppEvents.Error)) {
            onError(event);
        }
    }

    private void onInit(AppEvent event) {
        viewport.setLayout(new BorderLayout());

        BorderLayoutData centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerLayoutData.setCollapsible(false);

        mainPanel.setHeaderVisible(false);
        viewport.add(mainPanel, centerLayoutData);
    }

    private void onNavigationPanelReady(AppEvent event) {
        BorderLayoutData westLayoutData = new BorderLayoutData(Style.LayoutRegion.WEST, 200, 150, 300);
        westLayoutData.setCollapsible(true);
        westLayoutData.setSplit(true);

        Component component = event.getData();
        viewport.add(component, westLayoutData);
    }

    private void onUIReady(AppEvent event) {
        RootPanel.get().add(viewport);
    }

    private void onError(AppEvent event) {}
}
