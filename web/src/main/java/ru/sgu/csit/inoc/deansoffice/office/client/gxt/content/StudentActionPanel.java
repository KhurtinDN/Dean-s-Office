package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.StudentDto;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 7, 2010
 * Time: 11:56:34 PM
 */
public class StudentActionPanel extends ContentPanel {
    private StudentGrid studentGrid;

    public StudentActionPanel(StudentGrid studentGrid) {
        this.studentGrid = studentGrid;
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setHeading("Документы");

        Button reference1Button = new Button("Справка #1", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentDto selectedStudentItem = studentGrid.getSelectionModel().getSelectedItem();
                if (selectedStudentItem != null) {
                    Long studentId = selectedStudentItem.get("id");
                    String url = "/documents/reference-1.pdf?studentId=" + studentId;
                    Window.open(url, "_blank", "");
                } else {
                    Window.alert("Выберите, пожалуйста, студента");
                }
            }
        });

        Button reference2Button = new Button("Справка #2", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentDto selectedStudentItem = studentGrid.getSelectionModel().getSelectedItem();
                if (selectedStudentItem != null) {
                    Long studentId = selectedStudentItem.get("id");
                    String url = "/documents/reference-2.pdf?studentId=" + studentId;
                    Window.open(url, "_blank", "");
                } else {
                    Window.alert("Выберите, пожалуйста, студента");
                }
            }
        });

        RowData rowData = new RowData();
        rowData.setMargins(new Margins(10, 0, 0, 10));
        add(reference1Button, rowData);
        add(reference2Button, rowData);
    }
}
