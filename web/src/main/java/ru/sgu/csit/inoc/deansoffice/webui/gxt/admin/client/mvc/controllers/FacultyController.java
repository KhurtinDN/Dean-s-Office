package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.FacultyView;

/**
 * @author Denis Khurtin
 */
public class FacultyController extends Controller {
    private FacultyView facultyView;

    public FacultyController() {
        registerEventTypes(AdminEvents.FacultiesSettingSelected, AdminEvents.FacultySettingSelected);
        registerEventTypes(AdminEvents.ShowSettingsPanel);
        registerEventTypes(AdminEvents.FacultyAdded, AdminEvents.FacultyChanged, AdminEvents.FacultiesDeleted);
        registerEventTypes(AdminEvents.SpecialityAdded, AdminEvents.SpecialityChanged, AdminEvents.SpecialitiesDeleted);
    }

    @Override
    protected void initialize() {
        super.initialize();

        facultyView = new FacultyView(this);
    }

    @Override
    public void handleEvent(final AppEvent event) {
        forwardToView(facultyView, event);
    }
}
