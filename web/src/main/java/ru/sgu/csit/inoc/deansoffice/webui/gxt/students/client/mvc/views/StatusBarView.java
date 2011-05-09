package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.StatusBarController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

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

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(StudentEvents.UIReady)) {
            onUIReady();
        } else if (eventType.equals(CommonEvents.Info) || eventType.equals(CommonEvents.InfoWithConfirmation)) {
            onInfo(event);
        } else if (eventType.equals(CommonEvents.Error)) {
            onError(event);
        } else if (eventType.equals(StudentEvents.SpecialitySelected)) {
            onSpecialitySelected(event);
        } else if (eventType.equals(StudentEvents.GroupSelected)) {
            onGroupSelected(event);
        } else if (eventType.equals(StudentEvents.StudentSelected)) {
            onStudentSelected(event);
        }
    }

    private void onInit() {
        status.setWidth("100%");
        status.setBox(true);
        setStatus("Инициализация");

        toolBar.add(status);

        Dispatcher.forwardEvent(StudentEvents.StatusBarReady, toolBar);
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

    private void onSpecialitySelected(AppEvent event) {
        SpecialityModel specialityModel = event.getData();
        setStatus("Выбрана специальность: " + specialityModel.getFullName());
    }

    private void onGroupSelected(AppEvent event) {
        GroupModel groupModel = event.getData();
        setStatus("Выбрана группа: " + groupModel.getName());
    }

    private void onStudentSelected(AppEvent event) {
        StudentModel studentModel = event.getData();
        setStatus("Выбран студент: " + studentModel.getFullName());
    }
}
