package ru.sgu.csit.inoc.deansoffice.services.impl;

import ru.sgu.csit.inoc.deansoffice.domain.Passport;
import ru.sgu.csit.inoc.deansoffice.domain.Person;
import ru.sgu.csit.inoc.deansoffice.services.PasportService;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:20
 */
public class PassportServiceImpl implements PasportService {
    @Override
    public void fillAllFields(Passport passport, String value) {
        fillPasportDataFields(passport, value);

        passport.setSex(Person.Sex.UNKNOWN);

        passport.setFirstName(value);
        passport.setMiddleName(value);
        passport.setLastName(value);

        passport.setFirstNameDative(value);
        passport.setMiddleNameDative(value);
        passport.setLastNameDative(value);
    }

    @Override
    public void fillPasportDataFields(Passport passport, String value) {
        passport.setSeries(value);
        passport.setNumber(value);
        //passport.setAddress();
        passport.setCitizenship(value);
        passport.setIssuedDate(null);
        passport.setIssuingOrganization(value);
    }
}
