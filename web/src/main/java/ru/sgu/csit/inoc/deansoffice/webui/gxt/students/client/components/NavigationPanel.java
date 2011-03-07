package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.AppService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

import java.util.List;
import java.util.Map;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 5:26 PM
 */
public class NavigationPanel extends ContentPanel {
    private LayoutContainer navigationContainer;

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Структура факультета");

        VBoxLayout vBoxLayout = new VBoxLayout();
        vBoxLayout.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.STRETCH);
        setLayout(vBoxLayout);

        navigationContainer = new LayoutContainer(new AccordionLayout() {
            @Override
            protected void onBeforeExpand(ComponentEvent ce) {
                super.onBeforeExpand(ce);

                for (Component item : container.getItems()) {
                    if (item instanceof ContentPanel) {
                        ContentPanel contentPanel = (ContentPanel)item;
                        for (Component component : contentPanel.getItems()) {
                            if (component instanceof TreePanel) {
                                ((TreePanel)component).getSelectionModel().deselectAll();
                            }
                        }
                    }
                }
            }
            /*@Override
            protected void onExpand(ComponentEvent ce) {
                layoutContainer();
            }*/
        });

        add(navigationContainer);
    }

    public void reloadMenuData(FacultyModel facultyModel) {
        navigationContainer.removeAll();
        AppService.App.getInstance().loadMenuData(facultyModel.getId(), new MenuLoader());
    }

    private class MenuLoader implements AsyncCallback<List<Map<SpecialityModel, List<GroupModel>>>> {
        @Override
        public void onFailure(Throwable caught) {
            AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
            appEvent.setData("throwable", caught);
            Dispatcher.forwardEvent(appEvent);
        }

        @Override
        public void onSuccess(List<Map<SpecialityModel, List<GroupModel>>> resultList) {
            ContentPanel firstPanel = new ContentPanel(); // for normal fly expanding
            firstPanel.setHeaderVisible(false);
            navigationContainer.add(firstPanel);

            for (int course = 1; course <= resultList.size(); ++course) {
                TreeStore<BaseModel> treeStore = new TreeStore<BaseModel>();
                TreePanel<BaseModel> treePanel = new TreePanel<BaseModel>(treeStore) {
                    @Override
                    protected BaseModel prepareData(BaseModel model) {
                        if (model instanceof SpecialityModel) {
                            model.set("name", model.get("shortName"));
                        }
                        return super.prepareData(model);
                    }
                };
                treePanel.setDisplayProperty("name");
                treePanel.getStyle().setLeafIcon(IconHelper.createStyle("group-icon"));

                final Integer finalCourse = course;
                treePanel.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<BaseModel>() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent<BaseModel> se) {
                        BaseModel baseModel = se.getSelectedItem();

                        if (baseModel instanceof SpecialityModel) {
                            AppEvent appEvent = new AppEvent(AppEvents.SpecialitySelected, baseModel);
                            appEvent.setData("course", finalCourse);
                            Dispatcher.forwardEvent(appEvent);
                        } else if (baseModel instanceof GroupModel) {
                            Dispatcher.forwardEvent(AppEvents.GroupSelected, baseModel);
                        }
                    }
                });
                treePanel.setAutoExpand(true);

                ContentPanel courseContentPanel = new ContentPanel(new FitLayout());
                courseContentPanel.setHeading(course + " курс");
                courseContentPanel.setAnimCollapse(false);
                courseContentPanel.add(treePanel);

                navigationContainer.add(courseContentPanel);

                Map<SpecialityModel, List<GroupModel>> specialityGroupMap = resultList.get(course - 1);

                for (Map.Entry<SpecialityModel, List<GroupModel>> entry : specialityGroupMap.entrySet()) {
                    SpecialityModel specialityModel = entry.getKey();
                    treeStore.add(specialityModel, false);

                    for (GroupModel groupModel : entry.getValue()) {
                        treeStore.add(specialityModel, groupModel, false);
                    }
                }
            }

            NavigationPanel.this.layout();
        }
    }
}
