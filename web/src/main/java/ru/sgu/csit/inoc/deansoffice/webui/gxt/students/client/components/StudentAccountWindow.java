package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;

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

        addButton(new Button("Закрыть", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                StudentAccountWindow.this.hide();
            }
        }));
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Учетная карточка студента");
        setLayout(new FitLayout());

        TabPanel tabPanel = new TabPanel();
        tabPanel.add(createFaceTabItem());
        tabPanel.add(createBackTabItem());

        add(tabPanel, new FitData(4));
    }

    private TabItem createFaceTabItem() {
        TabItem faceTabItem = new TabItem("Лицевая сторона");

//        HTML html = new HTML();
        Html html = new Html();
        html.setStylePrimaryName("student-account");

        faceTabItem.addText("Здесь была лицевая сторона");

        return faceTabItem;
    }

    private String getHeaderTemplate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<img src=\"{photoUrl}\" />");
        stringBuilder.append("<h1>Учетная карточка студента</h1>");
        stringBuilder.append("<div>Фамилия: {lastName} Имя: {firstName} Отчество: {middleName}</div>");
        stringBuilder.append("<div>Специальность (направление): {speciality} Шифр: {middleName}</div>");

        return stringBuilder.toString();
    }

    private TabItem createBackTabItem() {
        TabItem backTabItem = new TabItem("Задняя сторона");
        backTabItem.addText("Здесь была задняя сторона");
        return backTabItem;
    }

    @Override
    public void show() {
        super.show();
        maximize();
    }
}
