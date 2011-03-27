package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.NavigationPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.StudentsPanel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/27/11
 * Time: 2:14 PM
 */
public class AddReferenceDialog extends Window {
    private StudentsPanel studentsPanel = new StudentsPanel(true);
    private NavigationPanel navigationPanel = new NavigationPanel(studentsPanel);
    private Grid<StudentModel> studentModelGrid = studentsPanel.getStudentGrid();

    public AddReferenceDialog(final ReferenceQueueWindow referenceQueueWindow) {
        setHeading("Добавление новой справки");
        setSize(1000, 650);
        setMaximizable(true);
        setModal(true);
        setBlinkModal(true);

        addButton(new Button("Добавить справку #1", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentModel studentModel = studentModelGrid.getSelectionModel().getSelectedItem();
                if (studentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_1);
                    appEvent.setData("ownerId", studentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите, пожалуйста, студента!");
                }
            }
        }));

        addButton(new Button("Добавить справку #2", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentModel studentModel = studentModelGrid.getSelectionModel().getSelectedItem();
                if (studentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_2);
                    appEvent.setData("ownerId", studentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите, пожалуйста, студента!");
                }
            }
        }));

        /*addButton(new Button("Добавить справку #3", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentModel studentModel = studentModelGrid.getSelectionModel().getSelectedItem();
                if (studentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_3);
                    appEvent.setData("ownerId", studentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите, пожалуйста, студента!");
                }
            }
        }));*/

        addButton(new Button("Закрыть", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                AddReferenceDialog.this.hide();
                referenceQueueWindow.reload();
            }
        }));
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setLayout(new BorderLayout());

//        StudentInfoLayoutContainer studentInfoLayoutContainer = new StudentInfoLayoutContainer();

        BorderLayoutData westLayoutData = new BorderLayoutData(Style.LayoutRegion.WEST, 200, 150, 400);
        westLayoutData.setCollapsible(true);
        westLayoutData.setSplit(true);

//        BorderLayoutData eastLayoutData = new BorderLayoutData(Style.LayoutRegion.EAST, 400, 200, 600);
//        eastLayoutData.setCollapsible(true);
//        eastLayoutData.setSplit(true);

        BorderLayoutData centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);

        add(navigationPanel, westLayoutData);
        add(studentsPanel, centerLayoutData);
//        add(captainsContentPanel, eastLayoutData);

        navigationPanel.reloadMenuData();
    }
}
