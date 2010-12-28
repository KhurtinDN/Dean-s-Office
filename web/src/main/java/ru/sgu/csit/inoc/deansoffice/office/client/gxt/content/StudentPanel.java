package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.content.StudentGrid;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.content.StudentService;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.StudentDto;

import java.util.List;

/**
 * User: KhurtinDN ( KhurtinDN@gmail.com )
 * Date: 12/27/10
 * Time: 12:45 PM
 */
public class StudentPanel extends ContentPanel {
    private StudentGrid studentGrid = new StudentGrid();

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Студенты");
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        add(studentGrid, new RowData(1, 1));
    }

    public StudentGrid getStudentGrid() {
        return studentGrid;
    }

    public void showGroup(Long groupId, final String groupName) {
        StudentService.App.getInstance().downloadStudents(groupId, new AsyncCallback<List<StudentDto>>() {
            @Override
            public void onFailure(Throwable caught) {
                Info.display("Сообщение от сервера", "Запрашиваемый сервис не доступен!");
            }

            @Override
            public void onSuccess(List<StudentDto> studentDtoList) {
                studentGrid.update(groupName, studentDtoList);
            }
        });
    }
}
