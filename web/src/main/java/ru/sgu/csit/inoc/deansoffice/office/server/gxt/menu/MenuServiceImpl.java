package ru.sgu.csit.inoc.deansoffice.office.server.gxt.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.TestMain;
import ru.sgu.csit.inoc.deansoffice.office.client.gxt.menu.MenuService;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.GroupDto;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.SpecialityDto;

import java.util.*;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 16, 2010
 * Time: 10:51:39 PM
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private SpecialityDAO specialityDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public ArrayList<Map<SpecialityDto, List<GroupDto>>> downloadMenuData() {
        List<Faculty> facultyList = facultyDAO.findAll();     // todo: how to get strict ONE object
        if (facultyList == null || facultyList.size() != 1) {
            throw new RuntimeException("Faculty must be one object!");
        }
        Faculty faculty = facultyList.get(0);

        Integer courseCount = faculty.getCourseCount();
        ArrayList<Map<SpecialityDto, List<GroupDto>>> result =
                new ArrayList<Map<SpecialityDto, List<GroupDto>>>(courseCount);

        for (int course = 1; course <= courseCount; ++course) {
            Map<SpecialityDto, List<GroupDto>> specialityGroupMap = new LinkedHashMap<SpecialityDto, List<GroupDto>>();

            List<Speciality> specialityList = specialityDAO.findByFaculty(faculty);

            for (Speciality speciality : specialityList) {
                List<Group> groupList = groupDAO.findByCourseAndSpeciality(course, speciality);

                List<GroupDto> groupDtoList = new ArrayList<GroupDto>(groupList.size());
                for (Group group : groupList) {
                    groupDtoList.add(new GroupDto(group.getId(), group.getName()));
                }

                specialityGroupMap.put(new SpecialityDto(speciality.getId(), speciality.getShortName()), groupDtoList);
            }

            result.add(specialityGroupMap);
        }

        return result;
    }

    @Override
    public void generateBase() {
        TestMain.main(null);
    }
}
