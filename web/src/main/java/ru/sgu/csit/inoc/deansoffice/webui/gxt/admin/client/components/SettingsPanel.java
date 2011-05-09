package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.utils.Setting;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 3:00 PM
 */
public class SettingsPanel extends ContentPanel {
    private TreeStore<Setting> store;

    public SettingsPanel(String header, TreeStore<Setting> store) {
        this.store = store;

        setHeading(header);
        setLayout(new FitLayout());

        TreePanel<Setting> structureSettingsTreePanel = new TreePanel<Setting>(store);
        structureSettingsTreePanel.setDisplayProperty("name");
        structureSettingsTreePanel.setAutoExpand(true);

        structureSettingsTreePanel.setIconProvider(new ModelIconProvider<Setting>() {
            @Override
            public AbstractImagePrototype getIcon(Setting setting) {
                return setting.getIcon() == null ? null : IconHelper.createStyle(setting.getIcon());
            }
        });

        structureSettingsTreePanel.getSelectionModel().addSelectionChangedListener(
                new SelectionChangedListener<Setting>() {
                    @Override
                    public void selectionChanged(SelectionChangedEvent<Setting> se) {
                        Setting setting = se.getSelectedItem();

                        if (setting != null && setting.getEventType() != null) {
                            Dispatcher.forwardEvent(setting.getEventType(), setting.getData());
                        }
                    }
                });

        add(structureSettingsTreePanel);

        reload();
    }

    public SettingsPanel(String header, TreeLoader<Setting> loader) {
        this(header, new TreeStore<Setting>(loader));
    }

    public SettingsPanel(String header, DataProxy proxy, DataReader reader) {
        this(header, new BaseTreeLoader<Setting>(proxy, reader) {
            @Override
            public boolean hasChildren(Setting parent) {
                return parent.hasChildren();
            }
        });
    }

    public void reload() {
        if (store.getLoader() != null) {
            store.getLoader().load();
        }
    }
}
