package ru.sgu.csit.inoc.deansoffice.office.client.gxt;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.content.InfoPanel;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.content.StudentPanel;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.menu.MenuPanel;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.util.SelectionListener;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.util.ContentType;

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

        final StudentPanel studentPanel = new StudentPanel();

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.addMenuSelectionListener(new SelectionListener() {
            @Override
            public void selectionChanged(ModelData data) {
                Long id = data.get("id");
                ContentType type = data.get("type");
                String name = data.get("name");

                if (id != null && type != null && name != null) {
                    if (type.equals(ContentType.GROUP)) {
                        studentPanel.showGroup(id, name);
                    }
                } else {
                    Info.display("Тревожное сообщение", "Нет достаточных данных о выделенном объекте.");
                }
            }
        });

        InfoPanel infoPanel = new InfoPanel(studentPanel.getStudentGrid());

        BorderLayoutData westData = new BorderLayoutData(Style.LayoutRegion.WEST);
        westData.setCollapsible(true);
        westData.setSplit(true);
        westData.setMargins(new Margins(0, 3, 0, 0));

        BorderLayoutData centralData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centralData.setMargins(new Margins(0));

        BorderLayoutData eastData = new BorderLayoutData(Style.LayoutRegion.EAST, 400);
        eastData.setCollapsible(true);
        eastData.setSplit(true);
        eastData.setMargins(new Margins(0, 0, 0, 3));

        add(header);
        add(menuPanel, westData);
        add(studentPanel, centralData);
        add(infoPanel, eastData);
    }
}
