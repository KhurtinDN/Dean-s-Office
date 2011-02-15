package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 4:00 PM
 */
public class GroupUtil {
    public static GroupModel convertGroupToGroupModel(Group group) {
        GroupModel groupModel = new GroupModel();
        groupModel.setId(group.getId());
        groupModel.setName(group.getName());
        groupModel.setCourse(group.getCourse());

        return groupModel;
    }

    public static GroupModel convertGroupToGroupModel(Group group, SpecialityModel specialityModel) {
        GroupModel groupModel = new GroupModel();
        groupModel.setId(group.getId());
        groupModel.setName(group.getName());
        groupModel.setCourse(group.getCourse());
        groupModel.setSpeciality(specialityModel);

        return groupModel;
    }

    public static List<GroupModel> convertGroupListToGroupModelList(List<Group> groupList,
                                                                    SpecialityModel specialityModel) {

        List<GroupModel> groupModelList = new ArrayList<GroupModel>(groupList.size());

        for (Group group : groupList) {
            groupModelList.add(convertGroupToGroupModel(group, specialityModel));
        }

        return groupModelList;
    }
}
