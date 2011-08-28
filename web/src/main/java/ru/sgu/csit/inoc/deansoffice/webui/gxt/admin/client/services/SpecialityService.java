package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 10:50 PM
 */
@RemoteServiceRelativePath("GWTServices/AdminSpecialityService")
public interface SpecialityService extends RemoteService {

    List<SpecialityModel> loadSpecialities(Long facultyId);
    SpecialityModel loadSpeciality(Long specialityId);

    SpecialityModel saveOrUpdate(SpecialityModel specialityModel);
    void deleteSpecialities(List<Long> specialityIdList);

    public static class Util {
        private static final SpecialityServiceAsync ourInstance = GWT.create(SpecialityService.class);

        public static SpecialityServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
