package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.exceptions.BadRequestException;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.AppService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.FacultyUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.GroupUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.SpecialityUtil;
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
    public List<Map<SpecialityModel, List<GroupModel>>> loadMenuData(Long facultyId) { // todo: recode this bad method
        if (facultyId == null) {
            throw new BadRequestException("Id must be not null.");
        }

        Faculty faculty = facultyDAO.findById(facultyId);

        if (faculty == null) {
            throw new BadRequestException("Requested id not found.");
        }

        List<Map<SpecialityModel, List<GroupModel>>> result = new ArrayList<Map<SpecialityModel, List<GroupModel>>>();

        List<Speciality> specialityList = specialityDAO.findByFacultyId(facultyId);

        for (Speciality speciality : specialityList) {
            SpecialityModel specialityModel = SpecialityUtil.convertSpecialityToSpecialityModel(speciality);

            for (int course = 0; course < speciality.getCourseCount(); ++course) {
                List<Group> groupList = groupDAO.findByCourseAndSpecialityId(course + 1, speciality.getId());
                List<GroupModel> groupModelList =
                        GroupUtil.convertGroupListToGroupModelList(groupList, specialityModel);

                while (result.size() <= course) {
                    result.add(new LinkedHashMap<SpecialityModel, List<GroupModel>>());
                }

                Map<SpecialityModel, List<GroupModel>> specialityGroupMap = result.get(course);

                specialityGroupMap.put(specialityModel, groupModelList);
            }
        }

        return result;
    }

    @Override
    public FacultyModel loadActiveFaculty() {
        Faculty faculty = facultyDAO.findByShortName("КНиИТ").get(0); // todo: how get active faculty by user from session?
        return FacultyUtil.convertFacultyToFacultyModel(faculty);
    }
}
