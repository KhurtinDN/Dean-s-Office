package ru.sgu.csit.inoc.deansoffice.client.gxt.menu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 16, 2010
 * Time: 10:51:38 PM
 */
@RemoteServiceRelativePath("menu")
public interface MenuService extends RemoteService {
    Map<Long, String> downloadSpecialityName();

    Map<Long, String> downloadGroupName(Long specialityId);

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
