package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.KeyNav;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StudentsService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class StudentDialog extends Window {
    private FormPanel formPanel = new FormPanel();

    private TextField<String> firstNameTextField = new TextField<String>();
    private TextField<String> middleNameTextField = new TextField<String>();
    private TextField<String> lastNameTextField = new TextField<String>();

    private TextField<String> firstNameGenitiveTextField = new TextField<String>();
    private TextField<String> middleNameGenitiveTextField = new TextField<String>();
    private TextField<String> lastNameGenitiveTextField = new TextField<String>();

    private TextField<String> firstNameDativeTextField = new TextField<String>();
    private TextField<String> middleNameDativeTextField = new TextField<String>();
    private TextField<String> lastNameDativeTextField = new TextField<String>();

    private TextField<String> studentIdNumberTextField = new TextField<String>();

    private ComboBox<ModelData> studyFormComboBox = new ComboBox<ModelData>();
    private ModelData budget = new BaseModel();
    private ModelData commercial = new BaseModel();

    private ComboBox<ModelData> divisionComboBox = new ComboBox<ModelData>();
    private ModelData intramural = new BaseModel();
    private ModelData extramural = new BaseModel();
    private ModelData eveningStudy = new BaseModel();

    private ComboBox<GroupModel> groupComboBox = new ComboBox<GroupModel>();

    private StudentModel currentStudentModel;
    private Long specialityId;

    public StudentDialog(final GroupModel groupModel) {
        if (groupModel != null && groupModel.getSpeciality() != null) {
            specialityId = groupModel.getSpeciality().getId();
        }

        setHeading(MESSAGES.addStudentDialogHeading());
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setModal(true);
        setSize(600, 450);

        formPanel.setHeaderVisible(false);
        formPanel.setLabelWidth(140);

        lastNameTextField.setFieldLabel(MESSAGES.lastName());
        lastNameTextField.setAllowBlank(false);
        firstNameTextField.setFieldLabel(MESSAGES.firstName());
        firstNameTextField.setAllowBlank(false);
        middleNameTextField.setFieldLabel(MESSAGES.middleName());
        middleNameTextField.setAllowBlank(false);

        lastNameGenitiveTextField.setFieldLabel(MESSAGES.lastName());
        lastNameGenitiveTextField.setAllowBlank(false);
        firstNameGenitiveTextField.setFieldLabel(MESSAGES.firstName());
        firstNameGenitiveTextField.setAllowBlank(false);
        middleNameGenitiveTextField.setFieldLabel(MESSAGES.middleName());
        middleNameGenitiveTextField.setAllowBlank(false);

        lastNameDativeTextField.setFieldLabel(MESSAGES.lastName());
        lastNameDativeTextField.setAllowBlank(false);
        firstNameDativeTextField.setFieldLabel(MESSAGES.firstName());
        firstNameDativeTextField.setAllowBlank(false);
        middleNameDativeTextField.setFieldLabel(MESSAGES.middleName());
        middleNameDativeTextField.setAllowBlank(false);

        studentIdNumberTextField.setFieldLabel(MESSAGES.studentIdNumber());

        budget.set("title", MESSAGES.budget());
        budget.set("studyForm", StudentModel.StudyForm.BUDGET);

        commercial.set("title", MESSAGES.commercial());
        commercial.set("studyForm", StudentModel.StudyForm.COMMERCIAL);

        final ListStore<ModelData> studyFormStore = new ListStore<ModelData>();
        studyFormStore.add(budget);
        studyFormStore.add(commercial);

        studyFormComboBox.setDisplayField("title");
        studyFormComboBox.setFieldLabel(MESSAGES.studyForm());
        studyFormComboBox.setEditable(false);
        studyFormComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        studyFormComboBox.setAllowBlank(false);
        studyFormComboBox.setStore(studyFormStore);

        intramural.set("title", MESSAGES.intramural());
        intramural.set("division", StudentModel.Division.INTRAMURAL);

        extramural.set("title", MESSAGES.extramural());
        extramural.set("division", StudentModel.Division.EXTRAMURAL);

        eveningStudy.set("title", MESSAGES.eveningStudy());
        eveningStudy.set("division", StudentModel.Division.EVENINGSTUDY);

        final ListStore<ModelData> divisionStore = new ListStore<ModelData>();
        divisionStore.add(intramural);
        divisionStore.add(extramural);
        divisionStore.add(eveningStudy);

        divisionComboBox.setDisplayField("title");
        divisionComboBox.setFieldLabel(MESSAGES.division());
        divisionComboBox.setEditable(false);
        divisionComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        divisionComboBox.setAllowBlank(false);
        divisionComboBox.setStore(divisionStore);

        groupComboBox.setDisplayField("name");
        groupComboBox.setFieldLabel(MESSAGES.group());
        groupComboBox.setEditable(false);
        groupComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        groupComboBox.setAutoWidth(true);
        groupComboBox.setAllowBlank(false);
        groupComboBox.setValue(groupModel);
    }

    public StudentDialog(final StudentModel studentModel) {
        this(studentModel.getGroup());
        this.currentStudentModel = studentModel;

        setHeading(MESSAGES.editStudentDialogHeading());

        lastNameTextField.setValue(studentModel.getLastName());
        firstNameTextField.setValue(studentModel.getFirstName());
        middleNameTextField.setValue(studentModel.getMiddleName());

        lastNameGenitiveTextField.setValue(studentModel.getLastNameGenitive());
        firstNameGenitiveTextField.setValue(studentModel.getFirstNameGenitive());
        middleNameGenitiveTextField.setValue(studentModel.getMiddleNameGenitive());

        lastNameDativeTextField.setValue(studentModel.getLastNameDative());
        firstNameDativeTextField.setValue(studentModel.getFirstNameDative());
        middleNameDativeTextField.setValue(studentModel.getMiddleNameDative());

        studentIdNumberTextField.setValue(studentModel.getStudentIdNumber());

        if (studentModel.getStudyForm() != null) {
            switch (studentModel.getStudyForm()) {
                case BUDGET:
                    studyFormComboBox.setValue(budget);
                    break;
                case COMMERCIAL:
                    studyFormComboBox.setValue(commercial);
                    break;
            }
        }

        if (studentModel.getDivision() != null) {
            switch (studentModel.getDivision()) {
                case INTRAMURAL:
                    divisionComboBox.setValue(intramural);
                    break;
                case EXTRAMURAL:
                    divisionComboBox.setValue(extramural);
                    break;
                case EVENINGSTUDY:
                    divisionComboBox.setValue(eveningStudy);
                    break;
            }
        }

        groupComboBox.setValue(studentModel.getGroup());
    }

    @Override
    protected void onRender(final Element parent, final int pos) {
        super.onRender(parent, pos);

        final RpcProxy<List<GroupModel>> proxy = new RpcProxy<List<GroupModel>>() {
            @Override
            protected void load(final Object loadConfig, final AsyncCallback<List<GroupModel>> listAsyncCallback) {
                if (specialityId != null) {
                    GroupService.Util.getInstance().loadGroups(specialityId, listAsyncCallback);
                }
            }
        };

        final ListLoader<ListLoadResult<GroupModel>> loader = new BaseListLoader<ListLoadResult<GroupModel>>(proxy);
        groupComboBox.setStore(new ListStore<GroupModel>(loader));

        new KeyNav<ComponentEvent>(formPanel) {
            @Override
            public void onEnter(final ComponentEvent ce) {
                saveOrUpdate();
            }
        };

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                saveOrUpdate();
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                hide();
            }
        });

        formPanel.add(createFIOFieldSet(MESSAGES.fullNameNominative(),
                lastNameTextField, firstNameTextField, middleNameTextField), FormUtil.wFormData);
        formPanel.add(createFIOFieldSet(MESSAGES.fullNameGenitive(),
                lastNameGenitiveTextField, firstNameGenitiveTextField, middleNameGenitiveTextField), FormUtil.wFormData);
        formPanel.add(createFIOFieldSet(MESSAGES.fullNameDative(),
                lastNameDativeTextField, firstNameDativeTextField, middleNameDativeTextField), FormUtil.wFormData);
        formPanel.add(studentIdNumberTextField, FormUtil.wFormData);
        formPanel.add(studyFormComboBox, FormUtil.wFormData);
        formPanel.add(divisionComboBox, FormUtil.wFormData);
        formPanel.add(groupComboBox, FormUtil.wFormData);

        formPanel.addButton(saveButton);
        formPanel.addButton(cancelButton);

        add(formPanel, new RowData(1, 1));
    }

    @Override
    public void show() {
        super.show();
        lastNameTextField.focus();
    }

    private void saveOrUpdate() {
        if (formPanel.isValid()) {
            final boolean adding = (currentStudentModel == null);

            if (currentStudentModel == null) {
                currentStudentModel = new StudentModel();
            }

            currentStudentModel.setLastName(lastNameTextField.getValue());
            currentStudentModel.setFirstName(firstNameTextField.getValue());
            currentStudentModel.setMiddleName(middleNameTextField.getValue());

            currentStudentModel.setLastNameGenitive(lastNameGenitiveTextField.getValue());
            currentStudentModel.setFirstNameGenitive(firstNameGenitiveTextField.getValue());
            currentStudentModel.setMiddleNameGenitive(middleNameGenitiveTextField.getValue());

            currentStudentModel.setLastNameDative(lastNameDativeTextField.getValue());
            currentStudentModel.setFirstNameDative(firstNameDativeTextField.getValue());
            currentStudentModel.setMiddleNameDative(middleNameDativeTextField.getValue());

            currentStudentModel.setStudentIdNumber(studentIdNumberTextField.getValue());

            currentStudentModel.setStudyForm(studyFormComboBox.getValue().<StudentModel.StudyForm>get("studyForm"));
            currentStudentModel.setDivision(divisionComboBox.getValue().<StudentModel.Division>get("division"));

            final GroupModel groupModel = groupComboBox.getValue();

            currentStudentModel.setGroup(groupModel);
            currentStudentModel.setGroupName(groupModel.getName());
            currentStudentModel.setSpecialityName(groupModel.getSpeciality().getName());
            currentStudentModel.setCourse(groupModel.getCourse());

            StudentsService.Util.getInstance().saveOrUpdate(
                    currentStudentModel,
                    new BaseAsyncCallback<StudentModel>() {
                        @Override
                        public void onSuccess(final StudentModel studentModel) {
                            if (adding) {
                                Dispatcher.forwardEvent(AdminEvents.StudentAdded, studentModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.addStudentSuccessMessage());
                            } else {
                                Dispatcher.forwardEvent(AdminEvents.StudentChanged, studentModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editStudentSuccessMessage());
                            }
                        }
                    });

            hide();
        }
    }

    private FieldSet createFIOFieldSet(String name, Field firstField, Field secondField, Field thirdField) {
        final LayoutContainer firstLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        firstLayoutContainer.add(firstField, FormUtil.wrFormData);

        final LayoutContainer secondLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        secondLayoutContainer.add(secondField, FormUtil.wrFormData);

        final LayoutContainer thirdLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        thirdLayoutContainer.add(thirdField, FormUtil.wFormData);

        final LayoutContainer layoutContainer = new LayoutContainer(new ColumnLayout());
        layoutContainer.add(firstLayoutContainer, new ColumnData(0.333));
        layoutContainer.add(secondLayoutContainer, new ColumnData(0.333));
        layoutContainer.add(thirdLayoutContainer, new ColumnData(0.333));

        final FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading(name);
        fieldSet.setLayout(new FormLayout());

        fieldSet.add(layoutContainer, FormUtil.wFormData);

        return fieldSet;
    }
}
