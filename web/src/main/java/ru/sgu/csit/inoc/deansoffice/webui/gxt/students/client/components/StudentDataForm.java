package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.PersonModelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 1:32 PM
 */
public class StudentDataForm extends FormPanel {
    private StudentDetailsModel studentDetailsModel;

    private ModelData maleSex = new BaseModel();
    private ModelData femaleSex = new BaseModel();
    private ComboBox<ModelData> sexComboBox = new ComboBox<ModelData>();

    private DateField birthdayDateField = new DateField();
    private TextField<String> birthplaceTextField = new TextField<String>();
    private TextField<String> citizenshipTextField = new TextField<String>();
    private TextField<String> educationTextField = new TextField<String>();
    private TextField<String> workInfoTextField = new TextField<String>();

    private TextField<String> passportSeriesTextField = new TextField<String>();
    private TextField<String> passportNumberTextField = new TextField<String>();
    private TextField<String> issuingOrganizationTextField = new TextField<String>();
    private DateField issuedDateTextField = new DateField();

    private TextField<String> maritalStatusTextField = new TextField<String>();
    private TextField<String> childrenInfoTextField = new TextField<String>();

    private TextField<String> lastNameFatherTextField = new TextField<String>();
    private TextField<String> firstNameFatherTextField = new TextField<String>();
    private TextField<String> middleNameFatherTextField = new TextField<String>();
    private DateField birthdayDateFatherField = new DateField();
    private TextField<String> addressFatherTextField = new TextField<String>();
    private TextField<String> workInfoFatherTextField = new TextField<String>();
    private TextField<String> phonesFatherTextField = new TextField<String>();

    private TextField<String> lastNameMotherTextField = new TextField<String>();
    private TextField<String> firstNameMotherTextField = new TextField<String>();
    private TextField<String> middleNameMotherTextField = new TextField<String>();
    private DateField birthdayDateMotherField = new DateField();
    private TextField<String> addressMotherTextField = new TextField<String>();
    private TextField<String> workInfoMotherTextField = new TextField<String>();
    private TextField<String> phonesMotherTextField = new TextField<String>();

    private TextField<String> oldAddressTextField = new TextField<String>();
    private TextField<String> passportAddressTextField = new TextField<String>();
    private TextField<String> actualAddressTextField = new TextField<String>();

    private FormData wFormData = new FormData("100%");
    private FormData wh10FormData = new FormData("100%");
    private FormData wh5FormData = new FormData("100%");
    private FormData wrFormData = new FormData("100%");

    public StudentDataForm() {
        setHeaderVisible(false);
        setFrame(true);
        setBorders(false);
        setSize("100%", "100%");
        setLabelAlign(LabelAlign.TOP);

        ListStore<ModelData> store = new ListStore<ModelData>();
        store.add(maleSex);
        store.add(femaleSex);
        sexComboBox.setStore(store);

        wh10FormData.setMargins(new Margins(0, 0, 10, 0));
        wh5FormData.setMargins(new Margins(0, 0, 5, 0));
        wrFormData.setMargins(new Margins(0, 20, 0, 0));

        setButtonAlign(Style.HorizontalAlignment.CENTER);
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        setLayout(new FormLayout(LabelAlign.TOP));

        LayoutContainer columnLayoutContainer = new LayoutContainer(new ColumnLayout());
        columnLayoutContainer.add(createLeftLayoutContainer(), new ColumnData(0.5));
        columnLayoutContainer.add(createRightLayoutContainer(), new ColumnData(0.5));

        oldAddressTextField.setFieldLabel("До поступления в ВУЗ");
        passportAddressTextField.setFieldLabel("В соответствии с данными паспорта");
        actualAddressTextField.setFieldLabel("Фактический адрес");

        FieldSet addressFieldSet = new FieldSet();
        addressFieldSet.setLayout(new FormLayout());
        addressFieldSet.setHeading("Домашний адрес");

        addressFieldSet.add(createTripleFieldContainer(oldAddressTextField, passportAddressTextField,
                actualAddressTextField), wFormData);

        add(columnLayoutContainer, wFormData);
        add(addressFieldSet, wFormData);
    }

