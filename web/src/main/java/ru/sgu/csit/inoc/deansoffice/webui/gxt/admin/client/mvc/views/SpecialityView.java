package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.SpecialityPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.SpecialityController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

/**
 * @author Denis Khurtin
 */
public class SpecialityView extends View {
    private SpecialityPanel specialityPanel;
    private boolean active;

    public SpecialityView(SpecialityController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        specialityPanel = new SpecialityPanel();
    }

    @Override
    protected void handleEvent(final AppEvent event) {
        final EventType eventType = event.getType();

        if (eventType.equals(AdminEvents.SpecialitySettingSelected)) {
            onSpecialitySettingSelected(event);
        } else if (eventType.equals(AdminEvents.ShowSettingsPanel)) {
            onShowSettingsPanel(event);
        } else if (eventType.equals(AdminEvents.GroupAdded)) {
            reloadSpecialityPanel();
        } else if (eventType.equals(AdminEvents.GroupChanged)) {
            reloadSpecialityPanel();
        } else if (eventType.equals(AdminEvents.GroupsDeleted)) {
            reloadSpecialityPanel();
        }
    }

    private void onSpecialitySettingSelected(final AppEvent event) {
        Dispatcher.forwardEvent(AdminEvents.ShowSettingsPanel, specialityPanel);
        specialityPanel.reload(event.<SpecialityModel>getData());
    }

    private void onShowSettingsPanel(final AppEvent event) {
        active = specialityPanel == event.getData();
    }

    private void reloadSpecialityPanel() {
        if (active) {
            specialityPanel.reload();
        }
    }
}
