package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.UserView;

/**
 * @author Denis Khurtin
 */
public class UserController extends Controller {
    private UserView userView;

    public UserController() {
        registerEventTypes(AdminEvents.UsersSettingSelected);
        registerEventTypes(AdminEvents.UserAdded, AdminEvents.UserChanged, AdminEvents.UsersDeleted);
    }

    @Override
    protected void initialize() {
        super.initialize();

        userView = new UserView(this);
    }

    @Override
    public void handleEvent(final AppEvent event) {
        forwardToView(userView, event);
    }
}
