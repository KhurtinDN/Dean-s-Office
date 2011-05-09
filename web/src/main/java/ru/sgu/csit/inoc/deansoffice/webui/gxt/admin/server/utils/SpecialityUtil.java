package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/26/11
 * Time: 11:02 AM
 */
public class SpecialityUtil {
    public static SpecialityModel convertSpecialityToSpecialityModel(Speciality speciality) {
        if (speciality == null) {
            return null;
        }

        FacultyModel facultyModel = FacultyUtil.convertFacultyToFacultyModel(speciality.getFaculty());

        return convertSpecialityToSpecialityModel(speciality, facultyModel);
    }

    public static SpecialityModel convertSpecialityToSpecialityModel(Speciality speciality, FacultyModel facultyModel) {
        if (speciality == null) {
            return null;
        }

        SpecialityModel specialityModel = new SpecialityModel();
        specialityModel.setId(speciality.getId());
        specialityModel.setFullName(speciality.getName());
        specialityModel.setName(speciality.getShortName());
        specialityModel.setCode(speciality.getCode());
        specialityModel.setFaculty(facultyModel);

        return specialityModel;
    }

    public static List<SpecialityModel> convertSpecialityListToSpecialityModelList(List<Speciality> specialityList) {
        if (specialityList == null) {
            return null;
        }

        List<SpecialityModel> specialityModelList = new ArrayList<SpecialityModel>(specialityList.size());

        for (Speciality speciality : specialityList) {
            specialityModelList.add(convertSpecialityToSpecialityModel(speciality));
        }

        return specialityModelList;
    }

    public static List<SpecialityModel> convertSpecialityListToSpecialityModelList(List<Speciality> specialityList,
                                                                                   FacultyModel facultyModel) {
        if (specialityList == null) {
            return null;
        }

        List<SpecialityModel> specialityModelList = new ArrayList<SpecialityModel>(specialityList.size());

        for (Speciality speciality : specialityList) {
            specialityModelList.add(convertSpecialityToSpecialityModel(speciality, facultyModel));
        }

        return specialityModelList;
    }

    public static Speciality convertSpecialityModelToSpeciality(SpecialityModel specialityModel, Faculty faculty) {
        if (specialityModel == null) {
            return null;
        }

        Speciality speciality = new Speciality();
        speciality.setId(specialityModel.getId());
        speciality.setName(specialityModel.getFullName());
        speciality.setShortName(specialityModel.getName());
        speciality.setCode(specialityModel.getCode());
        speciality.setFaculty(faculty);

        return speciality;
    }
}
