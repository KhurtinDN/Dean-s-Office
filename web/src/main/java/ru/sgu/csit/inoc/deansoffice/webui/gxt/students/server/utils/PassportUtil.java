package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Passport;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.server.util.PersonUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.PassportModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:39 PM
 */
public class PassportUtil extends PersonUtil {
    public static List<PassportModel> convertPassportListsToPassportModelList(List<Passport> passports) {
        if (passports == null) {
            return null;
        }

        List<PassportModel> passportModelList = new ArrayList<PassportModel>(passports.size());

        for (Passport passport : passports) {
            passportModelList.add(convertPassportToPassportModel(passport));
        }

        return passportModelList;
    }

    public static List<Passport> convertPassportModelListsToPassportList(List<PassportModel> passportModelList) {
        if (passportModelList == null) {
            return null;
        }

        List<Passport> passportList = new ArrayList<Passport>(passportModelList.size());

        for (PassportModel passportModel : passportModelList) {
            passportList.add(convertPassportModelToPassport(passportModel));
        }

        return passportList;
    }

    public static PassportModel convertPassportToPassportModel(Passport passport) {
        if (passport == null) {
            return null;
        }

        PassportModel passportModel = new PassportModel();
        populatePersonModelByPerson(passportModel, passport);
        passportModel.setActual(passport.isActual());
        passportModel.setSeries(passport.getSeries());
        passportModel.setNumber(passport.getNumber());
        passportModel.setIssuingOrganization(passport.getIssuingOrganization());
        passportModel.setIssuedDate(passport.getIssuedDate());
        passportModel.setCitizenship(passport.getCitizenship());
        passportModel.setAddress(passport.getAddress());

        return passportModel;
    }

    public static Passport convertPassportModelToPassport(PassportModel passportModel) {
        if (passportModel == null) {
            return null;
        }
        Passport passport = new Passport();
        populatePersonByPersonModel(passport, passportModel);
        passport.setActual(passportModel.isActual());
        passport.setSeries(passportModel.getSeries());
        passport.setNumber(passportModel.getNumber());
        passport.setIssuingOrganization(passportModel.getIssuingOrganization());
        passport.setIssuedDate(passportModel.getIssuedDate());
        passport.setCitizenship(passportModel.getCitizenship());
        passport.setAddress(passportModel.getAddress());

        return passport;
    }
}
