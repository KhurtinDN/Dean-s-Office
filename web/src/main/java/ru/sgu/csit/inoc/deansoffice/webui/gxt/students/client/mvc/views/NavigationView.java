package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.NavigationPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.NavigationController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 5:35 PM
 */
public class NavigationView extends View {
    private NavigationPanel navigationPanel;

    public NavigationView(NavigationController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        navigationPanel = new NavigationPanel();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.Init)) {
            onInit(event);
        } else if (eventType.equals(AppEvents.FacultySelected)) {
            onFacultySelected(event);
        }
    }

    private void onInit(AppEvent event) {
        Dispatcher.forwardEvent(AppEvents.NavigationPanelReady, navigationPanel);
    }

    private void onFacultySelected(AppEvent event) {
        navigationPanel.reloadMenuData();
    }
}
