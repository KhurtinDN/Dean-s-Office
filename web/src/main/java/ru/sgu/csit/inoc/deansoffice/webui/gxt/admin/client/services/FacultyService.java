package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/25/11
 * Time: 2:21 PM
 */
@RemoteServiceRelativePath("GWTServices/AdminFacultyService")
public interface FacultyService extends RemoteService {
    List<FacultyModel> loadFaculties();

    FacultyModel createFaculty();
    void updateFaculty(FacultyModel facultyModel);
    void deleteFaculties(List<Long> facultyIdList);

    public static class Util {
        private static final FacultyServiceAsync ourInstance = GWT.create(FacultyService.class);

        public static FacultyServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
