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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class NavigationPanel extends ContentPanel {
    public NavigationPanel() {
        setHeading("Настройки");
        setLayout(new AccordionLayout() {
            @Override
            protected void onBeforeExpand(final ComponentEvent ce) {
                super.onBeforeExpand(ce);

                for (final Component item : container.getItems()) {
                    if (item instanceof ContentPanel) {
                        ContentPanel contentPanel = (ContentPanel) item;
                        for (final Component component : contentPanel.getItems()) {
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
    protected void onRender(final Element parent, final int pos) {
        super.onRender(parent, pos);

        add(createGeneralSettingsPanel());
        add(createUniversityStructureSettingsPanel());
    }

    private ContentPanel createGeneralSettingsPanel() {
        final TreeStore<Setting> generalSettingsTreeStore = new TreeStore<Setting>();
        generalSettingsTreeStore.add(
                new Setting("Учебное заведение", "institution-icon", AdminEvents.InstituteSettingSelected), false);
        generalSettingsTreeStore.add(
                new Setting("Пользователи системы", "users-icon", AdminEvents.UsersSettingSelected), false);

        return new SettingsPanel("Основные", generalSettingsTreeStore);
    }

    private ContentPanel createUniversityStructureSettingsPanel() {
        final RpcProxy<List<ModelData>> proxy = new RpcProxy<List<ModelData>>() {
            @Override
            protected void load(final Object loadConfig, final AsyncCallback<List<ModelData>> settingAsyncCallback) {
                if (loadConfig == null) {
                    settingAsyncCallback.onSuccess(null);
                } else if (loadConfig instanceof Setting) {
                    final Setting setting = (Setting) loadConfig;

                    if (setting.getData().equals("faculties")) {
                        AdminService.Util.getInstance().loadFaculties(settingAsyncCallback);
                    } else if (setting.getData() instanceof FacultyModel) {
                        final Long facultyId = ((FacultyModel) setting.getData()).getId();

                        AdminService.Util.getInstance().loadSpecialities(facultyId, settingAsyncCallback);
                    } else if (setting.getData() instanceof SpecialityModel) {
                        final Long specialityId = ((SpecialityModel) setting.getData()).getId();

                        AdminService.Util.getInstance().loadGroups(specialityId, settingAsyncCallback);
                    }
                }
            }
        };

        final DataReader<List<Setting>> reader = new DataReader<List<Setting>>() {
            @SuppressWarnings({"unchecked"})
            @Override
            public List<Setting> read(Object loadConfig, Object data) {
                if (loadConfig == null) {
                    final Setting facultiesSetting = new Setting("Факультеты", "faculties-icon");
                    facultiesSetting.setEventType(AdminEvents.FacultiesSettingSelected);
                    facultiesSetting.setData("faculties");
                    facultiesSetting.setChildren(true);

                    return Arrays.asList(facultiesSetting);
                }

                if (loadConfig instanceof Setting && data instanceof List) {
                    final Setting setting = (Setting) loadConfig;

                    final List<Setting> settings = new ArrayList<Setting>();

                    if (setting.getData().equals("faculties")) {
                        for (final FacultyModel facultyModel : (List<FacultyModel>) data) {
                            final Setting facultySetting = new Setting(facultyModel.getName(), "faculty-icon");
                            facultySetting.setEventType(AdminEvents.FacultySettingSelected);
                            facultySetting.setData(facultyModel);
                            facultySetting.setChildren(true);
                            settings.add(facultySetting);
                        }
                    } else if (setting.getData() instanceof FacultyModel) {
                        for (final SpecialityModel specialityModel : (List<SpecialityModel>) data) {
                            final Setting specialitySetting = new Setting(specialityModel.getName(), "speciality-icon");
                            specialitySetting.setEventType(AdminEvents.SpecialitySettingSelected);
                            specialitySetting.setData(specialityModel);
                            specialitySetting.setChildren(true);
                            settings.add(specialitySetting);
                        }
                    } else if (setting.getData() instanceof SpecialityModel) {
                        for (final GroupModel groupModel : (List<GroupModel>) data) {
                            final Setting groupSetting = new Setting(groupModel.getName(), "group-icon");
                            groupSetting.setEventType(AdminEvents.GroupSettingSelected);
                            groupSetting.setData(groupModel);
                            settings.add(groupSetting);
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
        for (final Component component : getItems()) {
            if (component instanceof SettingsPanel) {
                ((SettingsPanel) component).reload();
            }
        }
    }
}
