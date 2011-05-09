package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 9:48 AM
 */
@SuppressWarnings({"NonSerializableServiceParameters"})
@RemoteServiceRelativePath("GWTServices/AdminService")
public interface AdminService extends RemoteService {

    List<ModelData> loadFaculties();
    List<ModelData> loadSpecialities(Long facultyId);
    List<ModelData> loadGroups(Long specialityId);

    public static class Util {
        private static final AdminServiceAsync ourInstance = GWT.create(AdminService.class);

        public static AdminServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
