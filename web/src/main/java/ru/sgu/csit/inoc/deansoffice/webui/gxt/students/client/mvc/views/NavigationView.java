package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.NavigationPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.NavigationController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;

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

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(StudentEvents.FacultySelected)) {
            onFacultySelected();
        }
    }

    private void onInit() {
        Dispatcher.forwardEvent(StudentEvents.NavigationPanelReady, navigationPanel);
    }

    private void onFacultySelected() {
        navigationPanel.reloadMenuData();
    }
}
