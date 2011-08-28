package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.FacultiesPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.FacultyPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.FacultyController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

/**
 * @author Denis Khurtin
 */
public class FacultyView extends View {
    private FacultiesPanel facultiesPanel;
    private FacultyPanel facultyPanel;

    public FacultyView(final FacultyController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        facultiesPanel = new FacultiesPanel();
        facultyPanel = new FacultyPanel();
    }

    @Override
    protected void handleEvent(final AppEvent event) {
        final EventType eventType = event.getType();

        if (eventType.equals(AdminEvents.FacultiesSettingSelected)) {
            onFacultiesSettingSelected();
        } else if (eventType.equals(AdminEvents.FacultySettingSelected)) {
            onFacultySettingSelected(event);
        } else if (eventType.equals(AdminEvents.FacultyAdded)) {
            facultiesPanel.reload();
        } else if (eventType.equals(AdminEvents.FacultyChanged)) {
            facultiesPanel.reload();
        } else if (eventType.equals(AdminEvents.FacultiesDeleted)) {
            facultiesPanel.reload();
        } else if (eventType.equals(AdminEvents.SpecialityAdded)) {
            facultyPanel.reload();
        } else if (eventType.equals(AdminEvents.SpecialityChanged)) {
            facultyPanel.reload();
        } else if (eventType.equals(AdminEvents.SpecialitiesDeleted)) {
            facultyPanel.reload();
        }
    }

    private void onFacultiesSettingSelected() {
        Dispatcher.forwardEvent(AdminEvents.ShowSettingsPanel, facultiesPanel);
        facultiesPanel.reload();
    }

    private void onFacultySettingSelected(final AppEvent event) {
        Dispatcher.forwardEvent(AdminEvents.ShowSettingsPanel, facultyPanel);
        facultyPanel.reload(event.<FacultyModel>getData());
    }
}
