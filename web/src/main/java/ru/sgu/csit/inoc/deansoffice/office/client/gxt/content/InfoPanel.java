package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.StudentDto;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 7, 2010
 * Time: 11:54:52 PM
 */
public class InfoPanel extends ContentPanel {
    private StudentGrid studentGrid;

    public InfoPanel(StudentGrid studentGrid) {
        this.studentGrid = studentGrid;
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

//        getHeader().hide();
        setHeading("Свойства");
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final FormPanel formPanel = createFormPanel();
        final FormBinding formBinding = new FormBinding(formPanel, true);

        studentGrid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<StudentDto>>() {
                    @Override
                    public void handleEvent(SelectionChangedEvent<StudentDto> event) {
                        if (event.getSelection().size() > 0) {
                            formBinding.bind(event.getSelectedItem());
                        } else {
                            formBinding.unbind();
                        }
                    }
                });

        ActionPanel actionPanel = new ActionPanel(studentGrid);

        add(formPanel, new RowData(1, 0.5));
        add(actionPanel, new RowData(1, 0.5));
    }

    private FormPanel createFormPanel() {
        FormPanel formPanel = new FormPanel();
        formPanel.setBorders(false);
        formPanel.setHeading("Информация о студенте");

        LabelField nameLabelField = new LabelField();
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold; width: 150px");
        nameLabelField.setAutoWidth(true);

        LabelField studentIdNumberLabelField = new LabelField();
        studentIdNumberLabelField.setName("studentIdNumber");
        studentIdNumberLabelField.setFieldLabel("Номер студенческого:");
        studentIdNumberLabelField.setLabelStyle("font-weight: bold; width: 150px");
        studentIdNumberLabelField.setAutoWidth(true);

        LabelField divisionLabelField = new LabelField();
        divisionLabelField.setName("division");
        divisionLabelField.setFieldLabel("Отделение:");
        divisionLabelField.setLabelStyle("font-weight: bold; width: 150px");
        divisionLabelField.setAutoWidth(true);

        LabelField studyFormLabelField = new LabelField();
        studyFormLabelField.setName("studyForm");
        studyFormLabelField.setFieldLabel("Форма обучения:");
        studyFormLabelField.setLabelStyle("font-weight: bold; width: 150px");
        studyFormLabelField.setAutoWidth(true);

        formPanel.add(nameLabelField);
        formPanel.add(studentIdNumberLabelField);
        formPanel.add(divisionLabelField);
        formPanel.add(studyFormLabelField);

        return formPanel;
    }
}
