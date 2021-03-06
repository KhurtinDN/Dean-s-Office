package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.info.InformationPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.InformationController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 11:06 AM
 */
public class InformationView extends View {
    private InformationPanel informationPanel;

    public InformationView(InformationController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        informationPanel = new InformationPanel();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(StudentEvents.StudentSelected)) {
            onStudentSelected(event);
        } else if (eventType.equals(StudentEvents.GroupSelected)) {
            onGroupSelected(event);
        } else if (eventType.equals(StudentEvents.SpecialitySelected)) {
            onSpecialitySelected(event);
        }
    }

    private void onInit() {
        Dispatcher.forwardEvent(StudentEvents.InformationPanelReady, informationPanel);
    }

    private void onStudentSelected(AppEvent event) {
        StudentModel studentModel = event.getData();
        informationPanel.showStudentInformation(studentModel);
    }

    private void onGroupSelected(AppEvent event) {
        GroupModel groupModel = event.getData();
        informationPanel.showGroupInformation(groupModel);
    }

    private void onSpecialitySelected(AppEvent event) {
        SpecialityModel specialityModel = event.getData();
        informationPanel.showSpecialityInformation(specialityModel);
    }
}
