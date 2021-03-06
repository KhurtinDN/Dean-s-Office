package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.NavigationView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 5:34 PM
 */
public class NavigationController extends Controller {
    private NavigationView navigationView;

    public NavigationController() {
        registerEventTypes(CommonEvents.Init);
        registerEventTypes(StudentEvents.FacultySelected);
    }

    @Override
    protected void initialize() {
        super.initialize();

        navigationView = new NavigationView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(navigationView, event);
    }
}
