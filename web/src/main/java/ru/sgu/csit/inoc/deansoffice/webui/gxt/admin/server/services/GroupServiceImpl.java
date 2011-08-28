package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.SpecialityUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.GroupUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Service("AdminGroupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private SpecialityDAO specialityDAO;

    @Override
    public List<GroupModel> loadGroups(final Long specialityId) {
        Validate.notNull(specialityId, "specialityId is null");

        final List<Group> groupList = groupDAO.findBySpecialityId(specialityId);

        SpecialityModel specialityModel = null;
        if (groupList.size() > 0) {
            final Speciality speciality = groupList.get(0).getSpeciality();
            specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(speciality);
        }

        return GroupUtil.convertGroupListToGroupModelList(groupList, specialityModel);
    }

    @Override
    public GroupModel saveOrUpdate(final GroupModel groupModel) {
        Validate.notNull(groupModel, "groupModel is null");

        Speciality speciality = null;

        if (groupModel.getSpeciality() != null) {
            speciality = specialityDAO.findById(groupModel.getSpeciality().getId());
        }

        final Group group = GroupUtil.convertGroupModelToGroup(groupModel, speciality);

        groupDAO.saveOrUpdate(group);

        return GroupUtil.convertGroupToGroupModel(group);
    }

    @Override
    public void delete(final List<Long> groupIdList) {
        Validate.noNullElements(groupIdList, "Group id list is null or contains null element.");

        for (final Long groupId : groupIdList) {
            groupDAO.deleteById(groupId);
        }
    }
}
