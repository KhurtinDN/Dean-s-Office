package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.AppView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 2:25 PM
 */
public class AppController extends Controller {
    private AppView appView;

    public AppController() {
        registerEventTypes(AppEvents.Init);
        registerEventTypes(AppEvents.NavigationPanelReady);
        registerEventTypes(AppEvents.Error);
    }

    @Override
    protected void initialize() {
        super.initialize();

        appView = new AppView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(appView, event);
    }
}
