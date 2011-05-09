package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.StudentsView;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/31/11
 * Time: 11:11 AM
 */
public class StudentsController extends Controller {
    StudentsView studentsView;

    public StudentsController() {
        registerEventTypes(CommonEvents.Init);
        registerEventTypes(StudentEvents.SpecialitySelected, StudentEvents.GroupSelected);
    }

    @Override
    protected void initialize() {
        super.initialize();

        studentsView = new StudentsView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(studentsView, event);
    }
}
