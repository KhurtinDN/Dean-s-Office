package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.InstituteView;

/**
 * @author Denis Khurtin
 */
public class InstituteController extends Controller {
    private InstituteView instituteView;

    public InstituteController() {
        registerEventTypes(AdminEvents.InstituteSettingSelected, AdminEvents.ShowSettingsPanel);
        registerEventTypes(AdminEvents.EmployeeAdded, AdminEvents.EmployeeChanged, AdminEvents.EmployeesDeleted);
    }

    @Override
    protected void initialize() {
        super.initialize();

        instituteView = new InstituteView(this);
    }

    @Override
    public void handleEvent(final AppEvent event) {
        forwardToView(instituteView, event);
    }
}
