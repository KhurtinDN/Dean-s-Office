package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Dean;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

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

        Dean dean = faculty.getDean();

        if (dean != null) {
            PersonModel deanModel = new PersonModel();
            PersonUtil.populatePersonModelByPerson(deanModel, dean);

            facultyModel.setDean(deanModel);
        }

        return facultyModel;
    }

    public static Faculty convertFacultyModelToFaculty(FacultyModel facultyModel, Dean dean) {
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
