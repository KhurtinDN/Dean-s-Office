package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:56 PM
 */
public class SpecialityUtil {
    public static SpecialityModel convertSpecialityToSpecialityModel(Speciality speciality) {
        if (speciality == null) {
            return null;
        }

        SpecialityModel specialityModel = new SpecialityModel();
        specialityModel.setId(speciality.getId());
        specialityModel.setFullName(speciality.getName());
        specialityModel.setName(speciality.getShortName());
        specialityModel.setCode(speciality.getCode());

        return specialityModel;
    }
}
