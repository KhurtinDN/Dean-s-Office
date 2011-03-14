package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.MenuBarController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 8:26 AM
 */
public class MenuBarView extends View {
    private MenuBar menuBar;

    public MenuBarView(MenuBarController controller) {
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

        Menu orderMenu = new Menu();

        orderMenu.add(new MenuItem("Создать новый", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Dispatcher.forwardEvent(AppEvents.AddNewOrderCall);
            }
        }));

        orderMenu.add(new MenuItem("Очередь приказов", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Dispatcher.forwardEvent(AppEvents.OrderQueueCall);
            }
        }));

        Menu helpMenu = new Menu();

        helpMenu.add(new MenuItem("Справка", new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Dispatcher.forwardEvent(AppEvents.Info, "Справочная информация находится в разработке.");
            }
        }));

        menuBar = new MenuBar();
        menuBar.add(new MenuBarItem("Файл", fileMenu));
        menuBar.add(new MenuBarItem("Приказы", orderMenu));
        menuBar.add(new MenuBarItem("Помощь", helpMenu));
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.Init)) {
            onInit(event);
        }
    }

    private void onInit(AppEvent event) {
        Dispatcher.forwardEvent(AppEvents.MenuBarReady, menuBar);
    }
}
