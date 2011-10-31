package ru.sgu.csit.inoc.deansoffice.webui.gxt.login.client;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.util.KeyNav;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;

/**
 * @author Denis Khurtin
 */
public class LoginDialog extends Window {
    private final TextField<String> userNameTextField = new TextField<String>();
    private final FormPanel formPanel = new FormPanel();

    public LoginDialog() {
        setHeading("Вход в систему \"Деканат\"");
        setWidth(330);
        setModal(true);

        formPanel.setHeaderVisible(false);
        formPanel.setBorders(false);
        formPanel.setAutoWidth(true);
        formPanel.setAction(GWT.getHostPageBaseURL() + "j_spring_security_check");
        formPanel.setMethod(FormPanel.Method.POST);

        formPanel.addListener(Events.Submit, new Listener<FormEvent>() {
            @Override
            public void handleEvent(FormEvent be) {
                com.google.gwt.user.client.Window.open(GWT.getHostPageBaseURL(), "_self", "");
            }
        });

        userNameTextField.setAutoWidth(true);
        userNameTextField.setName("j_username");
        userNameTextField.setFieldLabel("Логин");

        final TextField<String> passwordTextField = new TextField<String>();
        passwordTextField.setAutoWidth(true);
        passwordTextField.setPassword(true);
        passwordTextField.setName("j_password");
        passwordTextField.setFieldLabel("Пароль");

        final CheckBox rememberMeCheckBox = new CheckBox();
        rememberMeCheckBox.setAutoWidth(true);
        rememberMeCheckBox.setName("_spring_security_remember_me");
        rememberMeCheckBox.setBoxLabel("Запомнить меня");

        formPanel.add(userNameTextField);
        formPanel.add(passwordTextField);
        formPanel.add(rememberMeCheckBox);

        setButtonAlign(Style.HorizontalAlignment.CENTER);


        addButton(new Button("Вход", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                tryLogin();
            }
        }));

        new KeyNav<ComponentEvent>(formPanel) {
            @Override
            public void onEnter(ComponentEvent ce) {
                tryLogin();
            }
        };

        add(formPanel);
    }

    private void tryLogin() {
        formPanel.submit();
    }

    @Override
    public void show() {
        super.show();
        userNameTextField.focus();
    }
}
