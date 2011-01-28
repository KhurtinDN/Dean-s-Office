package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.StudentService;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 5:26 PM
 */
public class NavigationPanel extends ContentPanel {
    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Структура факультета");

        VBoxLayout vBoxLayout = new VBoxLayout();
        vBoxLayout.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.STRETCH);
        setLayout(vBoxLayout);

        StudentService.App.getInstance().getCourseCount(new AsyncCallback<Integer>() {
            @Override
            public void onSuccess(Integer courseCount) {
                LayoutContainer navigationContainer = new LayoutContainer(new AccordionLayout() {
                    /*@Override
                    protected void onExpand(ComponentEvent ce) {
                        layoutContainer();
                    }*/
                });

                ContentPanel firstPanel = new ContentPanel(); // for normal fly expanding
                firstPanel.setHeaderVisible(false);
                navigationContainer.add(firstPanel);

                for (int course = 1; course <= courseCount; ++course) {
                    TreeLoader<BaseModel> loader = createTreeLoader(course);
                    TreeStore<BaseModel> treeStore = new TreeStore<BaseModel>(loader);
                    TreePanel<BaseModel> treePanel = new TreePanel<BaseModel>(treeStore);
                    treePanel.setDisplayProperty("name");
                    treePanel.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<BaseModel>() {
                        @Override
                        public void selectionChanged(SelectionChangedEvent<BaseModel> se) {
                            BaseModel baseModel = se.getSelectedItem();

                            if (baseModel instanceof SpecialityModel) {
                                Dispatcher.forwardEvent(AppEvents.SpecialitySelected, baseModel);
                            } else if (baseModel instanceof GroupModel) {
                                Dispatcher.forwardEvent(AppEvents.GroupSelected, baseModel);
                            }
                        }
                    });
                    treePanel.setAutoExpand(true);

                    loader.load();

                    ContentPanel courseContentPanel = new ContentPanel(new FitLayout());
                    courseContentPanel.setHeading(course + " курс");
                    courseContentPanel.setAnimCollapse(false);
                    courseContentPanel.add(treePanel);

                    navigationContainer.add(courseContentPanel);
                }

                NavigationPanel.this.add(navigationContainer);
                NavigationPanel.this.layout();
            }

            @Override
            public void onFailure(Throwable caught) {
                Info.display("Сообщение от сервера", "Сервер скорее всего не доступен!");
            }
        });
    }

    TreeLoader<BaseModel> createTreeLoader(final Integer course) {
        RpcProxy<List<BaseModel>> proxy = new RpcProxy<List<BaseModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<BaseModel>> listAsyncCallback) {
                BaseModel parent = (BaseModel) loadConfig;
                StudentService.App.getInstance().loadNavigationTree(course, parent, listAsyncCallback);
            }
        };

        return new BaseTreeLoader<BaseModel>(proxy) {
            @Override
            public boolean hasChildren(BaseModel parent) {
                return parent instanceof SpecialityModel;
            }
        };
    }
}
