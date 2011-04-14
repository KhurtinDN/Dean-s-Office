package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.info;

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
import com.google.gwt.user.client.ui.Image;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.StudentAccountWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.StudentModelUtil;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 5:43 PM
 */
public class StudentInfoLayoutContainer extends LayoutContainer {
    private StudentModel currentStudentModel;

    private Image image = new Image();
    private LabelField nameLabelField = new LabelField();
    private LabelField groupNameLabelField = new LabelField();
    private LabelField specialityNameLabelField = new LabelField();
    private LabelField courseLabelField = new LabelField();
    private LabelField studentIdNumberLabelField = new LabelField();
    private LabelField divisionLabelField = new LabelField();
    private LabelField studyFormLabelField = new LabelField();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

//        setLayout(new VBoxLayout(VBoxLayout.VBoxLayoutAlign.STRETCH));

        FieldSet infoFieldSet = new FieldSet();
        FormLayout infoFormLayout = new FormLayout();
        infoFormLayout.setLabelWidth(140);
        infoFieldSet.setLayout(infoFormLayout);
        infoFieldSet.setHeading("Информация о студенте");

//        image.setPixelSize(100, 100);
        image.setHeight("100px");

        nameLabelField.setName("fullName");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        groupNameLabelField.setName("groupName");
        groupNameLabelField.setFieldLabel("Группа:");
        groupNameLabelField.setLabelStyle("font-weight: bold");
        groupNameLabelField.setAutoWidth(true);

        specialityNameLabelField.setName("specialityName");
        specialityNameLabelField.setFieldLabel("Специальность:");
        specialityNameLabelField.setLabelStyle("font-weight: bold");
        specialityNameLabelField.setAutoWidth(true);

        courseLabelField.setName("course");
        courseLabelField.setFieldLabel("Курс:");
        courseLabelField.setLabelStyle("font-weight: bold");
        courseLabelField.setAutoWidth(true);

        studentIdNumberLabelField.setName("studentIdNumber");
        studentIdNumberLabelField.setFieldLabel("Номер студенческого:");
        studentIdNumberLabelField.setLabelStyle("font-weight: bold");
        studentIdNumberLabelField.setAutoWidth(true);

        divisionLabelField.setName("division");
        divisionLabelField.setFieldLabel("Отделение:");
        divisionLabelField.setLabelStyle("font-weight: bold");
        divisionLabelField.setAutoWidth(true);

        studyFormLabelField.setName("studyForm");
        studyFormLabelField.setFieldLabel("Форма обучения:");
        studyFormLabelField.setLabelStyle("font-weight: bold");
        studyFormLabelField.setAutoWidth(true);

        infoFieldSet.add(image);
        infoFieldSet.add(nameLabelField);
        infoFieldSet.add(groupNameLabelField);
        infoFieldSet.add(specialityNameLabelField);
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
        HBoxLayout hBoxLayout = new HBoxLayout();
        hBoxLayout.setPadding(new Padding(5));

        LayoutContainer layoutContainer = new LayoutContainer(hBoxLayout);
        layoutContainer.setBorders(true);

        Label referenceLabel = new Label("Справка #1");
        referenceLabel.addStyleName("x-form-item");
        referenceLabel.setStyleAttribute("fontWeight", "bold");
        layoutContainer.add(referenceLabel);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        layoutContainer.add(new Text(), flex);

        layoutContainer.add(new Button("Добавить в очередь", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (currentStudentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_1);
                    appEvent.setData("ownerId", currentStudentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
//                    String url = "../documents/reference-1.pdf?studentId=" + currentStudentModel.getId();
//                    Window.open(url, "_blank", "");
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation,
                            "GroupInfoLayoutContainer is not rendered!");
                }
            }
        }), new HBoxLayoutData(0, 5, 0, 5));

        layoutContainer.add(new Button("Добавить в очередь как...", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (currentStudentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_1);
                    appEvent.setData("ownerId", currentStudentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
                    Dispatcher.forwardEvent(AppEvents.ReferenceQueueCall, Boolean.TRUE);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation,
                            "GroupInfoLayoutContainer is not rendered!");
                }
            }
        }));

        return layoutContainer;
    }

    private LayoutContainer createReference2() {
        HBoxLayout hBoxLayout = new HBoxLayout();
        hBoxLayout.setPadding(new Padding(5));

        LayoutContainer layoutContainer = new LayoutContainer(hBoxLayout);
        layoutContainer.setBorders(true);

        Label referenceLabel = new Label("Справка #2");
        referenceLabel.addStyleName("x-form-item");
        referenceLabel.setStyleAttribute("fontWeight", "bold");
        layoutContainer.add(referenceLabel);

        HBoxLayoutData flex = new HBoxLayoutData(new Margins(0, 5, 0, 0));
        flex.setFlex(1);
        layoutContainer.add(new Text(), flex);

        layoutContainer.add(new Button("Добавить в очередь", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (currentStudentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_2);
                    appEvent.setData("ownerId", currentStudentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
//                    String url = "../documents/reference-2.pdf?studentId=" + currentStudentModel.getId();
//                    Window.open(url, "_blank", "");
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation,
                            "GroupInfoLayoutContainer is not rendered!");
                }
            }
        }), new HBoxLayoutData(0, 5, 0, 5));

        layoutContainer.add(new Button("Добавить в очередь как...", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (currentStudentModel != null) {
                    AppEvent appEvent = new AppEvent(AppEvents.RegistrationReference);
                    appEvent.setData("referenceType", ReferenceModel.ReferenceType.REFERENCE_2);
                    appEvent.setData("ownerId", currentStudentModel.getId());

                    Dispatcher.forwardEvent(appEvent);
                    Dispatcher.forwardEvent(AppEvents.ReferenceQueueCall, Boolean.TRUE);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation,
                            "GroupInfoLayoutContainer is not rendered!");
                }
            }
        }));

        return layoutContainer;
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
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите, пожалуйста, студента!");
                }
            }
        });
    }

    public void bind(StudentModel studentModel) {
        this.currentStudentModel = studentModel;

        image.setUrl("photos/" + studentModel.getPhotoId() + ".jpg");
        nameLabelField.setText(studentModel.getFullName());
        groupNameLabelField.setText(studentModel.getGroupName());
        specialityNameLabelField.setText(studentModel.getSpecialityName());
        courseLabelField.setText(studentModel.getCourse().toString());
        studentIdNumberLabelField.setText(studentModel.getStudentIdNumber());
        divisionLabelField.setText(StudentModelUtil.divisionToString(studentModel.getDivision()));
        studyFormLabelField.setText(StudentModelUtil.studyFormToString(studentModel.getStudyForm()));
    }
}
