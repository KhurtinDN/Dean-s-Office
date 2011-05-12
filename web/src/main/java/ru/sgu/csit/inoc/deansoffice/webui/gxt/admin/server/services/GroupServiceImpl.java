package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.GroupUtil;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/18/11
 * Time: 12:06 PM
 */
@Service("AdminGroupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private SpecialityDAO specialityDAO;

    @Override
    public List<GroupModel> loadGroups(Long specialityId) {

        List<Group> groupList = groupDAO.findBySpecialityId(specialityId);

        return GroupUtil.convertGroupListToGroupModelList(groupList);
    }

    @Override
    public GroupModel create(Long specialityId) {
        Speciality speciality = specialityDAO.findById(specialityId);

        Group group = new Group();
        group.setName("[Имя]");
        group.setSpeciality(speciality);

        groupDAO.save(group);

        return GroupUtil.convertGroupToGroupModel(group);
    }

    @Override
    public void update(GroupModel groupModel) {
        Speciality speciality = null;

        if (groupModel.getSpeciality() != null) {
            speciality = specialityDAO.findById(groupModel.getSpeciality().getId());
        }

        Group group = GroupUtil.convertGroupModelToGroup(groupModel, speciality);

        groupDAO.update(group);
    }

    @Override
    public void delete(List<Long> groupIdList) {
        for (Long groupId : groupIdList) {
            groupDAO.deleteById(groupId);
        }
    }
}
