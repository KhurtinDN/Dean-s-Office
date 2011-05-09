package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.controllers.AdminController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 8:26 PM
 */
public class AdminView extends View {
    private Viewport viewport;
    private ContentPanel viewportPanel;

    private BorderLayoutData centerLayoutData;

    private ContentPanel centralPanel;
    private UserPanel usersPanel;
    private StaffPanel staffPanel;
    private FacultiesPanel facultiesPanel;
    private FacultyPanel facultyPanel;
    private SpecialityPanel specialityPanel;

    public AdminView(AdminController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        viewport = new Viewport();
        viewportPanel = new ContentPanel();

        centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(AdminEvents.UIReady)) {
            onUIReady();
        } else if (eventType.equals(AdminEvents.MenuBarReady)) {
            onMenuBarReady(event);
        } else if (eventType.equals(AdminEvents.StatusBarReady)) {
            onStatusBarReady(event);
        } else if (eventType.equals(AdminEvents.NavigationPanelReady)) {
            onNavigationPanelReady(event);
        } else if (eventType.equals(AdminEvents.UsersSettingSelected)) {
            onUsersSettingSelected();
        } else if (eventType.equals(AdminEvents.StaffSettingSelected)) {
            onStaffSettingSelected();
        } else if (eventType.equals(AdminEvents.FacultiesSettingSelected)) {
            onFacultiesSettingSelected();
        } else if (eventType.equals(AdminEvents.FacultySettingSelected)) {
            onFacultySettingSelected(event);
        } else if (eventType.equals(AdminEvents.SpecialitySettingSelected)) {
            onSpecialitySettingSelected(event);
        }
    }

    private void onInit() {
        viewportPanel.setHeaderVisible(false);
        viewportPanel.setLayout(new BorderLayout());

        viewport.setLayout(new FitLayout());
        viewport.add(viewportPanel);
    }

    private void onUIReady() {
        setCentralPanel(new FormPanel());
        RootLayoutPanel.get().add(viewport);
    }

    private void onMenuBarReady(AppEvent event) {
        viewportPanel.setTopComponent(event.<Component>getData());
    }

    private void onStatusBarReady(AppEvent event) {
        viewportPanel.setBottomComponent(event.<Component>getData());
    }

    private void onNavigationPanelReady(AppEvent event) {
        BorderLayoutData westLayoutData = new BorderLayoutData(Style.LayoutRegion.WEST, 300, 200, 500);
        westLayoutData.setCollapsible(true);
        westLayoutData.setSplit(true);

        ContentPanel contentPanel = event.getData();
        viewportPanel.add(contentPanel, westLayoutData);
    }

    private void onUsersSettingSelected() {
        if (usersPanel == null) {
            usersPanel = new UserPanel();
        }
        setCentralPanel(usersPanel);
    }

    private void onStaffSettingSelected() {
        if (staffPanel == null) {
            staffPanel = new StaffPanel();
        }
        setCentralPanel(staffPanel);
    }

    private void onFacultiesSettingSelected() {
        if (facultiesPanel == null) {
            facultiesPanel = new FacultiesPanel();
        }
        setCentralPanel(facultiesPanel);
    }

    private void onFacultySettingSelected(AppEvent event) {
        if (facultyPanel == null) {
            facultyPanel = new FacultyPanel();
        }
        facultyPanel.bind(event.<FacultyModel>getData());
        setCentralPanel(facultyPanel);
    }

    private void onSpecialitySettingSelected(AppEvent event) {
        if (specialityPanel == null) {
            specialityPanel = new SpecialityPanel();
        }
        specialityPanel.bind(event.<SpecialityModel>getData());
        setCentralPanel(specialityPanel);
    }

    private void setCentralPanel(ContentPanel panel) {
        if (panel != centralPanel) {
            if (centralPanel != null) {
                viewportPanel.remove(centralPanel);
            }
            centralPanel = panel;
            viewportPanel.add(centralPanel, centerLayoutData);
            viewportPanel.layout(true);
        }
    }
}
