package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.StudentModelUtil;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 5:43 PM
 */
public class StudentInfoLayoutContainer extends LayoutContainer {
    private StudentModel currentStudentModel;

    private Image image;
    private LabelField nameLabelField;
    private LabelField groupLabelField;
    private LabelField specialityLabelField;
    private LabelField courseLabelField;
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

        image = new Image();
//        image.setPixelSize(100, 100);
        image.setHeight("100px");

        nameLabelField = new LabelField();
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        groupLabelField = new LabelField();
        groupLabelField.setName("group");
        groupLabelField.setFieldLabel("Группа:");
        groupLabelField.setLabelStyle("font-weight: bold");
        groupLabelField.setAutoWidth(true);

        specialityLabelField = new LabelField();
        specialityLabelField.setName("speciality");
        specialityLabelField.setFieldLabel("Специальность:");
        specialityLabelField.setLabelStyle("font-weight: bold");
        specialityLabelField.setAutoWidth(true);

        courseLabelField = new LabelField();
        courseLabelField.setName("course");
        courseLabelField.setFieldLabel("Курс:");
        courseLabelField.setLabelStyle("font-weight: bold");
        courseLabelField.setAutoWidth(true);

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

        infoFieldSet.add(image);
        infoFieldSet.add(nameLabelField);
        infoFieldSet.add(groupLabelField);
        infoFieldSet.add(specialityLabelField);
        infoFieldSet.add(courseLabelField);
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
                if (currentStudentModel != null) {
                    getStudentAccountWindow().showStudentAccount(currentStudentModel.getId());
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

    public void bind(StudentModel studentModel) {
        this.currentStudentModel = studentModel;

        if (isRendered()) {
            image.setUrl("photos/" + studentModel.getPhotoId() + ".jpg");
            nameLabelField.setText(StudentModelUtil.getFullName(studentModel));
            groupLabelField.setText(studentModel.getGroup().getName());
            specialityLabelField.setText(studentModel.getSpeciality().getFullName());
            courseLabelField.setText(studentModel.getCourse().toString());
            studentIdNumberLabelField.setText(studentModel.getStudentIdNumber());
            divisionLabelField.setText(StudentModelUtil.divisionToString(studentModel.getDivision()));
            studyFormLabelField.setText(StudentModelUtil.studyFormToString(studentModel.getStudyForm()));
        } else {
            AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.DebugInformation);
            appEvent.setData("message", "StudentInfoLayoutContainer is not rendered!");
            Dispatcher.forwardEvent(appEvent);
        }
    }
}
