package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.InformationView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 11:05 AM
 */
public class InformationController extends Controller {
    private InformationView informationView;

    public InformationController() {
        registerEventTypes(CommonEvents.Init);
        registerEventTypes(StudentEvents.StudentSelected, StudentEvents.GroupSelected, StudentEvents.SpecialitySelected);
    }

    @Override
    protected void initialize() {
        super.initialize();

        informationView = new InformationView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(informationView, event);
    }
}
