package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminAppEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 2:45 PM
 */
public class AdminNavigationPanel extends ContentPanel {
    public AdminNavigationPanel() {
        setHeading("Настройки");
        setLayout(new AccordionLayout() {
            @Override
            protected void onBeforeExpand(ComponentEvent ce) {
                super.onBeforeExpand(ce);

                for (Component item : container.getItems()) {
                    if (item instanceof ContentPanel) {
                        ContentPanel contentPanel = (ContentPanel) item;
                        for (Component component : contentPanel.getItems()) {
                            if (component instanceof TreePanel) {
                                ((TreePanel) component).getSelectionModel().deselectAll();
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        add(createGeneralSettingsPanel());
        add(createUniversityStructureSettingsPanel());
    }

    private ContentPanel createGeneralSettingsPanel() {
        TreeStore<ModelData> generalSettingsTreeStore = new TreeStore<ModelData>();
        generalSettingsTreeStore.add(createSettingList(), false);

        TreePanel<ModelData> generalSettingsTreePanel = new TreePanel<ModelData>(generalSettingsTreeStore);
        generalSettingsTreePanel.setDisplayProperty("name");
        generalSettingsTreePanel.setIconProvider(new ModelIconProvider<ModelData>() {
            @Override
            public AbstractImagePrototype getIcon(ModelData model) {
                String iconStyleName = ((Setting)model).getIcon();
                return iconStyleName == null ? null : IconHelper.createStyle(iconStyleName);
            }
        });

        generalSettingsTreePanel.getSelectionModel().addSelectionChangedListener(
                new SelectionChangedListener<ModelData>() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent<ModelData> se) {
                        EventType eventType = ((Setting)se.getSelectedItem()).getEventType();
                        if (eventType != null) {
                            Dispatcher.forwardEvent(eventType);
                        }
                    }
                });


        ContentPanel generalPanel = new ContentPanel(new FitLayout());
        generalPanel.setHeading("Основные");
        generalPanel.add(generalSettingsTreePanel);

        return generalPanel;
    }

    private List<ModelData> createSettingList() {
        List<ModelData> settingsList = new ArrayList<ModelData>();

        settingsList.add(new Setting("Пользователи", "users-icon", AdminAppEvents.UserSettingSelected));

        return settingsList;
    }

    private ContentPanel createUniversityStructureSettingsPanel() {
        return null;
    }

    private class Setting extends BaseModelData {
        private Setting(String name, String icon, EventType eventType) {
            setName(name);
            setIcon(icon);
            setEventType(eventType);
        }

        public String getName() {
            return get("name");
        }

        public void setName(String name) {
            set("name", name);
        }

        public String getIcon() {
            return get("icon");
        }

        public void setIcon(String icon) {
            set("icon", icon);
        }

        public EventType getEventType() {
            return get("eventType");
        }

        public void setEventType(EventType eventType) {
            set("eventType", eventType);
        }
    }
}
