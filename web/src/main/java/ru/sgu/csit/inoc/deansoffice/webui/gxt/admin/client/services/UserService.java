package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 3:43 PM
 */
@RemoteServiceRelativePath("GWTServices/AdminUserService")
public interface UserService extends RemoteService {

    List<UserModel> loadUsers();

    public static class Util {
        private static final UserServiceAsync ourInstance = GWT.create(UserService.class);

        public static UserServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
