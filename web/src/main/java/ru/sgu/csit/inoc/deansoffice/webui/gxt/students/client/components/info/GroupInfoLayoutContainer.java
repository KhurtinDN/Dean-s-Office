package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.info;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 6:02 PM
 */
public class GroupInfoLayoutContainer extends LayoutContainer {

    private LabelField nameLabelField = new LabelField();
    private LabelField specialityLabelField = new LabelField();
    private LabelField courseLabelField = new LabelField();

    private AddStudentLayoutContainer addStudentLayoutContainer = new AddStudentLayoutContainer();

    public GroupInfoLayoutContainer() {
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        specialityLabelField.setName("specialityName");
        specialityLabelField.setFieldLabel("Специальность:");
        specialityLabelField.setLabelStyle("font-weight: bold");
        specialityLabelField.setAutoWidth(true);

        courseLabelField.setName("course");
        courseLabelField.setFieldLabel("Курс:");
        courseLabelField.setLabelStyle("font-weight: bold");
        courseLabelField.setAutoWidth(true);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("Информация о группе");
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelWidth(140);
        fieldSet.setLayout(formLayout);

        Image image = new Image("icons/group128x128.png");
        image.setHeight("100px");

        fieldSet.add(image);
        fieldSet.add(nameLabelField);
        fieldSet.add(specialityLabelField);
        fieldSet.add(courseLabelField);

        add(fieldSet, new FlowData(5));
        add(addStudentLayoutContainer, new FlowData(5));
    }

    public void bind(GroupModel groupModel) {
        nameLabelField.setText(groupModel.getName());
        specialityLabelField.setText(groupModel.getSpeciality().getName());
        courseLabelField.setText(groupModel.getCourse().toString());

        addStudentLayoutContainer.bind(groupModel);
    }

    private static class AddStudentLayoutContainer extends FormPanel {
        private GroupModel currentGroupModel;

        private TextField<String> firstNameTextField = new TextField<String>();
        private TextField<String> middleNameTextField = new TextField<String>();
        private TextField<String> lastNameTextField = new TextField<String>();

        private TextField<String> firstNameGenitiveTextField = new TextField<String>();
        private TextField<String> middleNameGenitiveTextField = new TextField<String>();
        private TextField<String> lastNameGenitiveTextField = new TextField<String>();

        private TextField<String> firstNameDativeTextField = new TextField<String>();
        private TextField<String> middleNameDativeTextField = new TextField<String>();
        private TextField<String> lastNameDativeTextField = new TextField<String>();

        private ComboBox<ModelData> studyFormComboBox = new ComboBox<ModelData>();
        private ModelData budget = new BaseModel();
        private ModelData commercial = new BaseModel();

        private AddStudentLayoutContainer() {
            setFrame(true);

            FormLayout formLayout = new FormLayout();
            formLayout.setLabelWidth(150);
            setLayout(formLayout);

            lastNameTextField.setFieldLabel("Фамилия");
            firstNameTextField.setFieldLabel("Имя");
            middleNameTextField.setFieldLabel("Отчество");

            lastNameGenitiveTextField.setFieldLabel("Фамилия");
            firstNameGenitiveTextField.setFieldLabel("Имя");
            middleNameGenitiveTextField.setFieldLabel("Отчество");

            lastNameDativeTextField.setFieldLabel("Фамилия");
            firstNameDativeTextField.setFieldLabel("Имя");
            middleNameDativeTextField.setFieldLabel("Отчество");

            budget.set("title", "Бюджет");
            budget.set("studyForm", StudentModel.StudyForm.BUDGET);

            commercial.set("title", "Коммерция");
            commercial.set("studyForm", StudentModel.StudyForm.COMMERCIAL);

            ListStore<ModelData> store = new ListStore<ModelData>();
            store.add(budget);
            store.add(commercial);

            studyFormComboBox.setDisplayField("title");
            studyFormComboBox.setFieldLabel("Форма обучения");
            studyFormComboBox.setEditable(false);
            studyFormComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
            studyFormComboBox.setStore(store);
        }

