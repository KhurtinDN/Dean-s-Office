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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.SpecialitiesGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StaffService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 10:44 PM
 */
public class FacultyPanel extends ContentPanel {
    private final TextField<String> fullNameTextField = new TextField<String>();
    private final TextField<String> nameTextField = new TextField<String>();
    private final ComboBox<EmployeeModel> deansComboBox = new ComboBox<EmployeeModel>();

    private final SpecialitiesGrid specialitiesGrid = new SpecialitiesGrid();

    private FacultyModel currentFacultyModel;

    public FacultyPanel() {
        setHeading("Информация о факультете");
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        fullNameTextField.setFieldLabel("Полное имя");
        fullNameTextField.setAllowBlank(false);

        nameTextField.setFieldLabel("Имя");
        nameTextField.setAllowBlank(false);

        deansComboBox.setFieldLabel("Декан");
        deansComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        deansComboBox.setDisplayField("fullName");

        RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                StaffService.Util.getInstance().loadDeanList(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);
        deansComboBox.setStore(new ListStore<EmployeeModel>(loader));
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        final RowEditor<SpecialityModel> rowEditor = new RowEditor<SpecialityModel>();
        rowEditor.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        rowEditor.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
            @Override
            public void handleEvent(RowEditorEvent rowEditorEvent) {
                mask("Сохраниние измененной специальности");

                SpecialityModel specialityModel = (SpecialityModel) rowEditorEvent.getRecord().getModel();

                SpecialityService.Util.getInstance().updateSpeciality(specialityModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        specialitiesGrid.getStore().commitChanges();
                        unmask();
                        Dispatcher.forwardEvent(CommonEvents.Info, "Специальность успешно изменена!");
                        Dispatcher.forwardEvent(AdminEvents.SpecialityChanged);
                    }
                });
            }
        });

        specialitiesGrid.addPlugin(rowEditor);

        Button addSpecialityButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addSpecialityButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(false);
                mask("Добавление новой специальности");

                final Long lastFacultyId = currentFacultyModel.getId();

                SpecialityService.Util.getInstance().createSpeciality(currentFacultyModel.getId(), new BaseAsyncCallback<SpecialityModel>() {
                    @Override
                    public void onSuccess(SpecialityModel specialityModel) {
                        if (lastFacultyId.equals(currentFacultyModel.getId())) {
                            specialitiesGrid.getStore().add(specialityModel);
                            rowEditor.startEditing(specialitiesGrid.getStore().indexOf(specialityModel), true);
                        }

                        unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, "Специальность успешно добавлена!");
                        Dispatcher.forwardEvent(AdminEvents.SpecialityAdded);
                    }
                });
            }
        });

        Button editSpecialityButton = new Button("Редактировать", IconHelper.createStyle("editButton-icon"));
        editSpecialityButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                SpecialityModel specialityModel = specialitiesGrid.getSelectionModel().getSelectedItem();

                if (specialityModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, специальность!");
                } else {
                    rowEditor.startEditing(specialitiesGrid.getStore().indexOf(specialityModel), true);
                }
            }
        });

        Button removeSpecialityButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeSpecialityButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<SpecialityModel> specialityList = specialitiesGrid.getSelectionModel().getSelectedItems();

                if (specialityList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, специальность!");
                } else {
                    final List<Long> specialityIdList = new ArrayList<Long>(specialityList.size());

                    for (SpecialityModel specialityModel : specialityList) {
                        specialityIdList.add(specialityModel.getId());
                    }

                    MessageBox.confirm("Удаление специальностей",
                            "Вы действително хотите удалить выбранные специальности?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        rowEditor.stopEditing(false);
                                        mask("Удаление выбранных специальностей");

                                        SpecialityService.Util.getInstance().deleteSpecialities(specialityIdList,
                                                new BaseAsyncCallback<Void>() {
                                                    @Override
                                                    public void onSuccess(Void result) {
                                                        for (SpecialityModel specialityModel : specialityList) {
                                                            specialitiesGrid.getStore().remove(specialityModel);
                                                        }

                                                        unmask();
                                                        Dispatcher.forwardEvent(CommonEvents.Info, "Специальность успешно удалена!");
                                                        Dispatcher.forwardEvent(AdminEvents.SpecialitiesDeleted);
                                                    }
                                                });
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar specialityGridToolBar = new ToolBar();
        specialityGridToolBar.add(addSpecialityButton);
        specialityGridToolBar.add(new SeparatorToolItem());
        specialityGridToolBar.add(editSpecialityButton);
        specialityGridToolBar.add(new SeparatorToolItem());
        specialityGridToolBar.add(removeSpecialityButton);

        LayoutContainer fullNameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        fullNameLayoutContainer.add(fullNameTextField, FormUtil.wFormData);

        LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        LayoutContainer deansLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        deansLayoutContainer.add(deansComboBox, FormUtil.wFormData);

        Button saveButton = new Button("Сохранить", IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                currentFacultyModel.setFullName(fullNameTextField.getValue());
                currentFacultyModel.setName(nameTextField.getValue());
                currentFacultyModel.setDean(deansComboBox.getValue());

                FacultyService.Util.getInstance().updateFaculty(currentFacultyModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        Dispatcher.forwardEvent(CommonEvents.Info, "Факультет успешно изменен!");
                        Dispatcher.forwardEvent(AdminEvents.FacultyChanged);
                    }
                });
            }
        });

        Button cancelButton = new Button("Отменить", IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                fullNameTextField.setValue(currentFacultyModel.getFullName());
                nameTextField.setValue(currentFacultyModel.getName());
                deansComboBox.setValue(currentFacultyModel.getDean());
            }
        });

        ContentPanel facultyDataPanel = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        facultyDataPanel.setFrame(true);
        facultyDataPanel.setHeaderVisible(false);

        facultyDataPanel.add(fullNameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        facultyDataPanel.add(nameLayoutContainer, new RowData(200, 1, new Margins(0, 5, 0, 5)));
        facultyDataPanel.add(deansLayoutContainer, new RowData(300, 1, new Margins(0, 5, 0, 5)));

        facultyDataPanel.addButton(saveButton);
        facultyDataPanel.addButton(cancelButton);

        ContentPanel specialityGridPanel = new ContentPanel(new FitLayout());
        specialityGridPanel.setHeading("Список специальностей");
        specialityGridPanel.setTopComponent(specialityGridToolBar);
        specialityGridPanel.add(specialitiesGrid);

        add(facultyDataPanel, new RowData(1, 100));
        add(specialityGridPanel, new RowData(1, 1));
    }

    public void bind(FacultyModel facultyModel) {
        this.currentFacultyModel = facultyModel;

        fullNameTextField.setValue(facultyModel.getFullName());
        nameTextField.setValue(facultyModel.getName());
        deansComboBox.setValue(facultyModel.getDean());

        specialitiesGrid.reload(currentFacultyModel.getId());
    }
}
