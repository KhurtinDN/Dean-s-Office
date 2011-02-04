package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.Window;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 5:43 PM
 */
public class StudentInfoLayoutContainer extends LayoutContainer {
    private StudentModel currentStudentModel;

    private LabelField nameLabelField;
    private LabelField studentIdNumberLabelField;
    private LabelField divisionLabelField;
    private LabelField studyFormLabelField;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

//        setLayout(new VBoxLayout(VBoxLayout.VBoxLayoutAlign.STRETCH));

        FieldSet infoFieldSet = new FieldSet();
        FormLayout infoFormLayout = new FormLayout();
        infoFormLayout.setLabelWidth(140);
        infoFieldSet.setLayout(infoFormLayout);
        infoFieldSet.setHeading("Инфомация о студенте");

        nameLabelField = new LabelField();
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        studentIdNumberLabelField = new LabelField();
        studentIdNumberLabelField.setName("studentIdNumber");
        studentIdNumberLabelField.setFieldLabel("Номер студенческого:");
        studentIdNumberLabelField.setLabelStyle("font-weight: bold");
        studentIdNumberLabelField.setAutoWidth(true);

        divisionLabelField = new LabelField();
        divisionLabelField.setName("division");
        divisionLabelField.setFieldLabel("Отделение:");
        divisionLabelField.setLabelStyle("font-weight: bold");
        divisionLabelField.setAutoWidth(true);

        studyFormLabelField = new LabelField();
        studyFormLabelField.setName("studyForm");
        studyFormLabelField.setFieldLabel("Форма обучения:");
        studyFormLabelField.setLabelStyle("font-weight: bold");
        studyFormLabelField.setAutoWidth(true);

        infoFieldSet.add(nameLabelField);
        infoFieldSet.add(studentIdNumberLabelField);
        infoFieldSet.add(divisionLabelField);
        infoFieldSet.add(studyFormLabelField);

        FieldSet documentFieldSet = new FieldSet();
        documentFieldSet.setLayout(new FlowLayout());
        documentFieldSet.setHeading("Документы");

        documentFieldSet.add(createReference1(), new FlowData(0, 0, 0, 0));
        documentFieldSet.add(createReference2(), new FlowData(10, 0, 0, 0));
        documentFieldSet.add(createStudentAccount(), new FlowData(10, 0, 0, 0));

        add(infoFieldSet, new VBoxLayoutData(new Margins(5)));
        add(documentFieldSet, new VBoxLayoutData(new Margins(5)));

        layout(true);
    }

    @SuppressWarnings({"GWTStyleCheck"})
    private LayoutContainer createDocument(String labelText, String buttonText, SelectionListener<ButtonEvent> sl) {
        HBoxLayout hBoxLayout = new HBoxLayout();
        hBoxLayout.setPadding(new Padding(5));

        LayoutContainer layoutContainer = new LayoutContainer(hBoxLayout);
        layoutContainer.setBorders(true);

        Label referenceLabel = new Label(labelText);
        referenceLabel.addStyleName("x-form-item");
        referenceLabel.setStyleAttribute("fontWeight", "bold");
        layoutContainer.add(referenceLabel);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        layoutContainer.add(new Text(), flex);

        layoutContainer.add(new Button(buttonText, sl));

        return layoutContainer;
    }

    private LayoutContainer createReference1() {
        return createDocument("Справка #1", "Сгенерировать в PDF", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (currentStudentModel != null) {
                    String url = "../documents/reference-1.pdf?studentId=" + currentStudentModel.getId();
                    Window.open(url, "_blank", "");
                } else {
                    MessageBox messageBox = new MessageBox();
                    messageBox.setButtons(MessageBox.OK);
                    messageBox.setTitle("Внимание!");
                    messageBox.setMessage("Выберите, пожалуйста, студента.");
                    messageBox.show();
                }
            }
        });
    }

    private LayoutContainer createReference2() {
        return createDocument("Справка #2", "Сгенерировать в PDF", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (currentStudentModel != null) {
                    String url = "../documents/reference-2.pdf?studentId=" + currentStudentModel.getId();
                    Window.open(url, "_blank", "");
                } else {
                    MessageBox messageBox = new MessageBox();
                    messageBox.setButtons(MessageBox.OK);
                    messageBox.setTitle("Внимание!");
                    messageBox.setMessage("Выберите, пожалуйста, студента.");
                    messageBox.show();
                }

            }
        });
    }

    private LayoutContainer createStudentAccount() {
        return createDocument("Учетная карточка студента", "Посмотреть", new SelectionListener<ButtonEvent>() {
            private StudentAccountWindow studentAccountWindow;

            private StudentAccountWindow getStudentAccountWindow() {
                if (studentAccountWindow == null) {
                    studentAccountWindow = new StudentAccountWindow();
                }
                return studentAccountWindow;
            }

            @Override
            public void componentSelected(ButtonEvent ce) {
                getStudentAccountWindow().show();
            }
        });
    }

    public void bind(StudentModel studentModel) {
        this.currentStudentModel = studentModel;

        if (isRendered()) {
            nameLabelField.setText(studentModel.getName());
            studentIdNumberLabelField.setText(studentModel.getStudentIdNumber());
            divisionLabelField.setText(studentModel.getDivision());
            studyFormLabelField.setText(studentModel.getStudyForm());
        } else {
            Info.display("DEBUG", "StudentInfoLayoutContainer is not rendered!");
        }
    }
}
