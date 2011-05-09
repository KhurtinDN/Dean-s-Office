package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminStatusBarController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 11:18 AM
 */
public class AdminStatusBarView extends View {
    private Status status;
    private ToolBar toolBar;

    public AdminStatusBarView(AdminStatusBarController controller) {
        super(controller);
    }

    public void setStatus(String message) {
        status.setText(message);
    }

    @Override
    protected void initialize() {
        super.initialize();

        status = new Status();
        toolBar = new ToolBar();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(AdminEvents.UIReady)) {
            onUIReady();
        } else if (eventType.equals(CommonEvents.Info) || eventType.equals(CommonEvents.InfoWithConfirmation)) {
            onInfo(event);
        } else if (eventType.equals(CommonEvents.Error)) {
            onError(event);
        }
    }

    private void onInit() {
        status.setWidth("100%");
        status.setBox(true);
        setStatus("Инициализация");

        toolBar.add(status);

        Dispatcher.forwardEvent(AdminEvents.StatusBarReady, toolBar);
    }

    private void onUIReady() {
        setStatus("Приложение готово к работе");
    }

    private void onInfo(AppEvent event) {
        setStatus("Информация: " + event.getData().toString());
    }

    private void onError(AppEvent event) {
        setStatus("Ошибка: " + event.getData().toString());
    }
}
