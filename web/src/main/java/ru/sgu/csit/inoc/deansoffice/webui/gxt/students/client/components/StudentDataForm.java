package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.google.gwt.i18n.client.DateTimeFormat;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.util.PassportModelUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.util.PersonModelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 1:32 PM
 */
public class StudentDataForm extends FormPanel {
    private StudentDetailsModel studentDetailsModel;

    private Grid<PassportModel> passportGrid;
    private ListStore<PassportModel> passportListStore = new ListStore<PassportModel>();

    private ModelData maleSex = new BaseModel();
    private ModelData femaleSex = new BaseModel();
    private ComboBox<ModelData> sexComboBox = new ComboBox<ModelData>();

    private DateField birthdayDateField = new DateField();
    private TextField<String> birthplaceTextField = new TextField<String>();
    private TextField<String> citizenshipTextField = new TextField<String>();

    private TextArea educationTextField = new TextArea();
    private TextArea workInfoTextField = new TextArea();
    private TextArea maritalStatusTextField = new TextArea();
    private TextArea childrenInfoTextField = new TextArea();

    private TextField<String> lastNameFatherTextField = new TextField<String>();
    private TextField<String> firstNameFatherTextField = new TextField<String>();
    private TextField<String> middleNameFatherTextField = new TextField<String>();
    private DateField birthdayDateFatherField = new DateField();
    private TextArea addressFatherTextField = new TextArea();
    private TextField<String> workInfoFatherTextField = new TextField<String>();
    private TextField<String> phonesFatherTextField = new TextField<String>();

    private TextField<String> lastNameMotherTextField = new TextField<String>();
    private TextField<String> firstNameMotherTextField = new TextField<String>();
    private TextField<String> middleNameMotherTextField = new TextField<String>();
    private DateField birthdayDateMotherField = new DateField();
    private TextArea addressMotherTextField = new TextArea();
    private TextField<String> workInfoMotherTextField = new TextField<String>();
    private TextField<String> phonesMotherTextField = new TextField<String>();

    private TextArea oldAddressTextField = new TextArea();
    private TextArea passportAddressTextField = new TextArea();
    private TextArea actualAddressTextField = new TextArea();

    private FormData wFormData = new FormData("100%");
    private FormData wh10FormData = new FormData("100%");
    private FormData wh5FormData = new FormData("100%");
    private FormData wrFormData = new FormData("100%");

    public StudentDataForm() {
        setLayout(new FormLayout(LabelAlign.TOP));
        setHeaderVisible(false);
        setFrame(true);
        setBorders(false);
        setSize("100%", "100%");

        maleSex.set("sex", PersonModel.Sex.MALE);
        maleSex.set("title", PersonModelUtil.sexToString(PersonModel.Sex.MALE));

        femaleSex.set("sex", PersonModel.Sex.FEMALE);
        femaleSex.set("title", PersonModelUtil.sexToString(PersonModel.Sex.FEMALE));

        ListStore<ModelData> store = new ListStore<ModelData>();
        store.add(maleSex);
        store.add(femaleSex);

        sexComboBox.setEditable(false);
        sexComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        sexComboBox.setDisplayField("title");
        sexComboBox.setStore(store);

        educationTextField.setHeight(35);
        workInfoTextField.setHeight(35);
        maritalStatusTextField.setHeight(35);
        childrenInfoTextField.setHeight(35);

        oldAddressTextField.setHeight(35);
        passportAddressTextField.setHeight(35);
        actualAddressTextField.setHeight(35);

        addressFatherTextField.setHeight(35);
        addressMotherTextField.setHeight(35);

        wh10FormData.setMargins(new Margins(0, 0, 10, 0));
        wh5FormData.setMargins(new Margins(0, 0, 5, 0));
        wrFormData.setMargins(new Margins(0, 20, 0, 0));

        setButtonAlign(Style.HorizontalAlignment.CENTER);

        LayoutContainer columnLayoutContainer = new LayoutContainer(new ColumnLayout());
        columnLayoutContainer.add(createLeftLayoutContainer(), new ColumnData(0.5));
        columnLayoutContainer.add(createRightLayoutContainer(), new ColumnData(0.5));

        FieldSet addressFieldSet = new FieldSet();
        addressFieldSet.setLayout(new FormLayout());
        addressFieldSet.setHeading("Домашний адрес");

        oldAddressTextField.setFieldLabel("До поступления в ВУЗ");
        passportAddressTextField.setFieldLabel("В соответствии с данными паспорта");
        actualAddressTextField.setFieldLabel("Фактический адрес");

        addressFieldSet.add(createTripleFieldContainer(oldAddressTextField, passportAddressTextField,
                actualAddressTextField), wFormData);

        add(columnLayoutContainer, wFormData);
        add(addressFieldSet, wFormData);
        add(createParentLayoutContainer(), wFormData);
    }

