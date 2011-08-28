package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
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
 * @author Denis Khurtin
 */
@Service("AdminFacultyService")
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<FacultyModel> loadFaculties() {
        final List<Faculty> facultyList = facultyDAO.findAll();

        return FacultyUtil.convertFacultyListToFacultyModelList(facultyList);
    }

    @Override
    public FacultyModel saveOrUpdate(FacultyModel facultyModel) {
        Validate.notNull(facultyModel, "facultyModel is null");

        Employee dean = null;

        if (facultyModel.getDean() != null) {
            dean = employeeDAO.findDeanById(facultyModel.getDean().getId());
        }

        final Faculty faculty = FacultyUtil.convertFacultyModelToFaculty(facultyModel, dean);

        facultyDAO.saveOrUpdate(faculty);

        return FacultyUtil.convertFacultyToFacultyModel(faculty);
    }

    @Override
    public void deleteFaculties(List<Long> facultyIdList) {
        Validate.noNullElements(facultyIdList, "Faculty id list is null or contains null element.");

        for (final Long facultyId : facultyIdList) {
            facultyDAO.deleteById(facultyId);
        }
    }
}
