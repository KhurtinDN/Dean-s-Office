package ru.sgu.csit.inoc.deansoffice.login.client;

import com.google.gwt.core.client.EntryPoint;
import ru.sgu.csit.inoc.deansoffice.login.client.gxt.LoginDialog;

/**
 * User: hd (KhurtinDN@gmail.com)
 * Date: Nov 3, 2010
 * Time: 6:51:38 AM
 */
public class Login implements EntryPoint {
    public void onModuleLoad() {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.show();
    }
}