    private LayoutContainer createLeftLayoutContainer() {
        LayoutContainer leftLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        leftLayoutContainer.setStyleAttribute("paddingRight", "10px");

        sexComboBox.setFieldLabel("Пол");
        citizenshipTextField.setFieldLabel("Гражданство");
        birthdayDateField.setFieldLabel("Дата рождения");
        birthplaceTextField.setFieldLabel("Место рождения");

        LayoutContainer passportLayoutContainer = createPassportLayoutContainer();

        // leftLayoutContainer.add(createDoubleFieldContainer(sexComboBox, citizenshipTextField), wh10FormData);
        // leftLayoutContainer.add(createDoubleFieldContainer(birthdayDateField, birthplaceTextField), wh10FormData);
        LayoutContainer firstLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        firstLayoutContainer.add(sexComboBox, wrFormData);

        LayoutContainer secondLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        secondLayoutContainer.add(birthdayDateField, wrFormData);

        LayoutContainer thirdLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        thirdLayoutContainer.add(citizenshipTextField, wrFormData);

        LayoutContainer fourthLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        fourthLayoutContainer.add(birthplaceTextField, wFormData);

        LayoutContainer layoutContainer = new LayoutContainer(new ColumnLayout());
        layoutContainer.add(firstLayoutContainer, new ColumnData(0.18));
        layoutContainer.add(secondLayoutContainer, new ColumnData(0.2));
        layoutContainer.add(thirdLayoutContainer, new ColumnData(0.25));
        layoutContainer.add(fourthLayoutContainer, new ColumnData(0.37));

        leftLayoutContainer.add(layoutContainer, wh10FormData);

        leftLayoutContainer.add(passportLayoutContainer, wh10FormData);

        return leftLayoutContainer;
    }

    private LayoutContainer createPassportLayoutContainer () {
        ContentPanel passportContentPanel = new ContentPanel();
        passportContentPanel.setBorders(true);
        passportContentPanel.setLayout(new FitLayout());
        passportContentPanel.setHeading("Паспортные данные");
        passportContentPanel.setHeight(190);

        List<ColumnConfig> columnConfigList = new ArrayList<ColumnConfig>();

        CheckBoxSelectionModel<PassportModel> checkBoxSelectionModel = new CheckBoxSelectionModel<PassportModel>();
        checkBoxSelectionModel.setSelectionMode(Style.SelectionMode.SINGLE);

        columnConfigList.add(checkBoxSelectionModel.getColumn());

        ColumnConfig columnConfig = new ColumnConfig("lastName", "Фамилия", 100);
        columnConfig.setEditor(new CellEditor(new TextField<String>()));
        columnConfigList.add(columnConfig);

        columnConfig = new ColumnConfig("firstName", "Имя", 100);
        columnConfig.setEditor(new CellEditor(new TextField<String>()));
        columnConfigList.add(columnConfig);

        columnConfig = new ColumnConfig("middleName", "Отчество", 100);
        columnConfig.setEditor(new CellEditor(new TextField<String>()));
        columnConfigList.add(columnConfig);

        columnConfig = new ColumnConfig("series", "Серия", 50);
        columnConfig.setEditor(new CellEditor(new TextField<String>()));
        columnConfigList.add(columnConfig);

        columnConfig = new ColumnConfig("number", "Номер", 55);
        columnConfig.setEditor(new CellEditor(new TextField<String>()));
        columnConfigList.add(columnConfig);

        columnConfig = new ColumnConfig("issuedDate", "Когда выдан", 80);
        columnConfig.setEditor(new CellEditor(new DateField()));
        columnConfig.setDateTimeFormat(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM));
        columnConfigList.add(columnConfig);

