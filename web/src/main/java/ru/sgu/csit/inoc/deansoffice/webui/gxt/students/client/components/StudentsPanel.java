package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.StudentService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.StudentModelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/31/11
 * Time: 11:16 AM
 */
public class StudentsPanel extends ContentPanel {
    private ListStore<StudentModel> studentListStore = new ListStore<StudentModel>();
    private StudentAsyncCallback studentAsyncCallback = new StudentAsyncCallback();

    public StudentsPanel() {
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Студенты");
        setLayout(new FitLayout());

        studentListStore.sort("fullName", Style.SortDir.ASC);
        Grid<StudentModel> grid = new Grid<StudentModel>(studentListStore, createColumnModel());
        grid.setBorders(true);
        grid.setAutoExpandColumn("name");
        grid.setAutoExpandMax(2000);

        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<StudentModel>>() {
                    @Override
                    public void handleEvent(SelectionChangedEvent<StudentModel> be) {
                        StudentModel studentModel = be.getSelectedItem();
                        if (studentModel != null) {
                            Dispatcher.forwardEvent(AppEvents.StudentSelected, studentModel);
                        }
                    }
                });

        add(grid);
    }

    private ColumnModel createColumnModel() {
        ColumnConfig nnColumnConfig = new ColumnConfig("nn", "#", 40);
        nnColumnConfig.setSortable(false);
        nnColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return rowIndex + 1;
            }
        });

        ColumnConfig nameColumnConfig = new ColumnConfig("name", "Имя", 200);
        nameColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return StudentModelUtil.getFullName((StudentModel) model);
            }
        });

        ColumnConfig studyFormColumnConfig = new ColumnConfig("studyForm", "Форма обучения", 100);
        studyFormColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return StudentModelUtil.studyFormToString(((StudentModel) model).getStudyForm());
            }
        });

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(studyFormColumnConfig);

        return new ColumnModel(columns);
    }

    public void showGroup(GroupModel groupModel) {
        StudentService.App.getInstance().loadStudentList(groupModel.getId(), studentAsyncCallback);
    }

    public void showSpecialityByCourse(SpecialityModel specialityModel, Integer course) {
        StudentService.App.getInstance().loadStudentListBySpecialityIdAndCourse(specialityModel.getId(), course,
                studentAsyncCallback);
    }

    private class StudentAsyncCallback implements AsyncCallback<List<StudentModel>> {
        @Override
        public void onFailure(Throwable caught) {
            AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
            appEvent.setData("throwable", caught);
            Dispatcher.forwardEvent(appEvent);
        }

        @Override
        public void onSuccess(List<StudentModel> students) {
            studentListStore.removeAll();
            studentListStore.add(students);
        }
    }
}
