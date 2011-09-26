package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.GroupView;

/**
 * @author Denis Khurtin
 */
public class GroupController extends Controller {
    private GroupView groupView;

    public GroupController() {
        registerEventTypes(AdminEvents.GroupSettingSelected, AdminEvents.ShowSettingsPanel);
        registerEventTypes(AdminEvents.StudentAdded, AdminEvents.StudentChanged, AdminEvents.StudentsDeleted);
    }

    @Override
    protected void initialize() {
        super.initialize();

        groupView = new GroupView(this);
    }

    @Override
    public void handleEvent(final AppEvent event) {
        forwardToView(groupView, event);
    }
}
