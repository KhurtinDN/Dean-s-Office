package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.AdministrationModel;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 11:39 AM
 */
@RemoteServiceRelativePath("GWTServices/AdminAdministrationService")
public interface AdministrationService extends RemoteService {

    AdministrationModel load();

    void update(AdministrationModel administrationModel);

    public static class Util {
        private static final AdministrationServiceAsync ourInstance = GWT.create(AdministrationService.class);

        public static AdministrationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}

