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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs.GroupDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.GroupsGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
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
public class SpecialityPanel extends ContentPanel {
    private final TextField<String> fullNameTextField = new TextField<String>();
    private final TextField<String> nameTextField = new TextField<String>();
    private final TextField<String> codeTextField = new TextField<String>();
    private final NumberField courseCountNumberField = new NumberField();
    private final ComboBox<FacultyModel> facultiesComboBox = new ComboBox<FacultyModel>();

    private final GroupsGrid groupsGrid = new GroupsGrid();

    private SpecialityModel currentSpecialityModel;

    public SpecialityPanel() {
        setHeading(MESSAGES.specialityPanelHeading());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        fullNameTextField.setFieldLabel(MESSAGES.fullName());
        fullNameTextField.setAllowBlank(false);

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAllowBlank(false);

        codeTextField.setFieldLabel(MESSAGES.code());
        codeTextField.setAllowBlank(false);

        courseCountNumberField.setFieldLabel(MESSAGES.courseCount());
        courseCountNumberField.setPropertyEditorType(Integer.class);
        courseCountNumberField.setAllowBlank(false);

        facultiesComboBox.setFieldLabel(MESSAGES.faculty());
        facultiesComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        facultiesComboBox.setDisplayField("name");

        final RpcProxy<List<FacultyModel>> proxy = new RpcProxy<List<FacultyModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<FacultyModel>> listAsyncCallback) {
                FacultyService.Util.getInstance().loadFaculties(listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<FacultyModel>> loader = new BaseListLoader<ListLoadResult<FacultyModel>>(proxy);
        facultiesComboBox.setStore(new ListStore<FacultyModel>(loader));
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        final Button addGroupButton = new Button(MESSAGES.add(), IconHelper.createStyle("addButton-icon"));
        addGroupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final GroupDialog groupDialog = new GroupDialog(currentSpecialityModel);
                groupDialog.show();
            }
        });

        final Button editGroupButton = new Button(MESSAGES.edit(), IconHelper.createStyle("editButton-icon"));
        editGroupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                final GroupModel groupModel = groupsGrid.getSelectionModel().getSelectedItem();

                if (groupModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectGroupPlease());
                } else {
                    final GroupDialog groupDialog = new GroupDialog(groupModel);
                    groupDialog.show();
                }
            }
        });

        final Button removeGroupButton = new Button(MESSAGES.delete(), IconHelper.createStyle("removeButton-icon"));
        removeGroupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<GroupModel> groupList = groupsGrid.getSelectionModel().getSelectedItems();

                if (groupList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectGroupsPlease());
                } else {
                    final StringBuilder message = new StringBuilder(MESSAGES.deleteGroupsConfirm());
                    message.append("<br>").append("<br>");

                    for (int i = 0; i < groupList.size(); ++i) {
                        message.append(i + 1).append(". ").append(groupList.get(i).getName()).append("<br>");
                    }

                    MessageBox.confirm(
                            MESSAGES.deletingGroups(),
                            message.toString(),
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        delete(groupList);
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar groupGridToolBar = new ToolBar();
        groupGridToolBar.add(addGroupButton);
        groupGridToolBar.add(new SeparatorToolItem());
        groupGridToolBar.add(editGroupButton);
        groupGridToolBar.add(new SeparatorToolItem());
        groupGridToolBar.add(removeGroupButton);

        final LayoutContainer fullNameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        fullNameLayoutContainer.add(fullNameTextField, FormUtil.wFormData);

        final LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        final LayoutContainer codeLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        codeLayoutContainer.add(codeTextField, FormUtil.wFormData);

        final LayoutContainer courseCountLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        courseCountLayoutContainer.add(courseCountNumberField, FormUtil.wFormData);

        final LayoutContainer facultiesLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        facultiesLayoutContainer.add(facultiesComboBox, FormUtil.wFormData);

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                currentSpecialityModel.setFullName(fullNameTextField.getValue());
                currentSpecialityModel.setName(nameTextField.getValue());
                currentSpecialityModel.setCode(codeTextField.getValue());
                currentSpecialityModel.setCourseCount(courseCountNumberField.getValue().intValue());
                currentSpecialityModel.setFaculty(facultiesComboBox.getValue());

                SpecialityService.Util.getInstance().saveOrUpdate(
                        currentSpecialityModel,
                        new BaseAsyncCallback<SpecialityModel>() {
                            @Override
                            public void onSuccess(final SpecialityModel specialityModel) {
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editSpecialitySuccessMessage());
                                Dispatcher.forwardEvent(AdminEvents.SpecialityChanged, specialityModel);
                            }
                        });
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                bind(currentSpecialityModel);
            }
        });

        final ContentPanel specialityLayoutContainer = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        specialityLayoutContainer.setFrame(true);
        specialityLayoutContainer.setHeaderVisible(false);

        specialityLayoutContainer.add(fullNameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(nameLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(codeLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(courseCountLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(facultiesLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));

        specialityLayoutContainer.addButton(saveButton);
        specialityLayoutContainer.addButton(cancelButton);

        final ContentPanel groupGridPanel = new ContentPanel(new FitLayout());
        groupGridPanel.setHeading(MESSAGES.groupListHeading());
        groupGridPanel.setTopComponent(groupGridToolBar);
        groupGridPanel.add(groupsGrid);

        add(specialityLayoutContainer, new RowData(1, 100));
        add(groupGridPanel, new RowData(1, 1));
    }

    public void reload(final SpecialityModel specialityModel) {
        this.currentSpecialityModel = specialityModel;

        reload();
    }

    public void reload() {
        Validate.notNull(currentSpecialityModel, "Current speciality is null in speciality panel");

        bind(currentSpecialityModel);

        groupsGrid.reload(currentSpecialityModel.getId());
    }

    private void delete(final List<GroupModel> groupList) {
        final List<Long> groupIdList = new ArrayList<Long>(groupList.size());

        for (final GroupModel groupModel : groupList) {
            groupIdList.add(groupModel.getId());
        }

        mask(MESSAGES.deletingSelectedGroups());

        GroupService.Util.getInstance().delete(groupIdList,
                new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        for (GroupModel groupModel : groupList) {
                            groupsGrid.getStore().remove(groupModel);
                        }

                        unmask();


                        Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.deleteGroupsSuccess());
                        Dispatcher.forwardEvent(AdminEvents.GroupsDeleted);
                    }
                });
    }

    private void bind(final SpecialityModel specialityModel) {
        fullNameTextField.setValue(specialityModel.getFullName());
        nameTextField.setValue(specialityModel.getName());
        codeTextField.setValue(specialityModel.getCode());
        courseCountNumberField.setValue(specialityModel.getCourseCount());
        facultiesComboBox.setValue(specialityModel.getFaculty());
    }
}
