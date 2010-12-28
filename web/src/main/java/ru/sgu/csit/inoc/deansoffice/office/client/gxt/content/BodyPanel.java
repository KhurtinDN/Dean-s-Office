package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.Element;

/**
 * User: KhurtinDN ( KhurtinDN@gmail.com )
 * Date: 12/24/10
 * Time: 3:48 PM
 */
public class BodyPanel extends TabPanel {
    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        TabItem item = new TabItem("Справки");
        item.add(new ReferenceBodyPanel());

        add(item);

        item = new TabItem("Учетная карточка");
        item.add(new ReferenceBodyPanel());

        add(item);
    }
}
