package ru.sgu.csit.inoc.deansoffice.client.gxt.content;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.shared.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;


/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 12, 2010
 * Time: 10:03:41 PM
 */
public class BodyPanel extends ContentPanel {

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
    }

    private Grid<StudentItem> createStudentsGrid(String groupName, List<StudentDto> studentDtoList) {
        ListStore<StudentItem> listStore = new ListStore<StudentItem>();

        for (StudentDto student : studentDtoList) {
            StudentItem studentItem = new StudentItem(student.getName(), student.getStudentIdNumber(),
                    student.getDivision(), student.getStudyForm());
            studentItem.set("groupName", groupName);
            listStore.add(studentItem);
        }

        List<ColumnConfig> columnConfigList = new ArrayList<ColumnConfig>();
        columnConfigList.add(new ColumnConfig("name", "Имя", 400));

        ColumnModel columnModel = new ColumnModel(columnConfigList);

        //Grid<StudentItem> grid = new Grid<StudentItem>(listStore, columnModel);
        return new Grid<StudentItem>(listStore, columnModel);//grid;
    }

    private FormPanel createFormPanel() {
        FormPanel formPanel = new FormPanel();
        formPanel.setBorders(false);
        formPanel.setHeading("Информация о студенте");

        LabelField nameLabelField = new LabelField();
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("width: 150px");
        nameLabelField.setAutoWidth(true);
        formPanel.add(nameLabelField);

        LabelField studentIdNumberLabelField = new LabelField();
        studentIdNumberLabelField.setName("studentIdNumber");
        studentIdNumberLabelField.setFieldLabel("Номер студенческого:");
        studentIdNumberLabelField.setLabelStyle("width: 150px");
        studentIdNumberLabelField.setAutoWidth(true);
        formPanel.add(studentIdNumberLabelField);

        LabelField divisionLabelField = new LabelField();
        divisionLabelField.setName("division");
        divisionLabelField.setFieldLabel("Дивизия:");
        divisionLabelField.setLabelStyle("width: 150px");
        divisionLabelField.setAutoWidth(true);
        formPanel.add(divisionLabelField);

        LabelField studyFormLabelField = new LabelField();
        studyFormLabelField.setName("studyForm");
        studyFormLabelField.setFieldLabel("Форма обучения:");
        studyFormLabelField.setLabelStyle("width: 150px");
        studyFormLabelField.setAutoWidth(true);
        formPanel.add(studyFormLabelField);

        LabelField referencesLabelField = new LabelField();
        referencesLabelField.setFieldLabel("Печать справок:");
        referencesLabelField.setLabelStyle("font-weight: bold; width: 200px");
        referencesLabelField.setAutoWidth(true);
        formPanel.add(referencesLabelField);

        formPanel.add(new Button("Справка #1", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Info.display("Справки", "Справка #1");
            }
        }));

        formPanel.add(new Button("Справка #2", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Info.display("Справки", "Справка #2");
            }
        }));

        return formPanel;
    }

    public void showGroup(Long groupId, final String groupName) {
        StudentService.App.getInstance().downloadStudents(groupId, new AsyncCallback<List<StudentDto>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.toString());
            }

            @Override
            public void onSuccess(List<StudentDto> studentDtoList) {
                BodyPanel.this.removeAll();
                BodyPanel.this.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                FormPanel formPanel = createFormPanel();
                final FormBinding formBinding = new FormBinding(formPanel, true);

                Grid<StudentItem> studentsGrid = createStudentsGrid(groupName, studentDtoList);
                studentsGrid.getSelectionModel().addListener(Events.SelectionChange,
                        new Listener<SelectionChangedEvent<StudentItem>>() {
                            @Override
                            public void handleEvent(SelectionChangedEvent<StudentItem> event) {
                                if (event.getSelection().size() > 0) {
                                    formBinding.bind(event.getSelectedItem());
                                } else {
                                    formBinding.unbind();
                                }
                            }
                        });
                add(studentsGrid, new RowData(0.6, 1));
                add(formPanel, new RowData(0.4, 1));

                BodyPanel.this.getLayout().layout();
            }
        });
    }
}
