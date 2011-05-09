package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.AdminMenuBarView;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 8:04 AM
 */
public class AdminMenuBarController extends Controller {
    private AdminMenuBarView adminMenuBarView;

    public AdminMenuBarController() {
        registerEventTypes(CommonEvents.Init);
    }

    @Override
    protected void initialize() {
        super.initialize();

        adminMenuBarView = new AdminMenuBarView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(adminMenuBarView, event);
    }
}
