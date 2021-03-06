package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Dean;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/26/11
 * Time: 10:47 AM
 */
public class FacultyUtil {
    public static FacultyModel convertFacultyToFacultyModel(Faculty faculty) {
        if (faculty == null) {
            return null;
        }

        PersonModel deanModel = null;

        Dean dean = faculty.getDean();

        if (dean != null) {
            deanModel = new PersonModel();
            PersonUtil.populatePersonModelByPerson(deanModel, dean);
        }

        return convertFacultyToFacultyModel(faculty, deanModel);
    }

    public static FacultyModel convertFacultyToFacultyModel(Faculty faculty, PersonModel dean) {
        if (faculty == null) {
            return null;
        }

        FacultyModel facultyModel =  new FacultyModel();
        facultyModel.setId(faculty.getId());
        facultyModel.setName(faculty.getShortName());
        facultyModel.setFullName(faculty.getFullName());
        facultyModel.setDean(dean);

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

    public static List<FacultyModel> convertFacultyListToFacultyModelList(List<Faculty> facultyList) {
        if (facultyList == null) {
            return null;
        }

        List<FacultyModel> facultyModelList = new ArrayList<FacultyModel>(facultyList.size());

        for (Faculty faculty : facultyList) {
            facultyModelList.add(convertFacultyToFacultyModel(faculty));
        }

        return facultyModelList;
    }

    public static List<FacultyModel> convertFacultyListToFacultyModelList(List<Faculty> facultyList, PersonModel dean) {
        if (facultyList == null) {
            return null;
        }

        List<FacultyModel> facultyModelList = new ArrayList<FacultyModel>(facultyList.size());

        for (Faculty faculty : facultyList) {
            facultyModelList.add(convertFacultyToFacultyModel(faculty, dean));
        }

        return facultyModelList;
    }
}
