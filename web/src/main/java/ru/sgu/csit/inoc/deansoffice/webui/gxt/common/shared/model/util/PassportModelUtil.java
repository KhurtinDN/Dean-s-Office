package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.util;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PassportModel;

import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/3/11
 * Time: 2:27 PM
 */
public class PassportModelUtil {
    public static PassportModel createCopy(PassportModel passportModel) {
        PassportModel copyPassport = new PassportModel();

        copyPassport.setFirstName(passportModel.getFirstName());
        copyPassport.setLastName(passportModel.getLastName());
        copyPassport.setMiddleName(passportModel.getMiddleName());
        copyPassport.setBirthday(passportModel.getBirthday());
        copyPassport.setSex(passportModel.getSex());

//        copyPassport.setActual(passportModel.isActual());
//        copyPassport.setSeries(passportModel.getSeries());
//        copyPassport.setNumber(passportModel.getNumber());
//        copyPassport.setIssuedDate(passportModel.getIssuedDate());
        copyPassport.setIssuingOrganization(passportModel.getIssuingOrganization());
        copyPassport.setCitizenship(passportModel.getCitizenship());
        copyPassport.setAddress(passportModel.getAddress());

        return copyPassport;
    }

    public static PassportModel findCurrentPassport(List<PassportModel> passports) {
        if (passports == null) {
            return null;
        }

        for (PassportModel passportModel : passports) {
            if (passportModel.isActual()) {
                return passportModel;
            }
        }

        return null;
    }
}
