package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components;

import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.utils.Setting;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.AdminService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 2:45 PM
 */
public class NavigationPanel extends ContentPanel {
    public NavigationPanel() {
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
        TreeStore<Setting> generalSettingsTreeStore = new TreeStore<Setting>();
        generalSettingsTreeStore.add(
                new Setting("Учебное заведение", "institution-icon", AdminEvents.InstituteSettingSelected), false);
        generalSettingsTreeStore.add(
                new Setting("Сотрудники учебного заведения", "staff-icon", AdminEvents.StaffSettingSelected), false);
        generalSettingsTreeStore.add(
                new Setting("Пользователи системы", "users-icon", AdminEvents.UsersSettingSelected), false);

        return new SettingsPanel("Основные", generalSettingsTreeStore);
    }

    private ContentPanel createUniversityStructureSettingsPanel() {
        RpcProxy<List<ModelData>> proxy = new RpcProxy<List<ModelData>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<ModelData>> settingAsyncCallback) {
                if (loadConfig == null) {
                    settingAsyncCallback.onSuccess(null);
                } else if (loadConfig instanceof Setting) {
                    Setting setting = (Setting) loadConfig;

                    if (setting.getData().equals("faculties")) {
                        AdminService.Util.getInstance().loadFaculties(settingAsyncCallback);
                    } else if (setting.getData() instanceof FacultyModel) {
                        Long facultyId = ((FacultyModel) setting.getData()).getId();

                        AdminService.Util.getInstance().loadSpecialities(facultyId, settingAsyncCallback);
                    }
                }
            }
        };

        DataReader<List<Setting>> reader = new DataReader<List<Setting>>() {
            @SuppressWarnings({"unchecked"})
            @Override
            public List<Setting> read(Object loadConfig, Object data) {
                if (loadConfig == null) {
                    Setting facultiesSetting = new Setting("Факультеты", "faculties-icon");
                    facultiesSetting.setEventType(AdminEvents.FacultiesSettingSelected);
                    facultiesSetting.setData("faculties");
                    facultiesSetting.setChildren(true);

                    return Arrays.asList(facultiesSetting);
                }

                if (loadConfig instanceof Setting && data instanceof List) {
                    Setting setting = (Setting) loadConfig;

                    List<Setting> settings = new ArrayList<Setting>();

                    if (setting.getData().equals("faculties")) {
                        for (FacultyModel facultyModel : (List<FacultyModel>) data) {
                            Setting facultySetting = new Setting(facultyModel.getName(), "faculty-icon");
                            facultySetting.setEventType(AdminEvents.FacultySettingSelected);
                            facultySetting.setData(facultyModel);
                            facultySetting.setChildren(true);
                            settings.add(facultySetting);
                        }
                    } else if (setting.getData() instanceof FacultyModel) {
                        for (SpecialityModel specialityModel : (List<SpecialityModel>) data) {
                            Setting specialitySetting = new Setting(specialityModel.getName(), "speciality-icon");
                            specialitySetting.setEventType(AdminEvents.SpecialitySettingSelected);
                            specialitySetting.setData(specialityModel);
                            settings.add(specialitySetting);
                        }
                    }

                    return settings;
                }

                assert false : "Error converting data";

                return null;
            }
        };

        return new SettingsPanel("Структура учебного заведения", proxy, reader);
    }

    public void reload() {
        for (Component component : getItems()) {
            if (component instanceof SettingsPanel) {
                ((SettingsPanel) component).reload();
            }
        }
    }
}
