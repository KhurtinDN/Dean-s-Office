package ru.sgu.csit.inoc.deansoffice.login.client.gxt;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

/**
 * User: hd (KhurtinDN@gmail.com)
 * Date: Nov 3, 2010
 * Time: 6:58:58 AM
 */
public class LoginDialog extends Window {
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setHeading("Вход в систему \"Деканат\"");
        setAutoWidth(true);
        setModal(true);

        final FormPanel formPanel = new FormPanel();
        formPanel.setBorders(false);
        formPanel.getHeader().hide();
        formPanel.setAction("/j_spring_security_check");
        formPanel.setMethod(FormPanel.Method.POST);
        formPanel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        TextField<String> userNameTextField = new TextField<String>();
        userNameTextField.setName("j_username");
        userNameTextField.setFieldLabel("Логин");

        formPanel.add(userNameTextField);

        TextField<String> passwordTextField = new TextField<String>();
        passwordTextField.setPassword(true);
        passwordTextField.setName("j_password");
        passwordTextField.setFieldLabel("Пароль");

        formPanel.add(passwordTextField);

        formPanel.onFormSubmit();

        Button loginButton = new Button("Вход", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                formPanel.submit();
            }
        });

        formPanel.addListener(Events.Submit, new Listener<FormEvent>() {
            @Override
            public void handleEvent(FormEvent be) {
                com.google.gwt.user.client.Window.open("/", "_self", "");
            }
        });

        formPanel.addButton(loginButton);

        add(formPanel);

        userNameTextField.focus();
    }
}
