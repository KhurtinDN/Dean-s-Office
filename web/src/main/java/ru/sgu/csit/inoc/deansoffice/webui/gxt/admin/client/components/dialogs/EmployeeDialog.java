package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.KeyNav;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class EmployeeDialog extends Window {
    private final FormPanel formPanel = new FormPanel();

    private final TextField<String> lastNameTextField = new TextField<String>();
    private final TextField<String> firstNameTextField = new TextField<String>();
    private final TextField<String> middleNameTextField = new TextField<String>();
    private final TextField<String> degreeTextField = new TextField<String>();
    private final TextField<String> positionTextField = new TextField<String>();

    private EmployeeModel currentEmployeeModel;

    public EmployeeDialog() {
        setHeading(MESSAGES.addEmployeeDialogHeading());
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setModal(true);
        setSize(400, 230);

        formPanel.setHeaderVisible(false);
        formPanel.setLabelWidth(100);

        lastNameTextField.setFieldLabel(MESSAGES.lastName());
        lastNameTextField.setAutoWidth(true);
        lastNameTextField.setAllowBlank(false);

        firstNameTextField.setFieldLabel(MESSAGES.firstName());
        firstNameTextField.setAutoWidth(true);
        firstNameTextField.setAllowBlank(false);

        middleNameTextField.setFieldLabel(MESSAGES.middleName());
        middleNameTextField.setAutoWidth(true);
        middleNameTextField.setAllowBlank(false);

        degreeTextField.setFieldLabel(MESSAGES.degree());
        degreeTextField.setAutoWidth(true);
        degreeTextField.setAllowBlank(false);

        positionTextField.setFieldLabel(MESSAGES.position());
        positionTextField.setAutoWidth(true);
        positionTextField.setAllowBlank(false);

    }

    @Override
    protected void onRender(final Element parent, final int pos) {
        super.onRender(parent, pos);

        new KeyNav<ComponentEvent>(formPanel) {
            @Override
            public void onEnter(final ComponentEvent ce) {
                saveOrUpdate();
            }
        };

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                saveOrUpdate();
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        formPanel.add(lastNameTextField, FormUtil.w5FormData);
        formPanel.add(firstNameTextField, FormUtil.w5FormData);
        formPanel.add(middleNameTextField, FormUtil.w5FormData);
        formPanel.add(positionTextField, FormUtil.w5FormData);
        formPanel.add(degreeTextField, FormUtil.w5FormData);

        formPanel.addButton(saveButton);
        formPanel.addButton(cancelButton);

        add(formPanel, new RowData(1, 1));
    }

    public EmployeeDialog(final EmployeeModel employeeModel) {
        this();
        this.currentEmployeeModel = employeeModel;

        setHeading(MESSAGES.editEmployeeDialogHeading());

        lastNameTextField.setValue(employeeModel.getLastName());
        firstNameTextField.setValue(employeeModel.getFirstName());
        middleNameTextField.setValue(employeeModel.getMiddleName());
        degreeTextField.setValue(employeeModel.getDegree());
        positionTextField.setValue(employeeModel.getPosition());
    }

    @Override
    public void show() {
        super.show();
        lastNameTextField.focus();
    }

    private void saveOrUpdate() {
        if (formPanel.isValid()) {
            final boolean adding = (currentEmployeeModel == null);

            if (currentEmployeeModel == null) {
                currentEmployeeModel = new EmployeeModel();
            }

            currentEmployeeModel.setLastName(lastNameTextField.getValue());
            currentEmployeeModel.setFirstName(firstNameTextField.getValue());
            currentEmployeeModel.setMiddleName(middleNameTextField.getValue());
            currentEmployeeModel.setDegree(degreeTextField.getValue());
            currentEmployeeModel.setPosition(positionTextField.getValue());

            EmployeeService.Util.getInstance().saveOrUpdate(
                    currentEmployeeModel,
                    new BaseAsyncCallback<EmployeeModel>() {
                        @Override
                        public void onSuccess(final EmployeeModel employeeModel) {
                            if (adding) {
                                Dispatcher.forwardEvent(AdminEvents.EmployeeAdded, employeeModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.addEmployeeSuccessMessage());
                            } else {
                                Dispatcher.forwardEvent(AdminEvents.EmployeeChanged, employeeModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editEmployeeSuccessMessage());
                            }
                        }
                    });

            hide();
        }
    }
}
