package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.FormUtil;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/14/11
 * Time: 2:50 PM
 */
public abstract class AbstractDirectiveWindow extends Window {
    private TextArea descriptionTextField = new TextArea();
    private TextArea groundTextField = new TextArea();

    protected Button addButton = new Button("Добавить");

    public AbstractDirectiveWindow() {
        setSize(600, 600);
        setModal(true);
        setBlinkModal(true);

        addButton(addButton);
        addButton(new Button("Отменить", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        }));
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setLayout(new BorderLayout());

        descriptionTextField.setFieldLabel("Описание");
        descriptionTextField.setHeight(35);

        groundTextField.setFieldLabel("Основания");
        groundTextField.setHeight(35);

        FormPanel northPanel = new FormPanel();
        northPanel.setHeaderVisible(false);
        northPanel.add(descriptionTextField, FormUtil.wFormData);

        FormPanel southPanel = new FormPanel();
        southPanel.setHeaderVisible(false);
        southPanel.add(groundTextField, FormUtil.wFormData);

        BorderLayoutData northData = new BorderLayoutData(Style.LayoutRegion.NORTH, 60);
        BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH, 60);

        add(northPanel, northData);
        add(southPanel, southData);
    }
}
