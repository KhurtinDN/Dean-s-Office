package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.controllers.MessageController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * @author Denis Khurtin
 */
public class Admin implements EntryPoint {
    public void onModuleLoad() {
        final Dispatcher dispatcher = Dispatcher.get();

        dispatcher.addController(new AdminController());
        dispatcher.addController(new InstituteController());
        dispatcher.addController(new UserController());
        dispatcher.addController(new FacultyController());
        dispatcher.addController(new SpecialityController());
        dispatcher.addController(new GroupController());
        dispatcher.addController(new AdminNavigationController());
        dispatcher.addController(new AdminMenuBarController());
        dispatcher.addController(new AdminStatusBarController());
        dispatcher.addController(new MessageController());

        dispatcher.dispatch(CommonEvents.Init);
        dispatcher.dispatch(AdminEvents.UIReady);
    }
}