        columnConfig = new ColumnConfig("issuingOrganization", "Кем выдан", 100);
        columnConfig.setEditor(new CellEditor(new TextField<String>()));
        columnConfigList.add(columnConfig);

        ColumnModel columnModel = new ColumnModel(columnConfigList);
        passportGrid = new Grid<PassportModel>(passportListStore, columnModel);
        passportGrid.setSelectionModel(checkBoxSelectionModel);
        passportGrid.setColumnReordering(true);
        passportGrid.addPlugin(checkBoxSelectionModel);
        passportGrid.setAutoExpandColumn("issuingOrganization");

        final RowEditor<PassportModel> rowEditor = new RowEditor<PassportModel>();
        rowEditor.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        passportGrid.addPlugin(rowEditor);

        passportContentPanel.add(passportGrid);

        Button addPassportButton = new Button("Добавить новый паспорт", IconHelper.createStyle("addButton-icon"));
        addPassportButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                PassportModel currentPassport =
                        PassportModelUtil.findCurrentPassport(studentDetailsModel.getPassports());

                PassportModel passportModel = ( currentPassport == null ? new PassportModel() :
                        PassportModelUtil.createCopy(currentPassport) );

                rowEditor.stopEditing(false);
                passportListStore.add(passportModel);
                rowEditor.startEditing(passportListStore.indexOf(passportModel), true);
            }
        });

        Button removePassportButton = new Button("Удалить выбранный паспорт", IconHelper.createStyle("removeButton-icon"));
        removePassportButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                PassportModel passportModel = passportGrid.getSelectionModel().getSelectedItem();
                if (passportModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, паспорт!");
                } else {
                    rowEditor.stopEditing(false);
                    passportListStore.remove(passportModel);
                }
            }
        });

        passportContentPanel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        passportContentPanel.addButton(addPassportButton);
        passportContentPanel.addButton(removePassportButton);

        return passportContentPanel;
    }

    private LayoutContainer createRightLayoutContainer() {
        LayoutContainer rightLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        rightLayoutContainer.setStyleAttribute("paddingLeft", "10px");

        educationTextField.setFieldLabel("Образование (какое учебное заведение окончил, когда и где)");
        workInfoTextField.setFieldLabel(
                "Выполняемая работа (учеба) до поступления в ВУЗ (где и в качестве кого), стаж работы");

        maritalStatusTextField.setFieldLabel("Семейное положение, местожительство жены (мужа)");
        childrenInfoTextField.setFieldLabel("Сведения о детях (пол, дата рождения)");

        rightLayoutContainer.add(educationTextField, wh10FormData);
        rightLayoutContainer.add(workInfoTextField, wh10FormData);
        rightLayoutContainer.add(maritalStatusTextField, wh10FormData);
        rightLayoutContainer.add(childrenInfoTextField, wh10FormData);

        return rightLayoutContainer;
    }

    private LayoutContainer createParentLayoutContainer() {
        LayoutContainer parentLayoutContainer = new LayoutContainer(new ColumnLayout());

        // Father
        FieldSet fatherFieldSet = new FieldSet();
        fatherFieldSet.setLayout(new FormLayout(LabelAlign.TOP));
        fatherFieldSet.setHeading("Отец");
        fatherFieldSet.setStyleAttribute("paddingRight", "10px");

        lastNameFatherTextField.setFieldLabel("Фамилия");
        firstNameFatherTextField.setFieldLabel("Имя");
        middleNameFatherTextField.setFieldLabel("Отчество");
        birthdayDateFatherField.setFieldLabel("Дата рождения");
        addressFatherTextField.setFieldLabel("Место жительства");
        workInfoFatherTextField.setFieldLabel("Место работы");
        phonesFatherTextField.setFieldLabel("Телефон (домашний, рабочий, мобильный)");

        fatherFieldSet.add(createTripleFieldContainer(lastNameFatherTextField, firstNameFatherTextField,
                middleNameFatherTextField), wh5FormData);
        fatherFieldSet.add(createDoubleFieldContainer(birthdayDateFatherField, workInfoFatherTextField), wh5FormData);
        fatherFieldSet.add(addressFatherTextField, wh5FormData);
        fatherFieldSet.add(phonesFatherTextField, wFormData);

        // Mother
        FieldSet motherFieldSet = new FieldSet();
        motherFieldSet.setLayout(new FormLayout(LabelAlign.TOP));
        motherFieldSet.setHeading("Мать");
        motherFieldSet.setStyleAttribute("paddingLeft", "10px");

        lastNameMotherTextField.setFieldLabel("Фамилия");
        firstNameMotherTextField.setFieldLabel("Имя");
        middleNameMotherTextField.setFieldLabel("Отчество");
        birthdayDateMotherField.setFieldLabel("Дата рождения");
        addressMotherTextField.setFieldLabel("Место жительства");
        workInfoMotherTextField.setFieldLabel("Место работы");
        phonesMotherTextField.setFieldLabel("Телефон (домашний, рабочий, мобильный)");

        motherFieldSet.add(createTripleFieldContainer(lastNameMotherTextField, firstNameMotherTextField,
                middleNameMotherTextField), wh5FormData);
        motherFieldSet.add(createDoubleFieldContainer(birthdayDateMotherField, workInfoMotherTextField), wh5FormData);
        motherFieldSet.add(addressMotherTextField, wh5FormData);
        motherFieldSet.add(phonesMotherTextField, wFormData);

        LayoutContainer leftLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        leftLayoutContainer.setStyleAttribute("paddingRight", "10px");
        leftLayoutContainer.add(fatherFieldSet, wFormData);

        LayoutContainer rightLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        rightLayoutContainer.setStyleAttribute("paddingLeft", "10px");
        rightLayoutContainer.add(motherFieldSet, wFormData);

        parentLayoutContainer.add(leftLayoutContainer, new ColumnData(0.5));
        parentLayoutContainer.add(rightLayoutContainer, new ColumnData(0.5));

        return parentLayoutContainer;
    }

    private LayoutContainer createDoubleFieldContainer(Field firstField, Field secondField) {
        LayoutContainer firstLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        firstLayoutContainer.add(firstField, wrFormData);

        LayoutContainer secondLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        secondLayoutContainer.add(secondField, wFormData);

        LayoutContainer layoutContainer = new LayoutContainer(new ColumnLayout());
        layoutContainer.add(firstLayoutContainer, new ColumnData(0.333));
        layoutContainer.add(secondLayoutContainer, new ColumnData(0.666));

        return layoutContainer;
    }

    private LayoutContainer createTripleFieldContainer(Field firstField, Field secondField, Field thirdField) {
        LayoutContainer firstLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        firstLayoutContainer.add(firstField, wrFormData);

        LayoutContainer secondLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        secondLayoutContainer.add(secondField, wrFormData);

        LayoutContainer thirdLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        thirdLayoutContainer.add(thirdField, wFormData);

        LayoutContainer layoutContainer = new LayoutContainer(new ColumnLayout());
        layoutContainer.add(firstLayoutContainer, new ColumnData(0.333));
        layoutContainer.add(secondLayoutContainer, new ColumnData(0.333));
        layoutContainer.add(thirdLayoutContainer, new ColumnData(0.333));

        return layoutContainer;
    }

    public void setStudentDetails(StudentDetailsModel studentDetailsModel) {
        this.studentDetailsModel = studentDetailsModel;

        ModelData sex = null;
        if (studentDetailsModel.getSex() != null) {
            switch (studentDetailsModel.getSex()) {
                case MALE:
                    sex = maleSex;
                    break;
                case FEMALE:
                    sex = femaleSex;
                    break;
            }
        }
        sexComboBox.setValue(sex);

        birthdayDateField.setValue(studentDetailsModel.getBirthday());
        birthplaceTextField.setValue(studentDetailsModel.getBirthplace());
        educationTextField.setValue(studentDetailsModel.getEducation());
        workInfoTextField.setValue(studentDetailsModel.getWorkInfo());
        maritalStatusTextField.setValue(studentDetailsModel.getMaritalStatus());
        childrenInfoTextField.setValue(studentDetailsModel.getChildrenInfo());

        passportListStore.removeAll();

        if (studentDetailsModel.getPassports() != null) {
            passportListStore.add(studentDetailsModel.getPassports());
        }

        PassportModel currentPassport = PassportModelUtil.findCurrentPassport(studentDetailsModel.getPassports());

        if (currentPassport != null) {
            passportGrid.getSelectionModel().select(currentPassport, false);
        }

        citizenshipTextField.setValue(currentPassport == null ? null : currentPassport.getCitizenship());
        passportAddressTextField.setValue(currentPassport == null ? null : currentPassport.getAddress());

        ParentModel father = studentDetailsModel.getFather();
        lastNameFatherTextField.setValue(father == null ? null : father.getLastName());
        firstNameFatherTextField.setValue(father == null ? null : father.getFirstName());
        middleNameFatherTextField.setValue(father == null ? null : father.getMiddleName());
        birthdayDateFatherField.setValue(father == null ? null : father.getBirthday());
        workInfoFatherTextField.setValue(father == null ? null : father.getWorkInfo());
        phonesFatherTextField.setValue(father == null ? null : father.getPhoneNumbers());
        addressFatherTextField.setValue(father == null ? null : father.getAddress());

        ParentModel mother = studentDetailsModel.getMother();
        lastNameMotherTextField.setValue(mother == null ? null : mother.getLastName());
        firstNameMotherTextField.setValue(mother == null ? null : mother.getFirstName());
        middleNameMotherTextField.setValue(mother == null ? null : mother.getMiddleName());
        birthdayDateMotherField.setValue(mother == null ? null : mother.getBirthday());
        workInfoMotherTextField.setValue(mother == null ? null : mother.getWorkInfo());
        phonesMotherTextField.setValue(mother == null ? null : mother.getPhoneNumbers());
        addressMotherTextField.setValue(mother == null ? null : mother.getAddress());

        oldAddressTextField.setValue(studentDetailsModel.getOldAddress());
        actualAddressTextField.setValue(studentDetailsModel.getAddress());
    }

    public StudentDetailsModel getStudentDetails() {
        ModelData sexModelData = sexComboBox.getValue();
        studentDetailsModel.setSex(sexModelData == null ? null : (PersonModel.Sex) sexModelData.get("sex"));

        studentDetailsModel.setBirthday(birthdayDateField.getValue());
        studentDetailsModel.setBirthplace(birthplaceTextField.getValue());
        studentDetailsModel.setEducation(educationTextField.getValue());
        studentDetailsModel.setWorkInfo(workInfoTextField.getValue());
        studentDetailsModel.setMaritalStatus(maritalStatusTextField.getValue());
        studentDetailsModel.setChildrenInfo(childrenInfoTextField.getValue());

        studentDetailsModel.setOldAddress(oldAddressTextField.getValue());
        studentDetailsModel.setAddress(actualAddressTextField.getValue());

        studentDetailsModel.setPassports(passportListStore.getModels());

        // deselect all
        for (PassportModel passportModel : studentDetailsModel.getPassports()) {
            passportModel.setActual(false);
        }

        // select current passport
        PassportModel currentPassport = passportGrid.getSelectionModel().getSelectedItem();
        if (currentPassport != null) {   // todo: verify
            currentPassport.setActual(true);
            currentPassport.setCitizenship(citizenshipTextField.getValue());
            currentPassport.setAddress(passportAddressTextField.getValue());
        }

        ParentModel father = studentDetailsModel.getFather();
        if (father == null) {
            father = new ParentModel();
        }
        father.setLastName(lastNameFatherTextField.getValue());
        father.setFirstName(firstNameFatherTextField.getValue());
        father.setMiddleName(middleNameFatherTextField.getValue());
        father.setBirthday(birthdayDateFatherField.getValue());
        father.setWorkInfo(workInfoFatherTextField.getValue());
        father.setPhoneNumbers(phonesFatherTextField.getValue());
        father.setAddress(addressFatherTextField.getValue());

        studentDetailsModel.setFather(father);

        ParentModel mother = studentDetailsModel.getMother();
        if (mother == null) {
            mother = new ParentModel();
        }
        mother.setLastName(lastNameMotherTextField.getValue());
        mother.setFirstName(firstNameMotherTextField.getValue());
        mother.setMiddleName(middleNameMotherTextField.getValue());
        mother.setBirthday(birthdayDateMotherField.getValue());
        mother.setWorkInfo(workInfoMotherTextField.getValue());
        mother.setPhoneNumbers(phonesMotherTextField.getValue());
        mother.setAddress(addressMotherTextField.getValue());

        studentDetailsModel.setMother(mother);

        return studentDetailsModel;
    }
}
