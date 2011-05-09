package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 3:11 PM
 */
public class StaffPanel extends FormPanel {
    public StaffPanel() {
        setHeading("Сотрудники университета");

        add(new Button("test staff"));
    }
}
