package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 1:00 PM
 */
public class Students implements EntryPoint {
    public void onModuleLoad() {
        Dispatcher dispatcher = Dispatcher.get();
        dispatcher.addController(new AppController());
        dispatcher.addController(new NavigationController());
        dispatcher.addController(new StudentsController());
        dispatcher.addController(new InformationController());
        dispatcher.addController(new MenuBarController());
        dispatcher.addController(new StatusBarController());
        dispatcher.dispatch(AppEvents.Init);
        dispatcher.dispatch(AppEvents.UIReady);
    }
}
