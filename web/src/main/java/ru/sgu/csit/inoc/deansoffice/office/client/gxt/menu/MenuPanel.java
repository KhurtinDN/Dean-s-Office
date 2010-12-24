package ru.sgu.csit.inoc.deansoffice.office.client.gxt.menu;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.content.ReferenceBodyPanel;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.GroupDto;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.SpecialityDto;

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
    private ReferenceBodyPanel referenceBodyPanel;

    private final MenuSelectHandler menuSelectHandler = new MenuSelectHandler();

    public MenuPanel(ReferenceBodyPanel referenceBodyPanel) {
        this.referenceBodyPanel = referenceBodyPanel;
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
//            Window.alert(caught.toString());
            Info.display("Сообщение от сервера", "Запрашиваемый сервис не доступен!");
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
                    SpecialityDto specialityDto = entry.getKey();
                    ModelData specialityModelData = createModelData(specialityDto.getId(), "speciality",
                            specialityDto.getName(), null);
                    treeStore.add(specialityModelData, false);

                    for (GroupDto groupDto : entry.getValue()) {
                        ModelData groupModelData = createModelData(groupDto.getId(), "group", groupDto.getName(), null);
                        treeStore.add(specialityModelData, groupModelData, false);
                    }
                    treePanel.setExpanded(specialityModelData, true);
                }
                treePanel.getSelectionModel().addSelectionChangedListener(menuSelectHandler);
                courseContentPanel.add(treePanel);

                add(courseContentPanel);
            }
            panel.getLayout().layout();
        }

        private ModelData createModelData(Long id, String type, String name, String icon) {
            ModelData modelData = new BaseModelData();
            modelData.set("id", id);
            modelData.set("type", type);
            modelData.set("name", name);
            modelData.set("icon", icon);
            return modelData;
        }
    }

    private class MenuSelectHandler extends SelectionChangedListener<ModelData> {
        @Override
        public void selectionChanged(SelectionChangedEvent<ModelData> se) {
            Long id = se.getSelectedItem().get("id");
            String type = se.getSelectedItem().get("type");
            String name = se.getSelectedItem().get("name");
            if ("group".equals(type)) {
                referenceBodyPanel.showGroup(id, name);
            } else if ("speciality".equals(type)) {
                referenceBodyPanel.showSpeciality(id, name);
            }
        }

        public void processGroup() {
            ;
        }
    }
}
