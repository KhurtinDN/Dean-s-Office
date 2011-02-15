package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.AppController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.AppService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.FacultyModel;

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

        if (eventType.equals(AppEvents.Init)) {
            onInit(event);
        } else if (eventType.equals(AppEvents.Error)) {
            onError(event);
        } else if (eventType.equals(AppEvents.NavigationPanelReady)) {
            onNavigationPanelReady(event);
        } else if (eventType.equals(AppEvents.StudentsPanelReady)) {
            onStudentsPanelReady(event);
        } else if (eventType.equals(AppEvents.InformationPanelReady)) {
            onInformationPanelReady(event);
        } else if (eventType.equals(AppEvents.MenuBarReady)) {
            onMenuBarReady(event);
        } else if (eventType.equals(AppEvents.StatusBarReady)) {
            onStatusBarReady(event);
        } else if (eventType.equals(AppEvents.UIReady)) {
            onUIReady(event);
        }
    }

    private void onInit(AppEvent event) {
        viewportPanel.setHeaderVisible(false);
        viewportPanel.setLayout(new BorderLayout());

        viewport.setLayout(new FitLayout());
        viewport.add(viewportPanel);
    }

    private void onError(AppEvent event) {
        ErrorCode code = event.getData();

        switch (code) {
            case ServerReturnError:
                Info.display("Важное сообщение", "Сервер не доступен");
                break;
            case DebugInformation:
                String message = event.getData("message");
                Info.display("DEBUG", message);
                break;
            default:
                Info.display("Ошибка", "Неизвестная ошибка!");
        }
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
        Component component = event.getData();
        if (component != null) {
            viewportPanel.setTopComponent(component);
        }
    }

    private void onStatusBarReady(AppEvent event) {
        Component component = event.getData();
        if (component != null) {
            viewportPanel.setBottomComponent(component);
        }
    }

    private void onUIReady(AppEvent event) {
        RootLayoutPanel.get().add(viewport);

        AppService.App.getInstance().loadFaculty(new AsyncCallback<FacultyModel>() {
            @Override
            public void onFailure(Throwable caught) {
                Info.display("Важное сообщение", "Сервер не доступен");
            }

            @Override
            public void onSuccess(FacultyModel facultyModel) {
                Dispatcher.forwardEvent(AppEvents.FacultySelected, facultyModel);
            }
        });
    }
}
