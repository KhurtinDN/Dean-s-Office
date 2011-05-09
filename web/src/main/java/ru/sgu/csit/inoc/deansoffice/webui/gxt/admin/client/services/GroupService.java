package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/18/11
 * Time: 10:53 AM
 */
@RemoteServiceRelativePath("GWTServices/AdminGroupService")
public interface GroupService extends RemoteService {

    List<GroupModel> loadGroups(Long specialityId);

    GroupModel create(Long specialityId);
    void update(GroupModel groupModel);
    void delete(List<Long> groupIdList);

    public static class Util {
        private static final GroupServiceAsync ourInstance = GWT.create(GroupService.class);

        public static GroupServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
