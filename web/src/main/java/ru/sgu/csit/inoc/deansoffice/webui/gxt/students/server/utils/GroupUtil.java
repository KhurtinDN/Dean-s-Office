package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 4:00 PM
 */
public class GroupUtil {
    public static GroupModel convertGroupToGroupModel(Group group) {
        if (group == null) {
            return null;
        }

        GroupModel groupModel = new GroupModel();
        groupModel.setId(group.getId());
        groupModel.setName(group.getName());
        groupModel.setCourse(group.getCourse());

        return groupModel;
    }

    public static GroupModel convertGroupToGroupModel(Group group, SpecialityModel specialityModel) {
        if (group == null) {
            return null;
        }

        GroupModel groupModel = new GroupModel();
        groupModel.setId(group.getId());
        groupModel.setName(group.getName());
        groupModel.setCourse(group.getCourse());
        groupModel.setSpecialityName(specialityModel.getName());

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
}
