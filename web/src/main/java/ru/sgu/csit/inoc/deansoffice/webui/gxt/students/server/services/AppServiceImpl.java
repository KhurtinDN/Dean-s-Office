package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.AppService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.FacultyUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.GroupUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.SpecialityUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/10/11
 * Time: 2:18 PM
 */
@Service("AppService")
public class AppServiceImpl implements AppService {
    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private SpecialityDAO specialityDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public FacultyModel loadFaculty() {
        List<Faculty> facultyList = facultyDAO.findAll();     // todo: how to get strict ONE object

        if (facultyList == null || facultyList.size() != 1) {
            throw new RuntimeException("Faculty must be one object!"); // todo: how get csit?
        }

        Faculty faculty = facultyList.get(0);

        return FacultyUtil.convertFacultyToFacultyModel(faculty);
    }

    @Override
    public List<Map<SpecialityModel, List<GroupModel>>> loadMenuData(Long facultyId) {
        Integer courseCount = facultyDAO.findById(facultyId).getCourseCount();

        List<Map<SpecialityModel, List<GroupModel>>> result =
                new ArrayList<Map<SpecialityModel, List<GroupModel>>>(courseCount);

        for (int course = 1; course <= courseCount; ++course) {
            Map<SpecialityModel, List<GroupModel>> specialityGroupMap =
                    new LinkedHashMap<SpecialityModel, List<GroupModel>>();

            for (Speciality speciality : specialityDAO.findByFacultyId(facultyId)) {
                List<Group> groupList = groupDAO.findByCourseAndSpecialityId(course, speciality.getId());

                SpecialityModel specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(speciality);

                List<GroupModel> groupModelList =
                        GroupUtil.convertGroupListToGroupModelList(groupList, specialityModel);

                specialityGroupMap.put(specialityModel, groupModelList);
            }

            result.add(specialityGroupMap);
        }

        return result;
    }
}
