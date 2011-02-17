package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Passport;

import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:19
 */
public interface PasportService {
    void fillAllFields(Passport passport, String value);
    void fillPasportDataFields(Passport passport, String value);
    void sortPassportsByDate(List<Passport> passports, final boolean asc);
}
