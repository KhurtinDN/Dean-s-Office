package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.StudentsPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.StudentsController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/31/11
 * Time: 11:13 AM
 */
public class StudentsView extends View {
    private StudentsPanel studentsPanel;

    public StudentsView(StudentsController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        studentsPanel = new StudentsPanel();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.Init)) {
            onInit(event);
        } else if (eventType.equals(AppEvents.SpecialitySelected)) {
            onSpecialitySelected(event);
        } else if (eventType.equals(AppEvents.GroupSelected)) {
            onGroupSelected(event);
        }
    }

    private void onSpecialitySelected(AppEvent event) {
        SpecialityModel specialityModel = event.getData();
        Integer course = event.getData("course");
        studentsPanel.showSpecialityByCourse(specialityModel, course);
    }

    private void onGroupSelected(AppEvent event) {
        GroupModel groupModel = event.getData();
        studentsPanel.showGroup(groupModel);
    }

    private void onInit(AppEvent event) {
        Dispatcher.forwardEvent(AppEvents.StudentsPanelReady, studentsPanel);
    }
}
