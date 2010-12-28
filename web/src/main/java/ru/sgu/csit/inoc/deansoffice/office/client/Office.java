package ru.sgu.csit.inoc.deansoffice.office.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.MainLayoutContainer;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Office implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        MainLayoutContainer mainLayoutContainer = new MainLayoutContainer();
        RootLayoutPanel.get().add(mainLayoutContainer);
    }
}
