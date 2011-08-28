package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.AdminView;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 8:26 PM
 */
public class AdminController extends Controller {
    private AdminView adminView;

    public AdminController() {
        registerEventTypes(CommonEvents.Init, AdminEvents.UIReady, CommonEvents.Error);
        registerEventTypes(AdminEvents.NavigationPanelReady);
        registerEventTypes(AdminEvents.MenuBarReady, AdminEvents.StatusBarReady);
        registerEventTypes(AdminEvents.ShowSettingsPanel);
    }

    @Override
    protected void initialize() {
        super.initialize();

        adminView = new AdminView(this);
    }

    @Override
    public void handleEvent(final AppEvent event) {
        forwardToView(adminView, event);
    }
}