    private LayoutContainer createLeftLayoutContainer() {
        maleSex.set("sex", PersonModel.Sex.MALE);
        maleSex.set("title", PersonModelUtil.sexToString(PersonModel.Sex.MALE));

        femaleSex.set("sex", PersonModel.Sex.FEMALE);
        femaleSex.set("title", PersonModelUtil.sexToString(PersonModel.Sex.FEMALE));

        sexComboBox.setDisplayField("title");
        sexComboBox.setFieldLabel("Пол");

        birthdayDateField.setFieldLabel("Дата рождения");
        birthplaceTextField.setFieldLabel("Место рождения");
        citizenshipTextField.setFieldLabel("Гражданство");
        educationTextField.setFieldLabel("Образование (какое учебное заведение окончил, когда и где)");
        workInfoTextField.setFieldLabel(
                "Выполняемая работа (учеба) до поступления в ВУЗ (где и в качестве кого), стаж работы");

        passportSeriesTextField.setFieldLabel("Серия");
        passportNumberTextField.setFieldLabel("Номер");
        issuingOrganizationTextField.setFieldLabel("Кем выдан");
        issuedDateTextField.setFieldLabel("Когда выдан");

        FieldSet passportFieldSet = new FieldSet();
        passportFieldSet.setLayout(new FormLayout());
        passportFieldSet.setHeading("Паспортные данные");

        passportFieldSet.add(createDoubleFieldContainer(passportSeriesTextField, passportNumberTextField), wh5FormData);
        passportFieldSet.add(createDoubleFieldContainer(issuedDateTextField, issuingOrganizationTextField), wFormData);

        maritalStatusTextField.setFieldLabel("Семейное положение, местожительство жены (мужа)");
        childrenInfoTextField.setFieldLabel("Сведения о детях (пол, дата рождения)");

        LayoutContainer leftLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        leftLayoutContainer.setStyleAttribute("paddingRight", "10px");

        leftLayoutContainer.add(createDoubleFieldContainer(sexComboBox, citizenshipTextField), wh10FormData);
        leftLayoutContainer.add(createDoubleFieldContainer(birthdayDateField, birthplaceTextField), wh10FormData);

        leftLayoutContainer.add(passportFieldSet, wh10FormData);

        leftLayoutContainer.add(educationTextField, wh10FormData);
        leftLayoutContainer.add(workInfoTextField, wh10FormData);
        leftLayoutContainer.add(maritalStatusTextField, wh10FormData);
        leftLayoutContainer.add(childrenInfoTextField, wh10FormData);

        return leftLayoutContainer;
    }

