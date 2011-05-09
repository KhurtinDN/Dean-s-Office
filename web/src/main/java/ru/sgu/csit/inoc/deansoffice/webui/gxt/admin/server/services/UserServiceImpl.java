package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.UserService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 8:10 PM
 */
@Service("AdminUserService")
public class UserServiceImpl implements UserService {
    @Override
    public List<UserModel> loadUsers() {
        List<UserModel> users = new ArrayList<UserModel>();

        UserModel userModel = new UserModel();
        userModel.setLastName("Иванов");
        userModel.setFirstName("Иван");
        userModel.setMiddleName("Иванович");
        userModel.setLogin("test");
        userModel.setPassword("test");
        users.add(userModel);

        userModel = new UserModel();
        userModel.setLastName("Хуртин");
        userModel.setFirstName("Денис");
        userModel.setMiddleName("Николаевич");
        userModel.setLogin("hd");
        userModel.setPassword("qwerty");
        users.add(userModel);

        return users;
    }
}
