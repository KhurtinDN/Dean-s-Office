package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.AppService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.AppConstants;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.AppController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 2:35 PM
 */
public class AppView extends View {
    private Viewport viewport;
    private ContentPanel viewportPanel;

    public AppView(AppController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        viewport = new Viewport();
        viewportPanel = new ContentPanel();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Init)) {
            onInit();
        } else if (eventType.equals(CommonEvents.Error)) {
            onError();
        } else if (eventType.equals(StudentEvents.NavigationPanelReady)) {
            onNavigationPanelReady(event);
        } else if (eventType.equals(StudentEvents.StudentsPanelReady)) {
            onStudentsPanelReady(event);
        } else if (eventType.equals(StudentEvents.InformationPanelReady)) {
            onInformationPanelReady(event);
        } else if (eventType.equals(StudentEvents.MenuBarReady)) {
            onMenuBarReady(event);
        } else if (eventType.equals(StudentEvents.StatusBarReady)) {
            onStatusBarReady(event);
        } else if (eventType.equals(StudentEvents.UIReady)) {
            onUIReady();
        }
    }

    private void onInit() {
        viewportPanel.setHeaderVisible(false);
        viewportPanel.setLayout(new BorderLayout());

        viewport.setLayout(new FitLayout());
        viewport.add(viewportPanel);
    }

    private void onError() {
    }

    private void onNavigationPanelReady(AppEvent event) {
        Component component = event.getData();

        if (component != null) {
            BorderLayoutData westLayoutData = new BorderLayoutData(Style.LayoutRegion.WEST, 200, 150, 400);
            westLayoutData.setCollapsible(true);
            westLayoutData.setSplit(true);

            viewportPanel.add(component, westLayoutData);
        }
    }

    private void onStudentsPanelReady(AppEvent event) {
        Component component = event.getData();

        if (component != null) {
            BorderLayoutData centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);

            viewportPanel.add(component, centerLayoutData);
        }
    }

    private void onInformationPanelReady(AppEvent event) {
        Component component = event.getData();

        if (component != null) {
            BorderLayoutData eastLayoutData = new BorderLayoutData(Style.LayoutRegion.EAST, 400, 200, 500);
            eastLayoutData.setCollapsible(true);
            eastLayoutData.setSplit(true);

            viewportPanel.add(component, eastLayoutData);
        }
    }

    private void onMenuBarReady(AppEvent event) {
        viewportPanel.setTopComponent(event.<Component>getData());
    }

    private void onStatusBarReady(AppEvent event) {
        viewportPanel.setBottomComponent(event.<Component>getData());
    }

    private void onUIReady() {
        RootLayoutPanel.get().add(viewport);

        AppService.Util.getInstance().loadActiveFaculty(new BaseAsyncCallback<FacultyModel>() {
            @Override
            public void onSuccess(FacultyModel facultyModel) {
                Registry.register(AppConstants.CURRENT_FACULTY, facultyModel);
                Dispatcher.forwardEvent(StudentEvents.FacultySelected, facultyModel);
            }
        });
    }
}
