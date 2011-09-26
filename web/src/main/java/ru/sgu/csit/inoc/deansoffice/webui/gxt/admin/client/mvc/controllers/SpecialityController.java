package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.SpecialityView;

/**
 * @author Denis Khurtin
 */
public class SpecialityController extends Controller {
    private SpecialityView specialityView;

    public SpecialityController() {
        registerEventTypes(AdminEvents.SpecialitySettingSelected, AdminEvents.ShowSettingsPanel);
        registerEventTypes(AdminEvents.GroupAdded, AdminEvents.GroupChanged, AdminEvents.GroupsDeleted);
    }

    @Override
    protected void initialize() {
        super.initialize();

        specialityView = new SpecialityView(this);
    }

    @Override
    public void handleEvent(final AppEvent event) {
        forwardToView(specialityView, event);
    }
}
