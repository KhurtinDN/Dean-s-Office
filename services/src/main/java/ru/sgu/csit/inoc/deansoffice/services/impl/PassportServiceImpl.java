package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.domain.Passport;
import ru.sgu.csit.inoc.deansoffice.domain.Person;
import ru.sgu.csit.inoc.deansoffice.services.PasportService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:20
 */
@Service
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

    @Override
    public void sortPassportsByDate(List<Passport> passports, final boolean asc) {
        if (passports == null) {
            throw new RuntimeException("Passports list is null!! ");
        }
        Collections.sort(passports, new Comparator<Passport>() {
            public int compare(Passport ps1, Passport ps2) {
                Integer result = ps1.getIssuedDate().compareTo(ps2.getIssuedDate());

                return asc ? result : result * -1;
            }
        });
    }
}
