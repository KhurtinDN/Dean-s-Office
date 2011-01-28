package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.StatusBarController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/28/11
 * Time: 11:18 AM
 */
public class StatusBarView extends View {
    private Status status;
    private ToolBar toolBar;

    public StatusBarView(StatusBarController controller) {
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

        if (eventType.equals(AppEvents.Init)) {
            onInit(event);
        } else if (eventType.equals(AppEvents.UIReady)) {
            onUIReady(event);
        } else if (eventType.equals(AppEvents.SpecialitySelected)) {
            onSpecialitySelected(event);
        } else if (eventType.equals(AppEvents.GroupSelected)) {
            onGroupSelected(event);
        } else if (eventType.equals(AppEvents.StudentSelected)) {
            onStudentSelected(event);
        }
    }

    private void onInit(AppEvent event) {
        status.setWidth("100%");
        status.setBox(true);
        setStatus("Инициализация");

        toolBar.add(status);

        Dispatcher.forwardEvent(AppEvents.StatusBarReady, toolBar);
    }

    private void onUIReady(AppEvent event) {
        setStatus("Приложение готово к работе");
    }

    private void onSpecialitySelected(AppEvent event) {
        SpecialityModel specialityModel = event.getData();
        setStatus("Выбрана специальность: " + specialityModel.getName());
    }

    private void onGroupSelected(AppEvent event) {
        GroupModel groupModel = event.getData();
        setStatus("Выбрана группа: " + groupModel.getName());
    }

    private void onStudentSelected(AppEvent event) {
        StudentModel studentModel = event.getData();
        setStatus("Выбран студент: " + studentModel.getName());
    }
}
