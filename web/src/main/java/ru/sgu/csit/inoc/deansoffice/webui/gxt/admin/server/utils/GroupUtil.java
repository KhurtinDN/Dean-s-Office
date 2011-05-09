package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/26/11
 * Time: 11:00 AM
 */
public class GroupUtil {
    public static GroupModel convertGroupToGroupModel(Group group, SpecialityModel specialityModel) {
        if (group == null) {
            return null;
        }

        GroupModel groupModel = new GroupModel();
        groupModel.setId(group.getId());
        groupModel.setName(group.getName());
        groupModel.setCourse(group.getCourse());
        groupModel.setSpeciality(specialityModel);

        return groupModel;
    }

    public static GroupModel convertGroupToGroupModel(Group group) {
        if (group == null) {
            return null;
        }

        SpecialityModel specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(group.getSpeciality());

        GroupModel groupModel = convertGroupToGroupModel(group, specialityModel);
        groupModel.setSpeciality(specialityModel);

        return groupModel;
    }

    public static List<GroupModel> convertGroupListToGroupModelList(List<Group> groupList,
                                                                    SpecialityModel specialityModel) {
        if (groupList == null) {
            return null;
        }

        List<GroupModel> groupModelList = new ArrayList<GroupModel>(groupList.size());

        for (Group group : groupList) {
            groupModelList.add(convertGroupToGroupModel(group, specialityModel));
        }

        return groupModelList;
    }

    public static List<GroupModel> convertGroupListToGroupModelList(List<Group> groupList) {
        if (groupList == null) {
            return null;
        }

        List<GroupModel> groupModelList = new ArrayList<GroupModel>(groupList.size());

        for (Group group : groupList) {
            groupModelList.add(convertGroupToGroupModel(group));
        }

        return groupModelList;
    }

    public static Group convertGroupModelToGroup(GroupModel groupModel, Speciality speciality) {
        if (groupModel == null) {
            return null;
        }

        Group group = new Group();
        group.setId(groupModel.getId());
        group.setName(groupModel.getName());
        group.setCourse(groupModel.getCourse());
        group.setSpeciality(speciality);

        return group;
    }
}
