package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.InstitutePanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.InstituteController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;

/**
 * @author Denis Khurtin
 */
public class InstituteView extends View {
    private InstitutePanel institutePanel;
    private boolean active;

    public InstituteView(final InstituteController instituteController) {
        super(instituteController);
    }

    @Override
    protected void initialize() {
        super.initialize();

        institutePanel = new InstitutePanel();
    }

    @Override
    protected void handleEvent(final AppEvent event) {
        final EventType eventType = event.getType();

        if (eventType.equals(AdminEvents.InstituteSettingSelected)) {
            Dispatcher.forwardEvent(AdminEvents.ShowSettingsPanel, institutePanel);
            institutePanel.reload();
        } else if (eventType.equals(AdminEvents.ShowSettingsPanel)) {
            onShowSettingsPanel(event);
        } else if (eventType.equals(AdminEvents.EmployeeAdded)) {
            reloadInstitutionPanel();
        } else if (eventType.equals(AdminEvents.EmployeeChanged)) {
            reloadInstitutionPanel();
        } else if (eventType.equals(AdminEvents.EmployeesDeleted)) {
            reloadInstitutionPanel();
        }
    }

    private void onShowSettingsPanel(final AppEvent event) {
        active = institutePanel == event.getData();
    }

    private void reloadInstitutionPanel() {
        if (active) {
            institutePanel.reload();
        }
    }
}
