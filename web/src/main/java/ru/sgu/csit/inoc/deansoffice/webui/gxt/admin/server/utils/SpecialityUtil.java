package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class SpecialityUtil {
    public static SpecialityModel convertSpecialityToSpecialityModel(final Speciality speciality) {
        if (speciality == null) {
            return null;
        }

        final FacultyModel facultyModel = FacultyUtil.convertFacultyToFacultyModel(speciality.getFaculty());

        return convertSpecialityToSpecialityModel(speciality, facultyModel);
    }

    public static SpecialityModel convertSpecialityToSpecialityModel(final Speciality speciality,
                                                                     final FacultyModel facultyModel) {
        if (speciality == null) {
            return null;
        }

        final SpecialityModel specialityModel = new SpecialityModel();
        specialityModel.setId(speciality.getId());
        specialityModel.setFullName(speciality.getName());
        specialityModel.setName(speciality.getShortName());
        specialityModel.setCode(speciality.getCode());
        specialityModel.setCourseCount(speciality.getCourseCount());
        specialityModel.setFaculty(facultyModel);

        return specialityModel;
    }

    public static List<SpecialityModel> convertSpecialityListToSpecialityModelList(final List<Speciality> specialityList,
                                                                                   final FacultyModel facultyModel) {
        if (specialityList == null) {
            return null;
        }

        final List<SpecialityModel> specialityModelList = new ArrayList<SpecialityModel>(specialityList.size());

        for (final Speciality speciality : specialityList) {
            specialityModelList.add(convertSpecialityToSpecialityModel(speciality, facultyModel));
        }

        return specialityModelList;
    }

    public static Speciality convertSpecialityModelToSpeciality(final SpecialityModel specialityModel,
                                                                final Faculty faculty) {
        if (specialityModel == null) {
            return null;
        }

        final Speciality speciality = new Speciality();
        speciality.setId(specialityModel.getId());
        speciality.setName(specialityModel.getFullName());
        speciality.setShortName(specialityModel.getName());
        speciality.setCode(specialityModel.getCode());
        speciality.setCourseCount(specialityModel.getCourseCount());
        speciality.setFaculty(faculty);

        return speciality;
    }
}