    private LayoutContainer createRightLayoutContainer() {
        // Father
        lastNameFatherTextField.setFieldLabel("Фимилия");
        firstNameFatherTextField.setFieldLabel("Имя");
        middleNameFatherTextField.setFieldLabel("Отчество");
        birthdayDateFatherField.setFieldLabel("Дата рождения");
        addressFatherTextField.setFieldLabel("Место жительства");
        workInfoFatherTextField.setFieldLabel("Место работы");
        phonesFatherTextField.setFieldLabel("Телефон (домашний, рабочий, мобильный)");

        FieldSet fatherFieldSet = new FieldSet();
        fatherFieldSet.setLayout(new FormLayout(LabelAlign.TOP));
        fatherFieldSet.setHeading("Отец");

        fatherFieldSet.add(createTripleFieldContainer(lastNameFatherTextField, firstNameFatherTextField,
                middleNameFatherTextField), wh5FormData);
        fatherFieldSet.add(createDoubleFieldContainer(birthdayDateFatherField, workInfoFatherTextField), wh5FormData);
        fatherFieldSet.add(addressFatherTextField, wh5FormData);
        fatherFieldSet.add(phonesFatherTextField, wFormData);

        // Mother
        lastNameMotherTextField.setFieldLabel("Фимилия");
        firstNameMotherTextField.setFieldLabel("Имя");
        middleNameMotherTextField.setFieldLabel("Отчество");
        birthdayDateMotherField.setFieldLabel("Дата рождения");
        addressMotherTextField.setFieldLabel("Место жительства");
        workInfoMotherTextField.setFieldLabel("Место работы");
        phonesMotherTextField.setFieldLabel("Телефон (домашний, рабочий, мобильный)");

        FieldSet motherFieldSet = new FieldSet();
        motherFieldSet.setLayout(new FormLayout(LabelAlign.TOP));
        motherFieldSet.setHeading("Мать");

        motherFieldSet.add(createTripleFieldContainer(lastNameMotherTextField, firstNameMotherTextField,
                middleNameMotherTextField), wh5FormData);
        motherFieldSet.add(createDoubleFieldContainer(birthdayDateMotherField, workInfoMotherTextField), wh5FormData);
        motherFieldSet.add(addressMotherTextField, wh5FormData);
        motherFieldSet.add(phonesMotherTextField, wFormData);

        // adding to container
        LayoutContainer rightLayoutContainer = new LayoutContainer(new FormLayout(LabelAlign.TOP));
        rightLayoutContainer.setStyleAttribute("paddingLeft", "10px");

        rightLayoutContainer.add(fatherFieldSet, wFormData);
        rightLayoutContainer.add(motherFieldSet, wFormData);

        return rightLayoutContainer;
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

        if (studentDetailsModel.getSex() != null) {
            switch (studentDetailsModel.getSex()) {
                case MALE:
                    sexComboBox.select(maleSex);
                    break;
                case FEMALE:
                    sexComboBox.select(femaleSex);
                    break;
            }
        }

        if (studentDetailsModel.getBirthday() != null) {
            birthdayDateField.setValue(studentDetailsModel.getBirthday());
        }
        if (studentDetailsModel.getBirthplace() != null) {
            birthplaceTextField.setValue(studentDetailsModel.getBirthplace());
        }
        if (studentDetailsModel.getEducation() != null) {
            educationTextField.setValue(studentDetailsModel.getEducation());
        }
        if (studentDetailsModel.getWorkInfo() != null) {
            workInfoTextField.setValue(studentDetailsModel.getWorkInfo());
        }
        if (studentDetailsModel.getMaritalStatus() != null) {
            maritalStatusTextField.setValue(studentDetailsModel.getMaritalStatus());
        }
        if (studentDetailsModel.getChildrenInfo() != null) {
            childrenInfoTextField.setValue(studentDetailsModel.getChildrenInfo());
        }

        if (studentDetailsModel.getPassports() != null && studentDetailsModel.getPassports().size() > 0) {
            PassportModel currentPassportModel =
                    studentDetailsModel.getPassports().get(studentDetailsModel.getPassports().size() - 1);

            citizenshipTextField.setValue(currentPassportModel.getCitizenship());

            if (currentPassportModel.getNumber() != null) {
                passportNumberTextField.setValue(currentPassportModel.getNumber());
            }
            if (currentPassportModel.getSeries() != null) {
                passportSeriesTextField.setValue(currentPassportModel.getSeries());
            }
            if (currentPassportModel.getIssuingOrganization() != null) {
                issuingOrganizationTextField.setValue(currentPassportModel.getIssuingOrganization());
            }
            if (currentPassportModel.getIssuedDate() != null) {
                issuedDateTextField.setValue(currentPassportModel.getIssuedDate());
            }
            if (currentPassportModel.getAddress() != null) {
                passportAddressTextField.setValue(currentPassportModel.getAddress());
            }
        }

        if (studentDetailsModel.getFather() != null) {
            ParentModel father = studentDetailsModel.getFather();

            if (father.getLastName() != null) {
                lastNameFatherTextField.setValue(father.getLastName());
            }
            if (father.getFirstName() != null) {
                firstNameFatherTextField.setValue(father.getFirstName());
            }
            if (father.getFirstName() != null) {
                middleNameFatherTextField.setValue(father.getMiddleName());
            }
            if (father.getBirthday() != null) {
                birthdayDateFatherField.setValue(father.getBirthday());
            }
            if (father.getWorkInfo() != null) {
                workInfoFatherTextField.setValue(father.getWorkInfo());
            }
            if (father.getPhoneNumbers() != null) {
                phonesFatherTextField.setValue(father.getPhoneNumbers());
            }
            if (father.getAddress() != null) {
                addressFatherTextField.setValue(father.getAddress());
            }
        }

        if (studentDetailsModel.getMother() != null) {
            ParentModel mother = studentDetailsModel.getMother();

            if (mother.getLastName() != null) {
                lastNameMotherTextField.setValue(mother.getLastName());
            }
            if (mother.getFirstName() != null) {
                firstNameMotherTextField.setValue(mother.getFirstName());
            }
            if (mother.getFirstName() != null) {
                middleNameMotherTextField.setValue(mother.getMiddleName());
            }
            if (mother.getBirthday() != null) {
                birthdayDateMotherField.setValue(mother.getBirthday());
            }
            if (mother.getWorkInfo() != null) {
                workInfoMotherTextField.setValue(mother.getWorkInfo());
            }
            if (mother.getPhoneNumbers() != null) {
                phonesMotherTextField.setValue(mother.getPhoneNumbers());
            }
            if (mother.getAddress() != null) {
                addressMotherTextField.setValue(mother.getAddress());
            }
        }

        if (studentDetailsModel.getOldAddress() != null) {
            oldAddressTextField.setValue(studentDetailsModel.getOldAddress());
        }
        if (studentDetailsModel.getActualAddress() != null) {
            actualAddressTextField.setValue(studentDetailsModel.getActualAddress());
        }
    }

