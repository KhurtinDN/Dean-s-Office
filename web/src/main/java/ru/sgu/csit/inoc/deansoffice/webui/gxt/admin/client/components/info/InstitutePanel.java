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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs.EmployeeDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.EmployeeGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.AdministrationService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.AdministrationModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class InstitutePanel extends ContentPanel {
    private final TextField<String> nameTextField = new TextField<String>();
    private final ComboBox<EmployeeModel> rectorComboBox = new ComboBox<EmployeeModel>();

    private final EmployeeGrid employeeGrid = new EmployeeGrid();

    private AdministrationModel currentAdministrationModel;

    public InstitutePanel() {
        setHeading(MESSAGES.institutionHeading());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAllowBlank(false);

        rectorComboBox.setFieldLabel(MESSAGES.rector());
        rectorComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        rectorComboBox.setDisplayField("fullName");

        final RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                EmployeeService.Util.getInstance().loadRectorList(listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);
        rectorComboBox.setStore(new ListStore<EmployeeModel>(loader));
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        final Button addEmployeeButton = new Button(MESSAGES.add(), IconHelper.createStyle("addButton-icon"));
        addEmployeeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                final EmployeeDialog employeeDialog = new EmployeeDialog();
                employeeDialog.show();
            }
        });

        final Button editEmployeeButton = new Button(MESSAGES.edit(), IconHelper.createStyle("editButton-icon"));
        editEmployeeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                final EmployeeModel employeeModel = employeeGrid.getSelectionModel().getSelectedItem();

                if (employeeModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectEmployeePlease());
                } else {
                    final EmployeeDialog employeeDialog = new EmployeeDialog(employeeModel);
                    employeeDialog.show();
                }
            }
        });

        final Button removeEmployeesButton = new Button(MESSAGES.delete(), IconHelper.createStyle("removeButton-icon"));
        removeEmployeesButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                final List<EmployeeModel> employeeList = employeeGrid.getSelectionModel().getSelectedItems();

                if (employeeList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectEmployeesPlease());
                } else {
                    final StringBuilder message = new StringBuilder(MESSAGES.deleteEmployeesConfirm());
                    message.append("<br>").append("<br>");

                    for (int i = 0; i < employeeList.size(); ++i) {
                        message.append(i + 1).append(". ").append(employeeList.get(i).getFullName()).append("<br>");
                    }

                    MessageBox.confirm(
                            MESSAGES.deletingEmployees(),
                            message.toString(),
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        delete(employeeList);
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar staffGridToolBar = new ToolBar();
        staffGridToolBar.add(addEmployeeButton);
        staffGridToolBar.add(new SeparatorToolItem());
        staffGridToolBar.add(editEmployeeButton);
        staffGridToolBar.add(new SeparatorToolItem());
        staffGridToolBar.add(removeEmployeesButton);

        final LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        final LayoutContainer rectorsLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        rectorsLayoutContainer.add(rectorComboBox, FormUtil.wFormData);

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                mask(MESSAGES.saving());

                currentAdministrationModel.setName(nameTextField.getValue());
                currentAdministrationModel.setRector(rectorComboBox.getValue());

                AdministrationService.Util.getInstance().update(
                        currentAdministrationModel,
                        new BaseAsyncCallback<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editInstitutionSuccessMessage());
                                Dispatcher.forwardEvent(AdminEvents.InstitutionChanged);

                                unmask();
                            }
                        });
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                bind(currentAdministrationModel);
            }
        });

        final ContentPanel administrationPanel = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        administrationPanel.setFrame(true);
        administrationPanel.setHeaderVisible(false);

        administrationPanel.add(nameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        administrationPanel.add(rectorsLayoutContainer, new RowData(300, 1, new Margins(0, 5, 0, 5)));

        administrationPanel.addButton(saveButton);
        administrationPanel.addButton(cancelButton);

        final ContentPanel staffGridPanel = new ContentPanel(new FitLayout());
        staffGridPanel.setHeading(MESSAGES.employeeListHeading());
        staffGridPanel.setTopComponent(staffGridToolBar);
        staffGridPanel.add(employeeGrid);
        setTopComponent(staffGridToolBar);

        add(administrationPanel, new RowData(1, 100));
        add(staffGridPanel, new RowData(1, 1));
    }

    public void reload() {
        mask(MESSAGES.loading());

        AdministrationService.Util.getInstance().load(new BaseAsyncCallback<AdministrationModel>() {
            @Override
            public void onSuccess(final AdministrationModel result) {
                currentAdministrationModel = result;
                bind(currentAdministrationModel);

                unmask();

                employeeGrid.reload();
            }
        });
    }

    private void delete(final List<EmployeeModel> staffList) {
        final List<Long> employeeIdList = new ArrayList<Long>(staffList.size());

        for (final EmployeeModel employeeModel : staffList) {
            employeeIdList.add(employeeModel.getId());
        }

        mask(MESSAGES.deletingSelectedEmployees());

        EmployeeService.Util.getInstance().delete(employeeIdList, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(final Void result) {
                for (final EmployeeModel employeeModel : staffList) {
                    employeeGrid.getStore().remove(employeeModel);
                }

                unmask();

                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.deleteEmployeesSuccess());
                Dispatcher.forwardEvent(AdminEvents.EmployeesDeleted);
            }
        });
    }

    private void bind(final AdministrationModel administrationModel) {
        nameTextField.setValue(administrationModel.getName());
        rectorComboBox.setValue(administrationModel.getRector());
    }
}
