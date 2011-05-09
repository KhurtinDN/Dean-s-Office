package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 3:11 PM
 */
public class UserPanel extends FormPanel {
    public UserPanel() {
        setHeading("Пользователи системы");

        add(new Button("test users"));
    }
}
