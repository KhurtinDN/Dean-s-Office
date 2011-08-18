package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 4:08 PM
 */
public class FacultyUtil {
    public static FacultyModel convertFacultyToFacultyModel(Faculty faculty) {
        if (faculty == null) {
            return null;
        }

        FacultyModel facultyModel =  new FacultyModel();
        facultyModel.setId(faculty.getId());
        facultyModel.setName(faculty.getShortName());
        facultyModel.setFullName(faculty.getFullName());

        Employee dean = faculty.getDean();

        if (dean != null) {
            EmployeeModel deanModel = new EmployeeModel();
            PersonUtil.populatePersonModelByPerson(deanModel, dean);

            facultyModel.setDean(deanModel);
        }

        return facultyModel;
    }

    public static Faculty convertFacultyModelToFaculty(FacultyModel facultyModel, Employee dean) {
        if (facultyModel == null) {
            return null;
        }

        Faculty faculty =  new Faculty();
        faculty.setId(facultyModel.getId());
        faculty.setShortName(facultyModel.getName());
        faculty.setFullName(facultyModel.getFullName());
        faculty.setDean(dean);

        return faculty;
    }
}
