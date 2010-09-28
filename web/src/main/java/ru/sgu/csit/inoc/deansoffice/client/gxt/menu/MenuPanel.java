package ru.sgu.csit.inoc.deansoffice.client.gxt.menu;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        ArrayList<Map<Speciality, List<Group>>> list = new ArrayList<Map<Speciality, List<Group>>>();

//        MenuService.App.getInstance().downloadMenuData(new MenuLoader());

        /*for (int course = 1; course <= 6; ++course) {
            final ContentPanel courseContentPanel = new ContentPanel(new FitLayout());
            courseContentPanel.setAnimCollapse(false);
            courseContentPanel.setHeading(course + " курс");

            MenuService.App.getInstance().downloadSpecialityName(new AsyncCallback<Map<Long, String>>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(caught.toString());
                }

                @Override
                public void onSuccess(Map<Long, String> result) {
                    final TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
                    TreePanel<ModelData> treePanel = new TreePanel<ModelData>(treeStore);
                    treePanel.setDisplayProperty("name");

                    for (Map.Entry<Long, String> entry : result.entrySet()) {
                        final ModelData modelData = createModelData(entry.getValue(), null);
                        treeStore.add(modelData, false);

                        MenuService.App.getInstance().downloadGroupName(entry.getKey(),
                                new AsyncCallback<Map<Long, String>>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                        Window.alert(caught.toString());
                                    }

                                    @Override
                                    public void onSuccess(Map<Long, String> result) {
                                        for (Map.Entry<Long, String> entry : result.entrySet()) {
                                            treeStore.add(modelData, createModelData(entry.getValue(), null), false);
                                        }
                                    }
                                });

                        treePanel.setExpanded(modelData, true);
                    }

                    courseContentPanel.add(treePanel);

                }
            });

            add(courseContentPanel);
        }*/
    }

    private ModelData createModelData(String name, String icon) {
        ModelData modelData = new BaseModelData();
        modelData.set("name", name);
        modelData.set("icon", icon);
        return modelData;
    }

    /*private class MenuLoader implements AsyncCallback<ArrayList<Map<Speciality, List<Group>>>> {
        @Override
        public void onFailure(Throwable caught) {
            Window.alert(caught.toString());
        }

        @Override
        public void onSuccess(ArrayList<Map<Speciality, List<Group>>> resultList) {
            for (int course = 1; course <= resultList.size(); ++course) {
                ContentPanel courseContentPanel = new ContentPanel(new FitLayout());
                courseContentPanel.setAnimCollapse(false);
                courseContentPanel.setHeading(course + " курс");

                Map<Speciality, List<Group>> specialityGroupMap = resultList.get(course);

                TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
                TreePanel<ModelData> treePanel = new TreePanel<ModelData>(treeStore);
                treePanel.setDisplayProperty("name");

                for (Map.Entry<Speciality, List<Group>> entry : specialityGroupMap.entrySet()) {
                    ModelData modelData = createModelData(entry.getKey().getName(), null);
                    treeStore.add(modelData, false);

                    for (Group group : entry.getValue()) {
                        treeStore.add(modelData, createModelData(group.getName(), null), false);
                    }
                    treePanel.setExpanded(modelData, true);
                }
                courseContentPanel.add(treePanel);

                add(courseContentPanel);
            }
        }
    }*/
}
