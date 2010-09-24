package ru.sgu.csit.inoc.deansoffice.client.gxt;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.client.gxt.menu.MenuPanel;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 12, 2010
 * Time: 9:47:09 PM
 */
public class MainLayoutContainer extends Viewport {
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setLayout(new BorderLayout());

        final HeaderPanel header = new HeaderPanel();
        header.addListener(Events.Attach, new Listener<BaseEvent>() {
            private boolean build = false;
            @Override
            public void handleEvent(BaseEvent be) {
                if (!build) {
                    BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, header.getHeight());
                    northData.setCollapsible(true);
                    northData.setFloatable(true);
                    MainLayoutContainer.this.setLayoutData(header, northData);
                    MainLayoutContainer.this.layout();
                    build = true;
                }
            }
        });

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST);
        westData.setCollapsible(true);
        westData.setSplit(true);
        westData.setMargins(new Margins(0, 3, 0, 0));

        BorderLayoutData centralData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centralData.setMargins(new Margins(0));

        add(header);
        add(new MenuPanel(), westData);
        add(new BodyPanel(), centralData);
    }
}
