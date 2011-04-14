package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminAppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.AdminAppView;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 8:26 PM
 */
public class AdminAppController extends Controller {
    private AdminAppView adminAppView;

    public AdminAppController() {
        registerEventTypes(AdminAppEvents.Init);
        registerEventTypes(AdminAppEvents.Error);
    }

    @Override
    protected void initialize() {
        super.initialize();

        adminAppView = new AdminAppView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(adminAppView, event);
    }
}
