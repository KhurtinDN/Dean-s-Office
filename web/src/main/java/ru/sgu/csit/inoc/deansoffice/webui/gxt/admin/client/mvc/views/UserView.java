package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.UserPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.UserController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;

/**
 * @author Denis Khurtin
 */
public class UserView extends View {
    private UserPanel userPanel;

    public UserView(final UserController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        userPanel = new UserPanel();
    }

    @Override
    protected void handleEvent(final AppEvent event) {
        final EventType eventType = event.getType();

        if (eventType.equals(AdminEvents.UsersSettingSelected)) {
            onUsersSettingSelected();
        } else if (eventType.equals(AdminEvents.UserAdded)) {
            userPanel.reload();
        } else if (eventType.equals(AdminEvents.UserChanged)) {
            userPanel.reload();
        } else if (eventType.equals(AdminEvents.UsersDeleted)) {
            userPanel.reload();
        }
    }

    private void onUsersSettingSelected() {
        Dispatcher.forwardEvent(AdminEvents.ShowSettingsPanel, userPanel);
        userPanel.reload();
    }
}
