package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.*;
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
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.GroupsGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 10:44 PM
 */
public class SpecialityPanel extends ContentPanel {
    private TextField<String> fullNameTextField = new TextField<String>();
    private TextField<String> nameTextField = new TextField<String>();
    private TextField<String> codeTextField = new TextField<String>();
    private NumberField courseCountNumberField = new NumberField();
    private ComboBox<FacultyModel> facultiesComboBox = new ComboBox<FacultyModel>();

    private GroupsGrid groupsGrid = new GroupsGrid();

    private SpecialityModel currentSpecialityModel;

    public SpecialityPanel() {
        setHeading("Информация о специальность");
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        fullNameTextField.setFieldLabel("Полное имя");
        fullNameTextField.setAllowBlank(false);

        nameTextField.setFieldLabel("Имя");
        nameTextField.setAllowBlank(false);

        codeTextField.setFieldLabel("Код");
        codeTextField.setAllowBlank(false);

        courseCountNumberField.setFieldLabel("Количество курсов");
        courseCountNumberField.setPropertyEditorType(Integer.class);
        courseCountNumberField.setAllowBlank(false);

        facultiesComboBox.setFieldLabel("Факультет");
        facultiesComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        facultiesComboBox.setDisplayField("name");

        RpcProxy<List<FacultyModel>> proxy = new RpcProxy<List<FacultyModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<FacultyModel>> listAsyncCallback) {
                FacultyService.Util.getInstance().loadFaculties(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<FacultyModel>> loader = new BaseListLoader<ListLoadResult<FacultyModel>>(proxy);
        facultiesComboBox.setStore(new ListStore<FacultyModel>(loader));
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        final RowEditor<GroupModel> rowEditor = new RowEditor<GroupModel>();
        rowEditor.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        rowEditor.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
            @Override
            public void handleEvent(RowEditorEvent rowEditorEvent) {
                mask("Сохраниние измененной группы");

                GroupModel groupModel = (GroupModel) rowEditorEvent.getRecord().getModel();

                GroupService.Util.getInstance().update(groupModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        groupsGrid.getStore().commitChanges();
                        unmask();
                        Dispatcher.forwardEvent(CommonEvents.Info, "Группа успешно изменена!");
                        Dispatcher.forwardEvent(AdminEvents.GroupChanged);
                    }
                });
            }
        });

        groupsGrid.addPlugin(rowEditor);

        Button addGroupButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addGroupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(false);
                mask("Добавление новой группы");

                GroupService.Util.getInstance().create(currentSpecialityModel.getId(), new BaseAsyncCallback<GroupModel>() {
                    @Override
                    public void onSuccess(GroupModel groupModel) {
                        groupsGrid.getStore().add(groupModel);
                        rowEditor.startEditing(groupsGrid.getStore().indexOf(groupModel), true);

                        unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, "Группа успешно добавлена!");
                        Dispatcher.forwardEvent(AdminEvents.GroupAdded);
                    }
                });
            }
        });

        Button editGroupButton = new Button("Редактировать", IconHelper.createStyle("editButton-icon"));
        editGroupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                GroupModel groupModel = groupsGrid.getSelectionModel().getSelectedItem();

                if (groupModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, группу!");
                } else {
                    rowEditor.startEditing(groupsGrid.getStore().indexOf(groupModel), true);
                }
            }
        });

        Button removeGroupButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeGroupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<GroupModel> groupList = groupsGrid.getSelectionModel().getSelectedItems();

                if (groupList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, группу!");
                } else {
                    final List<Long> groupIdList = new ArrayList<Long>(groupList.size());

                    for (GroupModel groupModel : groupList) {
                        groupIdList.add(groupModel.getId());
                    }

                    MessageBox.confirm("Удаление групп",
                            "Вы действително хотите удалить выбранные группы?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        rowEditor.stopEditing(false);
                                        mask("Удаление выбранных групп");

                                        GroupService.Util.getInstance().delete(groupIdList,
                                                new BaseAsyncCallback<Void>() {
                                                    @Override
                                                    public void onSuccess(Void result) {
                                                        for (GroupModel groupModel : groupList) {
                                                            groupsGrid.getStore().remove(groupModel);
                                                        }

                                                        unmask();
                                                        Dispatcher.forwardEvent(CommonEvents.Info, "Группа успешно удалена!");
                                                        Dispatcher.forwardEvent(AdminEvents.GroupsDeleted);
                                                    }
                                                });
                                    }
                                }
                            });
                }
            }
        });

        ToolBar groupGridToolBar = new ToolBar();
        groupGridToolBar.add(addGroupButton);
        groupGridToolBar.add(new SeparatorToolItem());
        groupGridToolBar.add(editGroupButton);
        groupGridToolBar.add(new SeparatorToolItem());
        groupGridToolBar.add(removeGroupButton);

        LayoutContainer fullNameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        fullNameLayoutContainer.add(fullNameTextField, FormUtil.wFormData);

        LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        LayoutContainer codeLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        codeLayoutContainer.add(codeTextField, FormUtil.wFormData);

        LayoutContainer courseCountLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        courseCountLayoutContainer.add(courseCountNumberField, FormUtil.wFormData);

        LayoutContainer facultiesLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        facultiesLayoutContainer.add(facultiesComboBox, FormUtil.wFormData);

        Button saveButton = new Button("Сохранить", IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                currentSpecialityModel.setFullName(fullNameTextField.getValue());
                currentSpecialityModel.setName(nameTextField.getValue());
                currentSpecialityModel.setCode(codeTextField.getValue());
                currentSpecialityModel.setCourseCount(courseCountNumberField.getValue().intValue());
                currentSpecialityModel.setFaculty(facultiesComboBox.getValue());

                SpecialityService.Util.getInstance().updateSpeciality(currentSpecialityModel,
                        new BaseAsyncCallback<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                Dispatcher.forwardEvent(CommonEvents.Info, "Специальность успешно изменена!");
                                Dispatcher.forwardEvent(AdminEvents.SpecialityChanged);
                            }
                        });
            }
        });

        Button cancelButton = new Button("Отменить", IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                fullNameTextField.setValue(currentSpecialityModel.getFullName());
                nameTextField.setValue(currentSpecialityModel.getName());
                codeTextField.setValue(currentSpecialityModel.getCode());
                courseCountNumberField.setValue(currentSpecialityModel.getCourseCount());
                facultiesComboBox.setValue(currentSpecialityModel.getFaculty());
            }
        });

        ContentPanel specialityLayoutContainer = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        specialityLayoutContainer.setFrame(true);
        specialityLayoutContainer.setHeaderVisible(false);

        specialityLayoutContainer.add(fullNameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(nameLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(codeLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(courseCountLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));
        specialityLayoutContainer.add(facultiesLayoutContainer, new RowData(150, 1, new Margins(0, 5, 0, 5)));

        specialityLayoutContainer.addButton(saveButton);
        specialityLayoutContainer.addButton(cancelButton);

        ContentPanel groupGridPanel = new ContentPanel(new FitLayout());
        groupGridPanel.setHeading("Список групп");
        groupGridPanel.setTopComponent(groupGridToolBar);
        groupGridPanel.add(groupsGrid);

        add(specialityLayoutContainer, new RowData(1, 100));
        add(groupGridPanel, new RowData(1, 1));
    }

    public void bind(SpecialityModel specialityModel) {
        this.currentSpecialityModel = specialityModel;

        fullNameTextField.setValue(specialityModel.getFullName());
        nameTextField.setValue(specialityModel.getName());
        codeTextField.setValue(specialityModel.getCode());
        courseCountNumberField.setValue(specialityModel.getCourseCount());
        facultiesComboBox.setValue(specialityModel.getFaculty());

        groupsGrid.reload(specialityModel.getId());
    }
}
