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

    private boolean facultiesPanelActive;
    private boolean facultyPanelActive;

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
        } else if (eventType.equals(AdminEvents.ShowSettingsPanel)) {
            onShowSettingsPanel(event);
        } else if (eventType.equals(AdminEvents.FacultySettingSelected)) {
            onFacultySettingSelected(event);
        } else if (eventType.equals(AdminEvents.FacultyAdded)) {
            reloadFacultiesPanel();
        } else if (eventType.equals(AdminEvents.FacultyChanged)) {
            reloadFacultiesPanel();
        } else if (eventType.equals(AdminEvents.FacultiesDeleted)) {
            reloadFacultiesPanel();
        } else if (eventType.equals(AdminEvents.SpecialityAdded)) {
            reloadFacultyPanel();
        } else if (eventType.equals(AdminEvents.SpecialityChanged)) {
            reloadFacultyPanel();
        } else if (eventType.equals(AdminEvents.SpecialitiesDeleted)) {
            reloadFacultyPanel();
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

    private void onShowSettingsPanel(final AppEvent event) {
        facultiesPanelActive = facultiesPanel == event.getData();
        facultyPanelActive = facultyPanel == event.getData();
    }

    private void reloadFacultiesPanel() {
        if (facultiesPanelActive) {
            facultiesPanel.reload();
        }
    }

    private void reloadFacultyPanel() {
        if (facultyPanelActive) {
            facultyPanel.reload();
        }
    }
}
