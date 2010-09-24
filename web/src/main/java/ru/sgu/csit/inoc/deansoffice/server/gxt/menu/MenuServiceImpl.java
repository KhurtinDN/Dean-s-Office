package ru.sgu.csit.inoc.deansoffice.server.gxt.menu;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sgu.csit.inoc.deansoffice.client.gxt.menu.MenuService;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 16, 2010
 * Time: 10:51:39 PM
 */
public class MenuServiceImpl extends RemoteServiceServlet implements MenuService {
    @Autowired
    private SpecialityDAO specialityDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public Map<Long, String> downloadSpecialityName() { // Map<Long, String>
        List<Speciality> specialityList = specialityDAO.findAll(Speciality.class);
        Map<Long, String> result = new HashMap<Long, String>();

        for (Speciality speciality : specialityList) {
            result.put(speciality.getId(), speciality.getName());
        }

        return result;
    }

    @Override
    public Map<Long, String> downloadGroupName(Long specialityId) {
        List<Group> groupList = groupDAO.findAll(Group.class);
        Map<Long, String> result = new HashMap<Long, String>();

        for (Group group : groupList) {
            result.put(group.getId(), group.getName());
        }

        return result;
    }
}