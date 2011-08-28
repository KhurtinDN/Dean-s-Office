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
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.UserService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class UserDialog extends Window {
    private final FormPanel formPanel = new FormPanel();

    private final TextField<String> lastNameTextField = new TextField<String>();
    private final TextField<String> firstNameTextField = new TextField<String>();
    private final TextField<String> middleNameTextField = new TextField<String>();
    private final TextField<String> loginTextField = new TextField<String>();
    private final TextField<String> passwordTextField = new TextField<String>();

    private UserModel currentUserModel;

    public UserDialog() {
        setHeading(MESSAGES.addUserDialogHeading());
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setModal(true);
        setSize(400, 230);

        formPanel.setHeaderVisible(false);

        lastNameTextField.setFieldLabel(MESSAGES.lastName());
        lastNameTextField.setAutoWidth(true);
        lastNameTextField.setAllowBlank(false);

        firstNameTextField.setFieldLabel(MESSAGES.firstName());
        firstNameTextField.setAutoWidth(true);
        firstNameTextField.setAllowBlank(false);

        middleNameTextField.setFieldLabel(MESSAGES.middleName());
        middleNameTextField.setAutoWidth(true);
        middleNameTextField.setAllowBlank(false);

        loginTextField.setFieldLabel(MESSAGES.login());
        loginTextField.setAutoWidth(true);
        loginTextField.setAllowBlank(false);

        passwordTextField.setFieldLabel(MESSAGES.password());
        passwordTextField.setAutoWidth(true);
        passwordTextField.setAllowBlank(false);
    }

    public UserDialog(final UserModel userModel) {
        this();
        this.currentUserModel = userModel;

        setHeading(MESSAGES.editUserDialogHeading());

        lastNameTextField.setValue(userModel.getLastName());
        firstNameTextField.setValue(userModel.getFirstName());
        middleNameTextField.setValue(userModel.getMiddleName());
        loginTextField.setValue(userModel.getLogin());
        passwordTextField.setValue(userModel.getPassword());
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
        formPanel.add(loginTextField, FormUtil.w5FormData);
        formPanel.add(passwordTextField, FormUtil.w5FormData);

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
            final boolean adding = (currentUserModel == null);

            if (currentUserModel == null) {
                currentUserModel = new UserModel();
            }

            currentUserModel.setLastName(lastNameTextField.getValue());
            currentUserModel.setFirstName(firstNameTextField.getValue());
            currentUserModel.setMiddleName(middleNameTextField.getValue());
            currentUserModel.setLogin(loginTextField.getValue());
            currentUserModel.setPassword(passwordTextField.getValue());

            UserService.Util.getInstance().saveOrUpdate(
                    currentUserModel,
                    new BaseAsyncCallback<UserModel>() {
                        @Override
                        public void onSuccess(final UserModel userModel) {
                            if (adding) {
                                Dispatcher.forwardEvent(AdminEvents.UserAdded, userModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.addUserSuccessMessage());
                            } else {
                                Dispatcher.forwardEvent(AdminEvents.UserChanged, userModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editUserSuccessMessage());
                            }
                        }
                    });

            hide();
        }
    }
}
