package ru.sgu.csit.inoc.deansoffice.client.gxt;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.menu.*;
import com.google.gwt.user.client.Element;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 12, 2010
 * Time: 9:57:47 PM
 */
public class HeaderPanel extends ContentPanel {
    public HeaderPanel() {
        this.getHeader().hide();
        this.setAutoHeight(true);

        Menu fileMenu = new Menu();
        fileMenu.add(new MenuItem("Open file"));
        fileMenu.add(new MenuItem("Exit"));

        Menu editMenu = new Menu();
        editMenu.add(new MenuItem("Copy"));
        editMenu.add(new MenuItem("Cut"));
        editMenu.add(new MenuItem("Paste"));

        Menu helpMenu = new Menu();
        helpMenu.add(new MenuItem("Help"));

        MenuBar menuBar = new MenuBar();
        menuBar.setBorders(true);
        menuBar.setStyleAttribute("borderTop", "none");

        menuBar.add(new MenuBarItem("File", fileMenu));
        menuBar.add(new MenuBarItem("Edit", editMenu));
        menuBar.add(new MenuBarItem("Help", helpMenu));

        setTopComponent( menuBar );
    }
}
