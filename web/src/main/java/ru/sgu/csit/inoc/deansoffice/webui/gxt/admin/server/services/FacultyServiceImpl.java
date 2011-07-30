package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.EmployeeDAO;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.FacultyUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/25/11
 * Time: 2:25 PM
 */
@Service("AdminFacultyService")
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<FacultyModel> loadFaculties() {
        List<Faculty> facultyList = facultyDAO.findAll();

        return FacultyUtil.convertFacultyListToFacultyModelList(facultyList);
    }

    @Override
    public FacultyModel createFaculty() {
        Faculty faculty = new Faculty();
        faculty.setShortName("[Имя]");
        faculty.setFullName("[Полное имя]");

        facultyDAO.save(faculty);

        return FacultyUtil.convertFacultyToFacultyModel(faculty);
    }

    @Override
    public void updateFaculty(FacultyModel facultyModel) {
        Employee dean = null;

        if (facultyModel.getDean() != null) {
            dean = employeeDAO.findDeanById(facultyModel.getDean().getId());
        }

        Faculty faculty = FacultyUtil.convertFacultyModelToFaculty(facultyModel, dean);

        facultyDAO.update(faculty);
    }

    @Override
    public void deleteFaculties(List<Long> facultyIdList) {
        for (Long facultyId : facultyIdList) {
            facultyDAO.deleteById(facultyId);
        }
    }
}
