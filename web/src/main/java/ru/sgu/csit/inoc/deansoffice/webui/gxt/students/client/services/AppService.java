package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;

import java.util.List;
import java.util.Map;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/10/11
 * Time: 1:07 PM
 */
@RemoteServiceRelativePath("GWTServices/AppService")
public interface AppService extends RemoteService {

    public FacultyModel loadFaculty();
    public List<Map<SpecialityModel, List<GroupModel>>> loadMenuData(Long facultyId);

    public static class App {
        private static final AppServiceAsync ourInstance = GWT.create(AppService.class);

        public static AppServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
