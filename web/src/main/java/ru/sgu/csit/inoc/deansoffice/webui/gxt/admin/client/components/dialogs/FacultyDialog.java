package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.KeyNav;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class FacultyDialog extends Window {
    private FormPanel formPanel = new FormPanel();

    private final TextField<String> fullNameTextField = new TextField<String>();
    private final TextField<String> nameTextField = new TextField<String>();
    private final ComboBox<EmployeeModel> deansComboBox = new ComboBox<EmployeeModel>();

    private FacultyModel currentFacultyModel;

    public FacultyDialog() {
        setHeading(MESSAGES.addFacultyDialogHeading());
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setModal(true);
        setSize(400, 180);

        formPanel.setHeaderVisible(false);

        fullNameTextField.setFieldLabel(MESSAGES.fullName());
        fullNameTextField.setAutoWidth(true);
        fullNameTextField.setAllowBlank(false);

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAutoWidth(true);
        nameTextField.setAllowBlank(false);

        deansComboBox.setFieldLabel(MESSAGES.dean());
        deansComboBox.setAutoWidth(true);
        deansComboBox.setAllowBlank(false);
        deansComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        deansComboBox.setDisplayField("fullName");

    }

    public FacultyDialog(final FacultyModel facultyModel) {
        this();
        this.currentFacultyModel = facultyModel;

        setHeading(MESSAGES.editFacultyDialogHeading());

        fullNameTextField.setValue(facultyModel.getFullName());
        nameTextField.setValue(facultyModel.getName());
        deansComboBox.setValue(facultyModel.getDean());
    }

    @Override
    protected void onRender(final Element parent, final int pos) {
        super.onRender(parent, pos);

        final RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                EmployeeService.Util.getInstance().loadDeanList(listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);
        deansComboBox.setStore(new ListStore<EmployeeModel>(loader));

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

        formPanel.add(fullNameTextField, FormUtil.w5FormData);
        formPanel.add(nameTextField, FormUtil.w5FormData);
        formPanel.add(deansComboBox, FormUtil.w5FormData);

        formPanel.addButton(saveButton);
        formPanel.addButton(cancelButton);

        add(formPanel, new RowData(1, 1));
    }

    @Override
    public void show() {
        super.show();
        fullNameTextField.focus();
    }

    private void saveOrUpdate() {
        if (formPanel.isValid()) {
            final boolean adding = (currentFacultyModel == null);

            if (currentFacultyModel == null) {
                currentFacultyModel = new FacultyModel();
            }

            currentFacultyModel.setFullName(fullNameTextField.getValue());
            currentFacultyModel.setName(nameTextField.getValue());
            currentFacultyModel.setDean(deansComboBox.getValue());

            FacultyService.Util.getInstance().saveOrUpdate(
                    currentFacultyModel,
                    new BaseAsyncCallback<FacultyModel>() {
                        @Override
                        public void onSuccess(final FacultyModel facultyModel) {
                            if (adding) {
                                Dispatcher.forwardEvent(AdminEvents.FacultyAdded, facultyModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.addFacultySuccessMessage());
                            } else {
                                Dispatcher.forwardEvent(AdminEvents.FacultyChanged, facultyModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editFacultySuccessMessage());
                            }
                        }
                    });

            hide();
        }
    }
}
