package ru.sgu.csit.inoc.deansoffice.webui.gxt.login.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * @author Denis Khurtin
 */
public class Login implements EntryPoint {
    public void onModuleLoad() {
        final LoginDialog loginDialog = new LoginDialog();
        loginDialog.show();
    }
}
