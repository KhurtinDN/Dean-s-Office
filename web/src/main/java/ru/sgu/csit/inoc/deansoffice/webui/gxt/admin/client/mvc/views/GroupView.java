package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.GroupPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.GroupController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;

/**
 * @author Denis Khurtin
 */
public class GroupView extends View {
    private GroupPanel groupPanel;
    private boolean active;

    public GroupView(final GroupController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        groupPanel = new GroupPanel();
    }

    @Override
    protected void handleEvent(final AppEvent event) {
        final EventType eventType = event.getType();

        if (eventType.equals(AdminEvents.GroupSettingSelected)) {
            onGroupSettingSelected(event);
        } else if (eventType.equals(AdminEvents.ShowSettingsPanel)) {
            onShowSettingsPanel(event);
        } else if (eventType.equals(AdminEvents.StudentAdded)) {
            reloadGroupPanel();
        } else if (eventType.equals(AdminEvents.StudentChanged)) {
            reloadGroupPanel();
        } else if (eventType.equals(AdminEvents.StudentsDeleted)) {
            reloadGroupPanel();
        }
    }

    private void onGroupSettingSelected(final AppEvent event) {
        Dispatcher.forwardEvent(AdminEvents.ShowSettingsPanel, groupPanel);
        groupPanel.reload(event.<GroupModel>getData());
    }

    private void onShowSettingsPanel(final AppEvent event) {
        active = groupPanel == event.getData();
    }

    private void reloadGroupPanel() {
        if (active) {
            groupPanel.reload();
        }
    }
}
