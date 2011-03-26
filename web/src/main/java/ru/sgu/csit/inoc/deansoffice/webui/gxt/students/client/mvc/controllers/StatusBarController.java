package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.StatusBarView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 11:18 AM
 */
public class StatusBarController extends Controller {
    private StatusBarView statusBarView;

    public StatusBarController() {
        registerEventTypes(AppEvents.Init, AppEvents.UIReady);
        registerEventTypes(AppEvents.Info, AppEvents.InfoWithConfirmation, AppEvents.Error);
        registerEventTypes(AppEvents.SpecialitySelected, AppEvents.GroupSelected, AppEvents.StudentSelected);
    }

    @Override
    protected void initialize() {
        super.initialize();

        statusBarView = new StatusBarView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(statusBarView, event);
    }
}
