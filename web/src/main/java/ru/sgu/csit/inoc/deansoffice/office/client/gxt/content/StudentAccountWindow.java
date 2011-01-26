package ru.sgu.csit.inoc.deansoffice.office.client.gxt.content;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;

/**
 * User: KhurtinDN ( KhurtinDN@gmail.com )
 * Date: 12/28/10
 * Time: 3:04 PM
 */
public class StudentAccountWindow extends Window {

    public StudentAccountWindow() {
        setSize(800, 600);
        setModal(true);
        setBlinkModal(true);
        setMaximizable(true);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Учетная карточка студента");
        setLayout(new FitLayout());

        TabPanel tabPanel = new TabPanel();

        TabItem faceTabItem = new TabItem("Лицевая сторона");
        faceTabItem.addText("Здесь была лицевая сторона");

        TabItem backTabItem = new TabItem("Задняя сторона");
        backTabItem.addText("Здесь была задняя сторона");

        tabPanel.add(faceTabItem);
        tabPanel.add(backTabItem);

        add(tabPanel, new FitData(4));

        addButton(new Button("Закрыть", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentAccountWindow.this.hide();
            }
        }));

        layout(true);
    }

    @Override
    public void show() {
        super.show();
        maximize();
    }

    private class FrontPanel extends FormPanel {
        @Override
        protected void onRender(Element target, int index) {
            super.onRender(target, index);

//            TextField familyTextField = new TextField<>();
        }
    }
}