        @Override
        protected void onRender(Element parent, int pos) {
            super.onRender(parent, pos);

            setHeading("Добавление нового студента");

            add(createFIOFieldSet("ФИО в именительном подеже (кто?)",
                    lastNameTextField, firstNameTextField, middleNameTextField));
            add(createFIOFieldSet("ФИО в родительном подеже (кого?)",
                    lastNameGenitiveTextField, firstNameGenitiveTextField, middleNameGenitiveTextField));
            add(createFIOFieldSet("ФИО в дательном подеже (кому?)",
                    lastNameDativeTextField, firstNameDativeTextField, middleNameDativeTextField));

            Button saveButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
            saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
                @Override
                public void componentSelected(ButtonEvent ce) {
                    final StudentModel studentModel = new StudentModel();
                    studentModel.setLastName(lastNameTextField.getValue());
                    studentModel.setFirstName(firstNameTextField.getValue());
                    studentModel.setMiddleName(middleNameTextField.getValue());

                    studentModel.setStudyForm(studyFormComboBox.getValue().<StudentModel.StudyForm>get("studyForm"));
                    studentModel.setDivision(StudentModel.Division.INTRAMURAL);

                    studentModel.setGroupName(currentGroupModel.getName());
                    studentModel.setSpecialityName(currentGroupModel.getSpeciality().getName());
                    studentModel.setCourse(currentGroupModel.getCourse());

//                    studentModel.setPhotoId(31L);

                    if (validateForm()) {
                        clearForm();

                        // todo save student
                    }
                }
            });

            add(createColumnLayoutContainer(studyFormComboBox, saveButton));
        }

        private void bind(final GroupModel groupModel) {
            currentGroupModel = groupModel;
        }

        private FieldSet createFIOFieldSet(String name, Field firstField, Field secondField, Field thirdField) {
            LayoutContainer firstLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
            firstLayoutContainer.add(firstField, FormUtil.wrFormData);

            LayoutContainer secondLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
            secondLayoutContainer.add(secondField, FormUtil.wrFormData);

            LayoutContainer thirdLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
            thirdLayoutContainer.add(thirdField, FormUtil.wFormData);

            LayoutContainer layoutContainer = new LayoutContainer(new ColumnLayout());
            layoutContainer.add(firstLayoutContainer, new ColumnData(0.333));
            layoutContainer.add(secondLayoutContainer, new ColumnData(0.333));
            layoutContainer.add(thirdLayoutContainer, new ColumnData(0.333));

            FieldSet fieldSet = new FieldSet();
            fieldSet.setHeading(name);
            fieldSet.setLayout(new FormLayout());

            fieldSet.add(layoutContainer, FormUtil.wFormData);

            return fieldSet;
        }

        private LayoutContainer createColumnLayoutContainer(ComboBox comboBox, Button button) {
            LayoutContainer studyFormLayoutContainer = new LayoutContainer(new FormLayout());
            studyFormLayoutContainer.add(comboBox, FormUtil.wrFormData);

            LayoutContainer layoutContainer = new LayoutContainer(new ColumnLayout());
            layoutContainer.add(studyFormLayoutContainer, new ColumnData(1));
            layoutContainer.add(button, new ColumnData(100));

            return layoutContainer;
        }

        private boolean validateForm() {
            if (studyFormComboBox.getValue() == null) {
                Window.alert("Введите форму обучения");
                return false;
            }

            if (lastNameTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно фамилию студента");
                return false;
            }

            if (firstNameTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно имя студента");
                return false;
            }

            if (middleNameTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно отчество студента");
                return false;
            }

            if (lastNameGenitiveTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно фамилию студента в родительном падеже");
                return false;
            }

            if (firstNameGenitiveTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно имя студента в родительном падеже");
                return false;
            }

            if (middleNameGenitiveTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно отчество студента в родительном падеже");
                return false;
            }

            if (lastNameDativeTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно фамилию студента в дательном падеже");
                return false;
            }

            if (firstNameDativeTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно имя студента в дательном падеже");
                return false;
            }

            if (middleNameDativeTextField.getValue().isEmpty()) {
                Window.alert("Введите корректно отчество студента в дательном падеже");
                return false;
            }

            return true;
        }

        private void clearForm() {
            lastNameTextField.clear();
            firstNameTextField.clear();
            middleNameTextField.clear();

            lastNameGenitiveTextField.clear();
            firstNameGenitiveTextField.clear();
            middleNameGenitiveTextField.clear();

            lastNameDativeTextField.clear();
            firstNameDativeTextField.clear();
            middleNameDativeTextField.clear();

            studyFormComboBox.clear();
        }
    }
}
