package ru.sgu.csit.inoc.deansoffice.office.client.gxt;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.menu.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.menu.MenuService;

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

        MenuItem logoutMenuItem = new MenuItem("Выход", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent menuEvent) {
                Window.open("j_spring_security_logout", "_self", "");
            }
        });

        fileMenu.add(logoutMenuItem);

        Menu editMenu = new Menu();

        MenuItem generateBaseMenuItem = new MenuItem("Сгенерить базу", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                MenuService.App.getInstance().generateBase(new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Info.display("Сообщение от сервера", "Все упало :(");
                    }

                    @Override
                    public void onSuccess(Void result) {
                        Info.display("Сообщение от сервера", "База была успешно создана!");
                    }
                });
            }
        });

        editMenu.add(generateBaseMenuItem);

        Menu helpMenu = new Menu();
        helpMenu.add(new MenuItem("Помощь"));

        MenuBar menuBar = new MenuBar();
        menuBar.setBorders(true);
        menuBar.setStyleAttribute("borderTop", "none");

        menuBar.add(new MenuBarItem("File", fileMenu));
        menuBar.add(new MenuBarItem("Edit", editMenu));
        menuBar.add(new MenuBarItem("Help", helpMenu));

        setTopComponent( menuBar );
    }
}
