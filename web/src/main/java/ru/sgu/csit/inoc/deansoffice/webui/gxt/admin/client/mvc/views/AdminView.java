package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * @author Denis Khurtin
 */
public class AdminView extends View {
    private Viewport viewport;
    private ContentPanel viewportPanel;

    private BorderLayoutData centerLayoutData;

    private ContentPanel centralPanel;

    public AdminView(AdminController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        viewport = new Viewport();
        viewportPanel = new ContentPanel();

        centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);
    }

    @Override
    protected void handleEvent(final AppEvent event) {
        final EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(AdminEvents.UIReady)) {
            onUIReady();
        } else if (eventType.equals(AdminEvents.MenuBarReady)) {
            onMenuBarReady(event);
        } else if (eventType.equals(AdminEvents.StatusBarReady)) {
            onStatusBarReady(event);
        } else if (eventType.equals(AdminEvents.NavigationPanelReady)) {
            onNavigationPanelReady(event);
        } else if (eventType.equals(AdminEvents.ShowSettingsPanel)) {
            setCentralPanel(event.<ContentPanel>getData());
        }
    }

    private void onInit() {
        viewportPanel.setHeaderVisible(false);
        viewportPanel.setLayout(new BorderLayout());

        viewport.setLayout(new FitLayout());
        viewport.add(viewportPanel);
    }

    private void onUIReady() {
        RootLayoutPanel.get().add(viewport);
    }

    private void onMenuBarReady(AppEvent event) {
        viewportPanel.setTopComponent(event.<Component>getData());
    }

    private void onStatusBarReady(AppEvent event) {
        viewportPanel.setBottomComponent(event.<Component>getData());
    }

    private void onNavigationPanelReady(AppEvent event) {
        final BorderLayoutData westLayoutData = new BorderLayoutData(Style.LayoutRegion.WEST, 300, 200, 500);
        westLayoutData.setCollapsible(true);
        westLayoutData.setSplit(true);

        ContentPanel contentPanel = event.getData();
        viewportPanel.add(contentPanel, westLayoutData);
    }

    private void setCentralPanel(final ContentPanel panel) {
        if (panel != centralPanel) {
            if (centralPanel != null) {
                viewportPanel.remove(centralPanel);
            }
            centralPanel = panel;
            viewportPanel.add(centralPanel, centerLayoutData);
            viewportPanel.layout(true);
        }
    }
}