    public StudentDetailsModel getStudentDetails() {
        // todo validate
        ModelData sexModelData = sexComboBox.getValue();
        if (sexModelData != null) {
            studentDetailsModel.setSex((PersonModel.Sex) sexModelData.get("sex")); // todo: fix
        }

        studentDetailsModel.setBirthday(birthdayDateField.getValue());
        studentDetailsModel.setBirthplace(birthplaceTextField.getValue());
        studentDetailsModel.setEducation(educationTextField.getValue());
        studentDetailsModel.setWorkInfo(workInfoTextField.getValue());
        studentDetailsModel.setMaritalStatus(maritalStatusTextField.getValue());
        studentDetailsModel.setChildrenInfo(childrenInfoTextField.getValue());

        studentDetailsModel.setOldAddress(oldAddressTextField.getValue());
        studentDetailsModel.setActualAddress(actualAddressTextField.getValue());

        PassportModel currentPassportModel;
        if (studentDetailsModel.getPassports() == null || studentDetailsModel.getPassports().size() <= 0) {
            currentPassportModel = new PassportModel();
        } else {
            currentPassportModel =
                    studentDetailsModel.getPassports().get(studentDetailsModel.getPassports().size() - 1);
        }
        currentPassportModel.setCitizenship(citizenshipTextField.getValue());
        currentPassportModel.setNumber(passportNumberTextField.getValue());
        currentPassportModel.setSeries(passportSeriesTextField.getValue());
        currentPassportModel.setIssuingOrganization(issuingOrganizationTextField.getValue());
        currentPassportModel.setIssuedDate(issuedDateTextField.getValue());
        currentPassportModel.setAddress(passportAddressTextField.getValue());

        List<PassportModel> passports = studentDetailsModel.getPassports();
        if (passports == null) {
            passports = new ArrayList<PassportModel>();
        }
        if (!passports.contains(currentPassportModel)) {
            passports.add(currentPassportModel);
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
