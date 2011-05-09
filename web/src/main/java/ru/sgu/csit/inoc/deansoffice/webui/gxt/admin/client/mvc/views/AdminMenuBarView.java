package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.Window;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminMenuBarController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 8:26 AM
 */
public class AdminMenuBarView extends View {
    private MenuBar menuBar;

    public AdminMenuBarView(AdminMenuBarController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        Menu fileMenu = new Menu();

        fileMenu.add(new MenuItem("Выход", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Window.open("j_spring_security_logout", "_self", "");
            }
        }));

        Menu helpMenu = new Menu();

        helpMenu.add(new MenuItem("Справка", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Dispatcher.forwardEvent(CommonEvents.Info, "Справочная информация находится в разработке.");
            }
        }));

        menuBar = new MenuBar();
        menuBar.add(new MenuBarItem("Файл", fileMenu));
        menuBar.add(new MenuBarItem("Помощь", helpMenu));
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        }
    }

    private void onInit() {
        Dispatcher.forwardEvent(AdminEvents.MenuBarReady, menuBar);
    }
}
