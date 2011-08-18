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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.StaffGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.AdministrationService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StaffService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.AdministrationModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class InstitutePanel extends ContentPanel {
    private final TextField<String> nameTextField = new TextField<String>();
    private final ComboBox<EmployeeModel> rectorComboBox = new ComboBox<EmployeeModel>();

    private final StaffGrid staffGrid = new StaffGrid();

    private AdministrationModel currentAdministrationModel;

    public InstitutePanel() {
        setHeading("Учебное заведение");
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        nameTextField.setFieldLabel("Имя");
        nameTextField.setAllowBlank(false);

        rectorComboBox.setFieldLabel("Ректор");
        rectorComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        rectorComboBox.setDisplayField("fullName");

        RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                StaffService.Util.getInstance().loadRectorList(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);
        rectorComboBox.setStore(new ListStore<EmployeeModel>(loader));
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        AdministrationService.Util.getInstance().load(new BaseAsyncCallback<AdministrationModel>() {
            @Override
            public void onSuccess(AdministrationModel result) {
                currentAdministrationModel = result;
                bind(currentAdministrationModel);
            }
        });

        final RowEditor<EmployeeModel> rowEditor = new RowEditor<EmployeeModel>();
        rowEditor.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        rowEditor.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
            @Override
            public void handleEvent(RowEditorEvent rowEditorEvent) {
                mask("Сохраниние измененного сотрудника");

                final EmployeeModel employeeModel = (EmployeeModel) rowEditorEvent.getRecord().getModel();

                StaffService.Util.getInstance().update(employeeModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        staffGrid.getStore().commitChanges();
                        unmask();
                        Dispatcher.forwardEvent(CommonEvents.Info, "Сотрудник успешно изменен!");
                        Dispatcher.forwardEvent(AdminEvents.StaffChanged);
                    }
                });
            }
        });

        staffGrid.addPlugin(rowEditor);

        final Button addStaffButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addStaffButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(false);
                mask("Добавление нового сотрудника");

                StaffService.Util.getInstance().create(new BaseAsyncCallback<EmployeeModel>() {
                    @Override
                    public void onSuccess(EmployeeModel employeeModel) {
                        staffGrid.getStore().add(employeeModel);
                        rowEditor.startEditing(staffGrid.getStore().indexOf(employeeModel), true);
                        unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, "Сотрудник успешно добавлен!");
                        Dispatcher.forwardEvent(AdminEvents.StaffAdded);
                    }
                });
            }
        });

        final Button editStaffButton = new Button("Редактировать", IconHelper.createStyle("editButton-icon"));
        editStaffButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                final EmployeeModel employeeModel = staffGrid.getSelectionModel().getSelectedItem();

                if (employeeModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, сотрудника!");
                } else {
                    rowEditor.startEditing(staffGrid.getStore().indexOf(employeeModel), true);
                }
            }
        });

        final Button removeStaffButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeStaffButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<EmployeeModel> staffList = staffGrid.getSelectionModel().getSelectedItems();

                if (staffList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, сотрудников!");
                } else {
                    final List<Long> employeeIdList = new ArrayList<Long>(staffList.size());

                    for (EmployeeModel employeeModel : staffList) {
                        employeeIdList.add(employeeModel.getId());
                    }

                    MessageBox.confirm("Удаление сотрудников",
                            "Вы действително хотите удалить выбранных сотрудников?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        rowEditor.stopEditing(false);
                                        mask("Удаление выбранных сотрудников");

                                        StaffService.Util.getInstance().delete(employeeIdList,
                                                new BaseAsyncCallback<Void>() {
                                                    @Override
                                                    public void onSuccess(Void result) {
                                                        for (EmployeeModel employeeModel : staffList) {
                                                            staffGrid.getStore().remove(employeeModel);
                                                        }

                                                        unmask();
                                                        Dispatcher.forwardEvent(CommonEvents.Info, "Сотрудники успешно удалены!");
                                                        Dispatcher.forwardEvent(AdminEvents.StaffDeleted);
                                                    }
                                                });
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar staffGridToolBar = new ToolBar();
        staffGridToolBar.add(addStaffButton);
        staffGridToolBar.add(new SeparatorToolItem());
        staffGridToolBar.add(editStaffButton);
        staffGridToolBar.add(new SeparatorToolItem());
        staffGridToolBar.add(removeStaffButton);

        LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        LayoutContainer rectorsLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        rectorsLayoutContainer.add(rectorComboBox, FormUtil.wFormData);

        Button saveButton = new Button("Сохранить", IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                currentAdministrationModel.setName(nameTextField.getValue());
                currentAdministrationModel.setRector(rectorComboBox.getValue());

                AdministrationService.Util.getInstance().update(currentAdministrationModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        Dispatcher.forwardEvent(CommonEvents.Info, "Информация успешно изменена!");
                        Dispatcher.forwardEvent(AdminEvents.FacultyChanged);
                    }
                });
            }
        });

        Button cancelButton = new Button("Отменить", IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                bind(currentAdministrationModel);
            }
        });

        ContentPanel administrationDataPanel = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        administrationDataPanel.setFrame(true);
        administrationDataPanel.setHeaderVisible(false);

        administrationDataPanel.add(nameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        administrationDataPanel.add(rectorsLayoutContainer, new RowData(300, 1, new Margins(0, 5, 0, 5)));

        administrationDataPanel.addButton(saveButton);
        administrationDataPanel.addButton(cancelButton);

        ContentPanel staffGridPanel = new ContentPanel(new FitLayout());
        staffGridPanel.setHeading("Список сотрудников");
        staffGridPanel.setTopComponent(staffGridToolBar);
        staffGridPanel.add(staffGrid);
        setTopComponent(staffGridToolBar);

        add(administrationDataPanel, new RowData(1, 100));
        add(staffGridPanel, new RowData(1, 1));

        staffGrid.reload();
    }

    private void bind(final AdministrationModel administrationModel) {
        nameTextField.setValue(administrationModel.getName());
        rectorComboBox.setValue(administrationModel.getRector());
    }
}
