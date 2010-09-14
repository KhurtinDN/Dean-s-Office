package ru.sgu.csit.inoc.deansoffice.client.gxt;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;

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

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH);
        northData.setCollapsible(true);
        northData.setFloatable(true);
//        northData.setHideCollapseTool(true);
        northData.setSplit(true);
        northData.setMargins(new Margins(0, 0, 3, 0));

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST);
        westData.setCollapsible(true);
        westData.setSplit(true);
        westData.setMargins(new Margins(0, 3, 0, 0));

        BorderLayoutData centralData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centralData.setMargins(new Margins(0));

        BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH);
        southData.setCollapsible(true);
        southData.setFloatable(true);
        southData.setSplit(true);
        southData.setMargins(new Margins(3, 0, 0, 0));

        add(new HeaderPanel(), northData);
        add(new MenuPanel(), westData);
        add(new BodyPanel(), centralData);
        add(new FooterPanel(), southData);

        layout();

        ((BorderLayout)getLayout()).hide(Style.LayoutRegion.SOUTH);
    }
}
