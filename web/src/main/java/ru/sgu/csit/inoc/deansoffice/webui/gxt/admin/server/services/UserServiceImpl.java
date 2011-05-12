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
    private List<UserModel> users = new ArrayList<UserModel>(); // todo: implement
    {
        UserModel userModel = new UserModel();
        userModel.setId((long) users.size());
        userModel.setLastName("Иванов");
        userModel.setFirstName("Иван");
        userModel.setMiddleName("Иванович");
        userModel.setFullName(userModel.getLastName() + ' ' + userModel.getFirstName() + ' ' + userModel.getMiddleName());
        userModel.setLogin("test");
        userModel.setPassword("test");
        users.add(userModel);

        userModel = new UserModel();
        userModel.setId((long) users.size());
        userModel.setLastName("Хуртин");
        userModel.setFirstName("Денис");
        userModel.setMiddleName("Николаевич");
        userModel.setFullName(userModel.getLastName() + ' ' + userModel.getFirstName() + ' ' + userModel.getMiddleName());
        userModel.setLogin("hd");
        userModel.setPassword("qwerty");
        users.add(userModel);
    }

    @Override
    public List<UserModel> loadUsers() {
        return users;
    }

    @Override
    public UserModel create() {
        UserModel userModel = new UserModel();
        userModel.setId((long) users.size());

        users.add(userModel);

        return userModel;
    }

    @Override
    public void update(UserModel userModel) {
        users.set(userModel.getId().intValue(), userModel);
    }

    @Override
    public void delete(List<Long> userIdList) {
        List<UserModel> userModelList = new ArrayList<UserModel>(userIdList.size());

        for (Long id : userIdList) {
            userModelList.add(users.get(id.intValue()));
        }

        users.removeAll(userModelList);
    }
}
