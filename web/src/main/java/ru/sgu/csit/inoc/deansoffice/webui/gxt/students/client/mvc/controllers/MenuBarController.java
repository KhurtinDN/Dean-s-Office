package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.MenuBarView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 8:04 AM
 */
public class MenuBarController extends Controller {
    private MenuBarView menuBarView;

    public MenuBarController() {
        registerEventTypes(AppEvents.Init);
    }

    @Override
    protected void initialize() {
        super.initialize();

        menuBarView = new MenuBarView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(menuBarView, event);
    }
}
