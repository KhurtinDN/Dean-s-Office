package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Employee;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class FacultyUtil {
    public static FacultyModel convertFacultyToFacultyModel(final Faculty faculty) {
        if (faculty == null) {
            return null;
        }

        final EmployeeModel deanModel = EmployeeUtil.convertEmployeeToEmployeeModel(faculty.getDean());

        return convertFacultyToFacultyModel(faculty, deanModel);
    }

    public static FacultyModel convertFacultyToFacultyModel(final Faculty faculty, final EmployeeModel dean) {
        if (faculty == null) {
            return null;
        }

        final FacultyModel facultyModel =  new FacultyModel();
        facultyModel.setId(faculty.getId());
        facultyModel.setName(faculty.getShortName());
        facultyModel.setFullName(faculty.getFullName());
        facultyModel.setDean(dean);

        return facultyModel;
    }

    public static Faculty convertFacultyModelToFaculty(final FacultyModel facultyModel, final Employee dean) {
        if (facultyModel == null) {
            return null;
        }

        final Faculty faculty =  new Faculty();
        faculty.setId(facultyModel.getId());
        faculty.setShortName(facultyModel.getName());
        faculty.setFullName(facultyModel.getFullName());
        faculty.setDean(dean);

        return faculty;
    }

    public static List<FacultyModel> convertFacultyListToFacultyModelList(final List<Faculty> facultyList) {
        if (facultyList == null) {
            return null;
        }

        final List<FacultyModel> facultyModelList = new ArrayList<FacultyModel>(facultyList.size());

        for (final Faculty faculty : facultyList) {
            facultyModelList.add(convertFacultyToFacultyModel(faculty));
        }

        return facultyModelList;
    }
}
