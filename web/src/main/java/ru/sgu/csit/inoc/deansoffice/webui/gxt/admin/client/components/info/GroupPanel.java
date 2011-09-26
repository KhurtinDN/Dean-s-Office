package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs.StudentDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.StudentsGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StudentsService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.Validate;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class GroupPanel extends ContentPanel {
    private final TextField<String> nameTextField = new TextField<String>();
    private final NumberField courseNumberField = new NumberField();
    private final ComboBox<SpecialityModel> specialityComboBox = new ComboBox<SpecialityModel>();

    private final StudentsGrid studentsGrid = new StudentsGrid();

    private GroupModel currentGroupModel;

    public GroupPanel() {
        setHeading(MESSAGES.groupPanelHeading());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAllowBlank(false);

        courseNumberField.setFieldLabel(MESSAGES.course());
        courseNumberField.setPropertyEditorType(Integer.class);
        courseNumberField.setAllowBlank(false);

        specialityComboBox.setFieldLabel(MESSAGES.speciality());
        specialityComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        specialityComboBox.setDisplayField("name");

        final RpcProxy<List<SpecialityModel>> proxy = new RpcProxy<List<SpecialityModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<SpecialityModel>> listAsyncCallback) {
                final Long facultyId = currentGroupModel.getSpeciality().getFaculty().getId();
                SpecialityService.Util.getInstance().loadSpecialities(facultyId, listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<SpecialityModel>> loader = new BaseListLoader<ListLoadResult<SpecialityModel>>(proxy);
        specialityComboBox.setStore(new ListStore<SpecialityModel>(loader));
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        final Button addStudentButton = new Button(MESSAGES.add(), IconHelper.createStyle("addButton-icon"));
        addStudentButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final StudentDialog studentDialog = new StudentDialog(currentGroupModel);
                studentDialog.show();
            }
        });

        final Button editStudentButton = new Button(MESSAGES.edit(), IconHelper.createStyle("editButton-icon"));
        editStudentButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                final StudentModel studentModel = studentsGrid.getSelectionModel().getSelectedItem();

                if (studentModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectStudentPlease());
                } else {
                    final StudentDialog studentDialog = new StudentDialog(studentModel);
                    studentDialog.show();
                }
            }
        });

        final Button removeStudentButton = new Button(MESSAGES.delete(), IconHelper.createStyle("removeButton-icon"));
        removeStudentButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<StudentModel> studentList = studentsGrid.getSelectionModel().getSelectedItems();

                if (studentList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectStudentsPlease());
                } else {
                    final StringBuilder message = new StringBuilder(MESSAGES.deleteStudentsConfirm());
                    message.append("<br>").append("<br>");

                    for (int i = 0; i < studentList.size(); ++i) {
                        message.append(i + 1).append(". ").append(studentList.get(i).getFullName()).append("<br>");
                    }

                    MessageBox.confirm(
                            MESSAGES.deletingStudents(),
                            message.toString(),
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        delete(studentList);
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar studentsGridToolBar = new ToolBar();
        studentsGridToolBar.add(addStudentButton);
        studentsGridToolBar.add(new SeparatorToolItem());
        studentsGridToolBar.add(editStudentButton);
        studentsGridToolBar.add(new SeparatorToolItem());
        studentsGridToolBar.add(removeStudentButton);

        final LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        final LayoutContainer courseLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        courseLayoutContainer.add(courseNumberField, FormUtil.wFormData);

        final LayoutContainer specialitiesLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        specialitiesLayoutContainer.add(specialityComboBox, FormUtil.wFormData);

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                currentGroupModel.setName(nameTextField.getValue());
                currentGroupModel.setCourse(courseNumberField.getValue().intValue());
                currentGroupModel.setSpeciality(specialityComboBox.getValue());

                GroupService.Util.getInstance().saveOrUpdate(
                        currentGroupModel,
                        new BaseAsyncCallback<GroupModel>() {
                            @Override
                            public void onSuccess(final GroupModel groupModel) {
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editSpecialitySuccessMessage());
                                Dispatcher.forwardEvent(AdminEvents.GroupChanged, groupModel);
                            }
                        });
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                bind(currentGroupModel);
            }
        });

        final ContentPanel groupLayoutContainer = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        groupLayoutContainer.setFrame(true);
        groupLayoutContainer.setHeaderVisible(false);

        groupLayoutContainer.add(nameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        groupLayoutContainer.add(courseLayoutContainer, new RowData(300, 1, new Margins(0, 5, 0, 5)));
        groupLayoutContainer.add(specialitiesLayoutContainer, new RowData(300, 1, new Margins(0, 5, 0, 5)));

        groupLayoutContainer.addButton(saveButton);
        groupLayoutContainer.addButton(cancelButton);

        final ContentPanel studentsGridPanel = new ContentPanel(new FitLayout());
        studentsGridPanel.setHeading(MESSAGES.studentListHeading());
        studentsGridPanel.setTopComponent(studentsGridToolBar);
        studentsGridPanel.add(studentsGrid);

        add(groupLayoutContainer, new RowData(1, 100));
        add(studentsGridPanel, new RowData(1, 1));
    }

    public void reload(final GroupModel groupModel) {
        this.currentGroupModel = groupModel;

        reload();
    }

    public void reload() {
        Validate.notNull(currentGroupModel, "Current group is null in group panel");
        Validate.notNull(currentGroupModel.getSpeciality(), "Speciality is null");

        bind(currentGroupModel);

        studentsGrid.reload(currentGroupModel.getId());
    }

    private void delete(final List<StudentModel> studentList) {
        final List<Long> studentIdList = new ArrayList<Long>(studentList.size());

        for (final StudentModel studentModel : studentList) {
            studentIdList.add(studentModel.getId());
        }

        mask(MESSAGES.deletingSelectedStudents());

        StudentsService.Util.getInstance().delete(studentIdList,
                new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        for (StudentModel studentModel : studentList) {
                            studentsGrid.getStore().remove(studentModel);
                        }

                        unmask();


                        Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.deleteStudentsSuccess());
                        Dispatcher.forwardEvent(AdminEvents.StudentsDeleted);
                    }
                });
    }

    private void bind(final GroupModel groupModel) {
        nameTextField.setValue(groupModel.getName());
        courseNumberField.setValue(groupModel.getCourse());
        specialityComboBox.setValue(groupModel.getSpeciality());
    }
}
