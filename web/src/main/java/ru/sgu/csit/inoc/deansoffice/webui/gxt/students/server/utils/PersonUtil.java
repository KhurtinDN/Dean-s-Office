package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils;

import ru.sgu.csit.inoc.deansoffice.domain.Person;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.PersonModel;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/11/11
 * Time: 3:33 PM
 */
public class PersonUtil {
    public static void populatePerson(Person person, PersonModel personModel) {
        personModel.setId(person.getId());
        personModel.setFirstName(person.getFirstName());
        personModel.setMiddleName(person.getMiddleName());
        personModel.setLastName(person.getLastName());
        personModel.setBirthday(person.getBirthday());
        personModel.setSex( convertPersonSexToPersonModelSex(person.getSex()));
    }

    public static PersonModel.Sex convertPersonSexToPersonModelSex(Person.Sex sex) {
        if (sex == null) {
            return null;
        }
        switch (sex) {
            case MALE:
                return PersonModel.Sex.MALE;
            case FEMALE:
                return PersonModel.Sex.FEMALE;
            default:
                return null;
        }
    }
}
