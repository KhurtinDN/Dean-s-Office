package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Dean;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.PersonModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 4:08 PM
 */
public class FacultyUtil {
    public static FacultyModel convertFacultyToFacultyModel(Faculty faculty) {
        Dean dean = faculty.getDean();
        PersonModel deanModel = new PersonModel();
        PersonUtil.populatePerson(dean, deanModel);

        FacultyModel facultyModel =  new FacultyModel(faculty.getId());
        facultyModel.setDean(deanModel);
        facultyModel.setShortName(faculty.getShortName());
        facultyModel.setFullName(faculty.getFullName());
        facultyModel.setCourseCount(faculty.getCourseCount());

        return facultyModel;
    }
}
