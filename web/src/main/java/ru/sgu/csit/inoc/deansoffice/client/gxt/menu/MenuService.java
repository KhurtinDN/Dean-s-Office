package ru.sgu.csit.inoc.deansoffice.client.gxt.menu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 16, 2010
 * Time: 10:51:38 PM
 */
@RemoteServiceRelativePath("GWTServices/menuService")
public interface MenuService extends RemoteService {
    Map<Long, String> downloadSpecialityName();

    Map<Long, String> downloadGroupName(Long specialityId);

//    ArrayList< Map< Speciality, List<Group> > > downloadMenuData();

    /**
     * Utility/Convenience class.
     * Use MenuService.App.getInstance() to access static instance of MenuServiceAsync
     */
    public static class App {
        private static final MenuServiceAsync ourInstance = (MenuServiceAsync) GWT.create(MenuService.class);

        public static MenuServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
