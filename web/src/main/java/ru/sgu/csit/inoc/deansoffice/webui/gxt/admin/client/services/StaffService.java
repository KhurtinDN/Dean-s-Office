package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 11:39 AM
 */
@RemoteServiceRelativePath("GWTServices/AdminStaffService")
public interface StaffService extends RemoteService {

    List<PersonModel> loadStaffList();
    List<PersonModel> loadDeanList();

    public static class Util {
        private static final StaffServiceAsync ourInstance = GWT.create(StaffService.class);

        public static StaffServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
