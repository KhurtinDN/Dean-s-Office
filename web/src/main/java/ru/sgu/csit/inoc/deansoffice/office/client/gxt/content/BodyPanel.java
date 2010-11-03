package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.StudentDto;

import java.util.List;


/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 12, 2010
 * Time: 10:03:41 PM
 */
public class BodyPanel extends ContentPanel {
    private StudentGrid studentsGrid;
    private StudentInfoPanel studentInfoPanel;
//    private Grid<SpecialityDto> specialitiesGrid;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        studentsGrid = new StudentGrid();
        studentInfoPanel = new StudentInfoPanel(studentsGrid);
    }

    public void showGroup(Long groupId, final String groupName) {
        StudentService.App.getInstance().downloadStudents(groupId, new AsyncCallback<List<StudentDto>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.toString());
            }

            @Override
            public void onSuccess(List<StudentDto> studentDtoList) {
                studentsGrid.update(groupName, studentDtoList);

                BodyPanel.this.removeAll();
                BodyPanel.this.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

                add(studentsGrid, new RowData(0.6, 1));
                add(studentInfoPanel, new RowData(0.4, 1));

                BodyPanel.this.getLayout().layout();
            }
        });
    }

    public void showSpeciality(Long id, String name) {
        // todo
    }
}
