package ru.sgu.csit.inoc.deansoffice.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import ru.sgu.csit.inoc.deansoffice.client.gxt.MainLayoutContainer;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        MainLayoutContainer mainLayoutContainer = new MainLayoutContainer();
        RootPanel.get().add(mainLayoutContainer);
    }
}
