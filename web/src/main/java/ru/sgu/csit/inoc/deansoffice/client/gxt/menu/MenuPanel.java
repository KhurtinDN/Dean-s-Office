package ru.sgu.csit.inoc.deansoffice.client.gxt.menu;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.client.gxt.content.BodyPanel;
import ru.sgu.csit.inoc.deansoffice.shared.dto.GroupDto;
import ru.sgu.csit.inoc.deansoffice.shared.dto.SpecialityDto;

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
    private BodyPanel bodyPanel;
    public MenuPanel(BodyPanel bodyPanel) {
        this.bodyPanel = bodyPanel;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        setHeading("Структура факультета");
        setLayout(new AccordionLayout());

        MenuService.App.getInstance().downloadMenuData(new MenuLoader(this));
    }

    private class MenuLoader implements AsyncCallback<ArrayList<Map<SpecialityDto, List<GroupDto>>>> {
        private ContentPanel panel;

        public MenuLoader(ContentPanel panel) {
            this.panel = panel;
        }

        @Override
        public void onFailure(Throwable caught) {
            Window.alert(caught.toString());
        }

        @Override
        public void onSuccess(ArrayList<Map<SpecialityDto, List<GroupDto>>> resultList) {
            for (int course = 1; course <= resultList.size(); ++course) {
                ContentPanel courseContentPanel = new ContentPanel(new FitLayout());
                courseContentPanel.setAnimCollapse(false);
                courseContentPanel.setHeading(course + " курс");

                Map<SpecialityDto, List<GroupDto>> specialityGroupMap = resultList.get(course - 1);

                TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
                TreePanel<ModelData> treePanel = new TreePanel<ModelData>(treeStore);
                treePanel.setDisplayProperty("name");

                for (Map.Entry<SpecialityDto, List<GroupDto>> entry : specialityGroupMap.entrySet()) {
                    ModelData modelData = createModelData(entry.getKey().getName(), null);
                    treeStore.add(modelData, false);

                    for (GroupDto group : entry.getValue()) {
                        treeStore.add(modelData, createGroupModelData(group), false);
                    }
                    treePanel.setExpanded(modelData, true);
                }
                treePanel.getSelectionModel().addSelectionChangedListener(new GroupSelectHandler());
                courseContentPanel.add(treePanel);

                add(courseContentPanel);
            }
            panel.getLayout().layout();
        }

        private ModelData createGroupModelData(GroupDto groupDto) {
            ModelData groupModelData = createModelData(groupDto.getName(), null);
            groupModelData.set("groupId", groupDto.getId());
            return groupModelData;
        }

        private ModelData createModelData(String name, String icon) {
            ModelData modelData = new BaseModelData();
            modelData.set("name", name);
            modelData.set("icon", icon);
            return modelData;
        }
    }

    private class GroupSelectHandler extends SelectionChangedListener<ModelData> {
        @Override
        public void selectionChanged(SelectionChangedEvent<ModelData> se) {
            Long groupId = se.getSelectedItem().get("groupId");
            String name = se.getSelectedItem().get("name");
            bodyPanel.showGroup(groupId, name);
        }
    }
}
