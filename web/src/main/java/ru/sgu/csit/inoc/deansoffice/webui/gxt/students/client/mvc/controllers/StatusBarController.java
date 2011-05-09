package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.StatusBarView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 11:18 AM
 */
public class StatusBarController extends Controller {
    private StatusBarView statusBarView;

    public StatusBarController() {
        registerEventTypes(CommonEvents.Init, StudentEvents.UIReady);
        registerEventTypes(CommonEvents.Info, CommonEvents.InfoWithConfirmation, CommonEvents.Error);
        registerEventTypes(StudentEvents.SpecialitySelected, StudentEvents.GroupSelected, StudentEvents.StudentSelected);
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
