package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class GroupUtil {
    public static GroupModel convertGroupToGroupModel(final Group group, final SpecialityModel specialityModel) {
        if (group == null) {
            return null;
        }

        final GroupModel groupModel = new GroupModel();
        groupModel.setId(group.getId());
        groupModel.setName(group.getName());
        groupModel.setCourse(group.getCourse());
        groupModel.setSpeciality(specialityModel);

        return groupModel;
    }

    public static GroupModel convertGroupToGroupModel(final Group group) {
        if (group == null) {
            return null;
        }

        final SpecialityModel specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(group.getSpeciality());

        return convertGroupToGroupModel(group, specialityModel);
    }

    public static List<GroupModel> convertGroupListToGroupModelList(final List<Group> groupList,
                                                                    final SpecialityModel specialityModel) {
        if (groupList == null) {
            return null;
        }

        final List<GroupModel> groupModelList = new ArrayList<GroupModel>(groupList.size());

        for (final Group group : groupList) {
            groupModelList.add(convertGroupToGroupModel(group, specialityModel));
        }

        return groupModelList;
    }

    public static Group convertGroupModelToGroup(final GroupModel groupModel, final Speciality speciality) {
        if (groupModel == null) {
            return null;
        }

        final Group group = new Group();
        group.setId(groupModel.getId());
        group.setName(groupModel.getName());
        group.setCourse(groupModel.getCourse());
        group.setSpeciality(speciality);

        return group;
    }
}
