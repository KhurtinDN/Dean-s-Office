package ru.sgu.csit.inoc.deansoffice.client.gxt;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 12, 2010
 * Time: 9:59:46 PM
 */
public class MenuPanel extends ContentPanel {
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setHeading("Структура факультета");
        setLayout(new AccordionLayout());

        ContentPanel firstCourseContentPanel = new ContentPanel(new FitLayout());
        firstCourseContentPanel.setAnimCollapse(false);
        firstCourseContentPanel.setHeading("1 курс");

        TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
        TreePanel<ModelData> treePanel = new TreePanel<ModelData>(treeStore);
        treePanel.setDisplayProperty("name");

        ModelData modelData = newItem("Прикладная математика", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("111", null), false);
        treeStore.add(modelData, newItem("112", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Вычислительные машины", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("121", null), false);
        treeStore.add(modelData, newItem("122", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Компьютерная безопасность", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("131", null), false);
        treeStore.add(modelData, newItem("132", null), false);
        treePanel.setExpanded(modelData, true);

        firstCourseContentPanel.add(treePanel);

        ContentPanel secondCourseContentPanel = new ContentPanel(new FitLayout());
        secondCourseContentPanel.setAnimCollapse(false);
        secondCourseContentPanel.setHeading("2 курс");

        treeStore = new TreeStore<ModelData>();
        treePanel = new TreePanel<ModelData>(treeStore);
        treePanel.setDisplayProperty("name");

        modelData = newItem("Прикладная математика", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("211", null), false);
        treeStore.add(modelData, newItem("212", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Вычислительные машины", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("221", null), false);
        treeStore.add(modelData, newItem("222", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Компьютерная безопасность", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("231", null), false);
        treeStore.add(modelData, newItem("232", null), false);
        treePanel.setExpanded(modelData, true);

        secondCourseContentPanel.add(treePanel);

        ContentPanel thirdCourseContentPanel = new ContentPanel(new FitLayout());
        thirdCourseContentPanel.setAnimCollapse(false);
        thirdCourseContentPanel.setHeading("3 курс");

        treeStore = new TreeStore<ModelData>();
        treePanel = new TreePanel<ModelData>(treeStore);
        treePanel.setDisplayProperty("name");

        modelData = newItem("Прикладная математика", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("311", null), false);
        treeStore.add(modelData, newItem("312", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Вычислительные машины", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("321", null), false);
        treeStore.add(modelData, newItem("322", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Компьютерная безопасность", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("331", null), false);
        treeStore.add(modelData, newItem("332", null), false);
        treePanel.setExpanded(modelData, true);

        thirdCourseContentPanel.add(treePanel);

        ContentPanel fourthCourseContentPanel = new ContentPanel(new FitLayout());
        fourthCourseContentPanel.setAnimCollapse(false);
        fourthCourseContentPanel.setHeading("4 курс");

        treeStore = new TreeStore<ModelData>();
        treePanel = new TreePanel<ModelData>(treeStore);
        treePanel.setDisplayProperty("name");

        modelData = newItem("Прикладная математика", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("411", null), false);
        treeStore.add(modelData, newItem("412", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Вычислительные машины", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("421", null), false);
        treeStore.add(modelData, newItem("422", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Компьютерная безопасность", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("431", null), false);
        treeStore.add(modelData, newItem("432", null), false);
        treePanel.setExpanded(modelData, true);

        fourthCourseContentPanel.add(treePanel);

        ContentPanel fifthCourseContentPanel = new ContentPanel(new FitLayout());
        fifthCourseContentPanel.setAnimCollapse(false);
        fifthCourseContentPanel.setHeading("5 курс");

        treeStore = new TreeStore<ModelData>();
        treePanel = new TreePanel<ModelData>(treeStore);
        treePanel.setDisplayProperty("name");

        modelData = newItem("Прикладная математика", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("511", null), false);
        treeStore.add(modelData, newItem("512", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Вычислительные машины", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("521", null), false);
        treeStore.add(modelData, newItem("522", null), false);
        treePanel.setExpanded(modelData, true);

        modelData = newItem("Компьютерная безопасность", null);
        treeStore.add(modelData, false);
        treeStore.add(modelData, newItem("531", null), false);
        treeStore.add(modelData, newItem("532", null), false);
        treePanel.setExpanded(modelData, true);

        fifthCourseContentPanel.add(treePanel);

        add(firstCourseContentPanel);
        add(secondCourseContentPanel);
        add(thirdCourseContentPanel);
        add(fourthCourseContentPanel);
        add(fifthCourseContentPanel);
    }

    private ModelData newItem(String name, String icon) {
        ModelData modelData = new BaseModelData();
        modelData.set("name", name);
        modelData.set("icon", icon);
        return modelData;
    }
}
