package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views.AdminStatusBarView;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 11:18 AM
 */
public class AdminStatusBarController extends Controller {
    private AdminStatusBarView statusBarView;

    public AdminStatusBarController() {
        registerEventTypes(CommonEvents.Init, AdminEvents.UIReady);
        registerEventTypes(CommonEvents.Info, CommonEvents.InfoWithConfirmation, CommonEvents.Error);
    }

    @Override
    protected void initialize() {
        super.initialize();

        statusBarView = new AdminStatusBarView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(statusBarView, event);
    }
}
